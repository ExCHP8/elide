/*
 * Copyright (c) 2024 Elide Technologies, Inc.
 *
 * Licensed under the MIT license (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   https://opensource.org/license/mit/
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License.
 */
package elide.tool.engine

import io.netty.channel.unix.Unix
import io.netty.util.internal.PlatformDependent
import org.graalvm.nativeimage.ImageInfo
import org.graalvm.nativeimage.ProcessProperties
import java.io.File
import java.nio.file.Files
import java.util.concurrent.ConcurrentSkipListMap
import java.util.concurrent.ConcurrentSkipListSet
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString
import elide.runtime.LogLevel.DEBUG
import elide.runtime.core.HostPlatform
import elide.runtime.core.HostPlatform.OperatingSystem.*
import elide.tool.cli.Statics
import elide.tool.io.WorkdirManager

/**
 * # Native Engine
 *
 * Provides utilities for loading native portions of Elide early in the boot lifecycle.
 */
object NativeEngine {
  private const val loadEager = true
  private const val DEFAULT_NATIVES_PATH = "META-INF/native/"
  private val transportEngine: AtomicReference<String> = AtomicReference("nio")
  private val nativeTransportAvailable: AtomicBoolean = AtomicBoolean(false)
  private val errHolder: AtomicReference<Throwable?> = AtomicReference(null)
  private val missingLibraries: MutableSet<NativeLibInfo> = ConcurrentSkipListSet()
  private val nativeLibraryGroups: MutableMap<String, Boolean> = ConcurrentSkipListMap()

  internal fun transportEngine(): Pair<String, Boolean> = transportEngine.get() to nativeTransportAvailable.get()

  @JvmStatic private fun libExtension(os: HostPlatform.OperatingSystem): String? = when (os) {
    DARWIN -> "jnilib"
    LINUX -> "so"
    WINDOWS -> "dll"
    else -> null
  }

  // Calculate the path to a native resource which needs to be loaded.
  @JvmStatic private fun nativeLib(
    group: String,
    staticName: String,
    os: HostPlatform.OperatingSystem,
    base: String = DEFAULT_NATIVES_PATH,
    extension: String? = libExtension(os),
  ): NativeLib {
    val libname = StringBuilder().apply {
      // `umbrella` ...
      append(staticName)
    }

    val path = libname.toString() to StringBuilder(base).apply {
      // `META-INF/native/libumbrella` ...
      append("lib")
      append(libname)

      // `META-INF/native/libumbrella.so` ...
      if (extension?.isNotBlank() == true) {
        append(".")
        append(extension)
      }
    }.toString()

    return NativeLib.of(
      group,
      libname.toString(),
      path.toString(),
      staticName,
    )
  }

  // Calculate the path to a Netty native resource which needs to be loaded.
  @JvmStatic private fun nettyNativeLib(
    group: String,
    staticName: String,
    base: String = DEFAULT_NATIVES_PATH,
    os: HostPlatform.OperatingSystem,
    extension: String? = libExtension(os),
    builder: StringBuilder.() -> Unit,
  ): NativeLib {
    val libname = StringBuilder().apply {
      // `netty_transport_native_epoll_linux-x86_64` ...
      builder.invoke(this)
    }

    val path = libname.toString() to StringBuilder(base).apply {
      // `META-INF/native/libnetty_transport_native_epoll_linux-x86_64` ...
      append("lib")
      append(libname)

      // `META-INF/native/libnetty_transport_native_epoll_linux-x86_64.so` ...
      if (extension?.isNotBlank() == true) {
        append(".")
        append(extension)
      }
    }.toString()

    return NativeLib.of(
      group,
      libname.toString(),
      path.toString(),
      staticName,
    )
  }

  // Calculate the proper name of a Netty native library that needs to be loaded, based on the host OS.
  @JvmStatic private fun nettyNative(
    group: String,
    name: String,
    os: HostPlatform.OperatingSystem,
    static: String = name,
  ): NativeLib {
    val arch = PlatformDependent.normalizedArch()
    return nettyNativeLib(group = group, os = os, staticName = static) {
      // `netty_` ...
      append("netty_")

      // `netty_transport_native_epoll` ...
      append(name)

      // `netty_transport_native_epoll_linux-x86_64` ...
      append("_")
      append(arch)
    }
  }

  // Ensure a native library is loadable from the Java native path, copying from resources if necessary.
  @JvmStatic private fun ensureLoadableFromNatives(
    group: String,
    libs: List<NativeLibInfo>,
    workdir: File,
    loader: ClassLoader,
    forceLoad: Boolean = false,
  ) = libs.map {
    val (loaded, copied) = try {
      NativeUtil.loadOrCopy(
        workdir,
        it.path,
        it.name,
        loader,
        forceLoad = forceLoad,
      )
    } catch (err: Throwable) {
      missingLibraries.add(it)
      false to false
    }

    if (Statics.logging.isEnabled(DEBUG)) {
      Statics.logging.debug("Native library ${it.name} (loaded=$loaded, copied=$copied)")
    }
    loaded
  }.all { it }.also {
    nativeLibraryGroups[group] = it
  }

  // Load a named native library, varying by OS.
  @JvmStatic private fun HostPlatform.loadByPlatform(
    loader: ClassLoader,
    group: String,
    workdir: File,
    forceLoad: Boolean = !ImageInfo.inImageCode(),
    linux: (() -> List<NativeLibInfo>?)? = null,
    darwin: (() -> List<NativeLibInfo>?)? = null,
    windows: (() -> List<NativeLibInfo>?)? = null,
    default: (() -> List<NativeLibInfo>?)? = null,
  ) {
    when (os) {
      LINUX -> linux
      DARWIN -> darwin
      WINDOWS -> windows
    }.let {
      when (val target = (it?.invoke() ?: default?.invoke())) {
        null -> {}
        else -> ensureLoadableFromNatives(group, target, workdir, loader, forceLoad)
      }
    }
  }

  // Load native tooling libraries, based on OS.
  @JvmStatic private fun HostPlatform.loadNativeTooling(workdir: File, loader: ClassLoader) = loadByPlatform(
    loader,
    "tools",
    workdir,
    default = {
      listOf(nativeLib("tools", "umbrella", os))
    },
  )

  // Load native transport libraries, based on OS.
  @JvmStatic private fun HostPlatform.loadNativeTransport(workdir: File, loader: ClassLoader) = loadByPlatform(
    loader,
    "transport",
    workdir,
    linux = { listOf(
      nettyNative("transport", "transport_native_epoll", os),
      nettyNative("transport", "quiche_linux", os),
    ) },
    darwin = { listOf(
      nettyNative("transport", "resolver_dns_native_macos", os),
      nettyNative("transport", "quiche_osx", os),
      nettyNative("transport", "transport_native_kqueue", os),
    ) },
  )

  // Load native OpenSSL libraries.
  @JvmStatic private fun HostPlatform.loadNativeCrypto(workdir: File, loader: ClassLoader) = loadByPlatform(
    loader,
    "crypto",
    workdir,
    linux = {
      listOf(nettyNative("crypto", "tcnative_linux", os))
    },
    darwin = {
      listOf(nettyNative("crypto", "tcnative_osx", os))
    },
  )

  // Load natives from the CWD while it is swapped in.
  @JvmStatic private fun loadAllNatives(platform: HostPlatform, natives: File, loader: ClassLoader) = platform.apply {
    // trigger load of native libs
    val loadNatives: () -> Unit = {
      loadNativeCrypto(natives, loader)
      loadNativeTransport(natives, loader)
//      loadNativeTooling(natives, loader)
    }

    try {
      when (platform.os) {
        // on linux, we prefer `epoll` unless `io_uring` is requested
        LINUX -> {{
          // load libraries
          loadNatives()

          // check and return availability of epoll
          when {
            io.netty.incubator.channel.uring.IOUring.isAvailable() ->
              io.netty.incubator.channel.uring.IOUring.ensureAvailability().let { "io_uring" to true }

            io.netty.channel.epoll.Epoll.isAvailable() ->
              io.netty.channel.epoll.Epoll.ensureAvailability().let { "epoll" to true }

            else -> "nio" to false
          }
        }}

        // on darwin, we prefer `kqueue`
        DARWIN -> {{
          // force `Unix` classes to load first
          @Suppress("DEPRECATION") assert(!Unix.isAvailable())

          // then force-load libraries
          loadNatives()

          // then check and load KQueue and return
          io.netty.channel.kqueue.KQueue.ensureAvailability().let { "kqueue" to true }
        }}

        // no native transport available
        else -> {{ "nio" to false }}
      }.invoke()
    } catch (err: Throwable) {
      errHolder.set(err)
      "nio" to false
    }.also { (label, available) ->
      transportEngine.set(label)
      nativeTransportAvailable.set(available)
    }
  }

  /**
   * ## Load Natives
   *
   * Prepare VM-level settings and trigger loading of critical native libraries.
   *
   * @param workdir Working directory manager.
   * @param extraProps Extra VM properties to set.
   */
  @JvmStatic fun load(workdir: WorkdirManager, extraProps: List<Pair<String, String>>) {
    val tmp = workdir.tmpDirectory()
    val tmpPath = tmp.absolutePath
    val natives = workdir.nativesDirectory()
    val nativesPath = natives.absolutePath
    val platform = HostPlatform.resolve()
    val separator = when (HostPlatform.resolve().os) {
      HostPlatform.OperatingSystem.WINDOWS -> ";"
      else -> ":"
    }
    val libraryPath = System.getProperty("java.library.path", "")
    val execPath = if (!ImageInfo.inImageCode()) null else {
      Path(ProcessProperties.getExecutableName())
    }

    // sanity check: exec path should exist, then convert it into a suite of lib exec paths
    val libExecPaths = (if (execPath != null) {
      if (!Files.exists(execPath)) error("Executable path does not exist: $execPath")
      if (Files.isDirectory(execPath)) {
        execPath.resolve("resources")
      } else {
        // if it's a binary, we need the peer directory
        execPath.parent.resolve("resources")
      }
    } else {
      // with no exec path, use the current working directory
      Path(System.getProperty("user.dir")?.ifBlank { null } ?: error("No working directory"))
    }).let { resolvedExecPrefix ->
      listOf(
        resolvedExecPrefix,
        resolvedExecPrefix.resolve("python/python-home/lib/graalpy24.1"),
        resolvedExecPrefix.resolve("llvm/libsulong-native"),
        resolvedExecPrefix.resolve("ruby/ruby-home"),
      )
    }

    val javaLibraryPath = System.getenv("JAVA_HOME")?.ifBlank { null }?.let {
      Path(it).resolve("lib")
    }

    val libPath = if (!libraryPath.contains("/elide-runtime")) {
      libraryPath.split(separator).toMutableList().apply {
        // elide's natives path
        add(0, nativesPath)

        // any given override path
        System.getProperty("elide.natives")?.let { add(1, it) }

        // bundled library paths
        addAll(libExecPaths.map { it.absolutePathString() })

        // java's library path
        if (javaLibraryPath != null) add(javaLibraryPath.absolutePathString())
      }.joinToString(separator)
    } else libraryPath

    listOf(
      "java.library.path" to libPath,
      "io.netty.tmpdir" to tmpPath,
      "io.netty.native.workdir" to nativesPath,
    ).plus(extraProps).forEach {
      System.setProperty(it.first, it.second)
    }

    if (loadEager) {
      loadAllNatives(platform, natives.toFile(), this::class.java.classLoader)
    }

    // fix: account for static jni
    if (ImageInfo.inImageCode()) listOf(
      "console",
      "crypto",
      "sqlite",
      "transport",
      "tools",
    ).forEach {
      nativeLibraryGroups[it] = true
    }
  }

  /**
   * ## Load Check
   *
   * Checks to make sure a [group] of named native libraries was properly loaded.
   *
   * @param group Name of the group to check.
   * @return Whether the group was loaded.
   */
  @JvmStatic fun didLoad(group: String): Boolean = nativeLibraryGroups[group] ?: false

  /**
   * ## Boot Entrypoint
   *
   * Early in the static init process for Elide, this method is called to prepare and load native libraries and apply
   * VM-level settings as early as possible.
   *
   * @param workdir Working directory manager.
   * @param properties Provider of extra VM properties to set.
   */
  @JvmStatic fun boot(workdir: WorkdirManager, properties: () -> List<Pair<String, String>>) {
    load(workdir, properties.invoke())
  }
}