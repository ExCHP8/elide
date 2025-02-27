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

package elide.embedded.interop

import org.graalvm.nativeimage.ObjectHandle
import org.graalvm.nativeimage.ObjectHandles
import kotlin.io.path.Path
import elide.embedded.*
import elide.embedded.EmbeddedAppConfiguration.EmbeddedDispatchMode
import elide.embedded.interop.NativeAppLanguage.JS
import elide.embedded.interop.NativeAppLanguage.PYTHON
import elide.embedded.interop.NativeAppMode.FETCH
import elide.embedded.interop.NativeProtocolFormat.CAPNPROTO
import elide.embedded.interop.NativeProtocolFormat.PROTOBUF
import elide.embedded.interop.NativeProtocolVersion.V1_0

/**
 * A collection of functions useful for translating between native C data structures and their equivalent in the
 * embedded JVM API.
 *
 * These helpers are meant to be used from Java code in the [ElideEmbeddedNative] singleton, which acts as an
 * entrypoint for the Elide Embedded shared library.
 */
internal object NativeInterop {
  /** Convert the provided C [version] enum to its JVM counterpart, [EmbeddedProtocolVersion]. */
  @JvmStatic fun mapVersion(version: NativeProtocolVersion): EmbeddedProtocolVersion = when (version) {
    V1_0 -> EmbeddedProtocolVersion.V1_0
  }

  /** Convert the provided C [format] enum to its JVM counterpart, [EmbeddedProtocolFormat]. */
  @JvmStatic fun mapFormat(format: NativeProtocolFormat): EmbeddedProtocolFormat = when (format) {
    PROTOBUF -> EmbeddedProtocolFormat.PROTOBUF
    CAPNPROTO -> EmbeddedProtocolFormat.CAPNPROTO
  }

  /** Convert the provided C [mode] enum to its JVM counterpart, [EmbeddedDispatchMode]. */
  @JvmStatic fun mapAppMode(mode: NativeAppMode): EmbeddedDispatchMode = when (mode) {
    FETCH -> EmbeddedDispatchMode.FETCH
  }

  /** Convert the provided C [lang] enum to its JVM form, [EmbeddedGuestLanguage]. */
  @JvmStatic fun mapAppLanguage(lang: NativeAppLanguage): EmbeddedGuestLanguage = when (lang) {
    JS -> EmbeddedGuestLanguage.JAVA_SCRIPT
    PYTHON -> EmbeddedGuestLanguage.PYTHON
  }

  /**
   * Shorthand for constructing a new [EmbeddedConfiguration] instance using C values. The [version] and [format]
   * enums are automatically converted to their JVM equivalents.
   */
  @JvmStatic fun createConfig(
    version: NativeProtocolVersion,
    format: NativeProtocolFormat,
    guestRootPath: String,
    languageFlags: Int,
  ): EmbeddedConfiguration {
    return EmbeddedConfiguration(
      protocolVersion = mapVersion(version),
      protocolFormat = mapFormat(format),
      guestRoot = Path(guestRootPath),
      guestLanguages = buildSet {
        for (lang in NativeAppLanguage.entries) {
          // use the native enum value to check the language flag
          if (languageFlags and (1 shl lang.nativeValue()) != 0) add(mapAppLanguage(lang))
        }
      },
    )
  }

  /**
   * Shorthand for constructing a new [EmbeddedAppConfiguration] instance using C values. The [mode] and [language]
   * enums will be automatically converted to their JVM equivalents.
   */
  @JvmStatic fun createAppConfig(
    entrypoint: String,
    language: NativeAppLanguage,
    mode: NativeAppMode,
  ): EmbeddedAppConfiguration {
    return EmbeddedAppConfiguration(
      entrypoint = entrypoint,
      language = mapAppLanguage(language),
      dispatchMode = mapAppMode(mode),
    )
  }

  /** Returns an [ObjectHandle] for a null value, using the global handles factory. */
  @JvmStatic fun nullHandle(): ObjectHandle {
    return ObjectHandles.getGlobal().create(null)
  }

  /** Returns an [ObjectHandle] for a given [value], using the global handles factory. */
  @JvmStatic fun handleFor(value: Any): ObjectHandle? {
    return ObjectHandles.getGlobal().create(value)
  }

  /** Returns the value wrapped by the given [handle] using the global handles factory. */
  @JvmStatic fun <T> unwrapHandle(handle: ObjectHandle?): T {
    return ObjectHandles.getGlobal().get(handle)
  }
}
