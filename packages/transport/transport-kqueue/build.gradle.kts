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
@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.konan.target.HostManager
import java.nio.file.Path

plugins {
  `cpp-library`
  `java-library`
  id("elide.internal.conventions")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

library {
  targetMachines = listOf(
    machines.macOS.x86_64,
    machines.macOS.architecture("arm64"),
  )
}

elide {
  publishing {
    id = "transport-kqueue"
    name = "Elide Transport: KQueue"
    description = "Packages native KQueue support for Elide/Netty."
  }
}

val jvmApi: Configuration by configurations.creating {
  isCanBeResolved = true
  isCanBeConsumed = false
}
val apiElements: Configuration by configurations.getting { extendsFrom(jvmApi) }
val compileClasspath: Configuration by configurations.getting { extendsFrom(jvmApi) }

dependencies {
  jvmApi(libs.netty.transport.native.unix)
  jvmApi(libs.netty.transport)
  jvmApi(mn.netty.buffer)
  jvmApi(mn.netty.common)
  implementation(projects.packages.transport.transportCommon)
}

val jdkHome: String = System.getenv("GRAALVM_HOME")?.ifBlank { null }
  ?: System.getenv("JAVA_HOME")?.ifBlank { null }
  ?: System.getProperty("java.home")

val jdkHomePath: Path = Path.of(jdkHome)
val jdkIncludePath: Path = jdkHomePath.resolve("include")
val jdkNativeIncludePath: Path = when {
  HostManager.hostIsMac -> jdkIncludePath.resolve("darwin")
  HostManager.hostIsLinux -> jdkIncludePath.resolve("linux")
  else -> error("Unsupported OS for kqueue")
}

tasks.withType(CppCompile::class.java).configureEach {
  group = "build"
  description = "Compile shared library"
  onlyIf { HostManager.hostIsMac }
  source.from(layout.projectDirectory.dir("src/main/cpp").asFileTree.matching { include("**/*.c") })

  macros["NETTY_BUILD_STATIC"] = "1"
  macros["NETTY_BUILD_GRAALVM"] = "1"

  compilerArgs.addAll(listOf(
    "-x", "c",
    "-target", "arm64-apple-macos11",
    "-O3",
    "-Werror",
    "-fno-omit-frame-pointer",
    "-Wunused-variable",
    "-fvisibility=hidden",
    "-mmacosx-version-min=11.0",
    "-I$jdkIncludePath",
    "-I$jdkNativeIncludePath",
  ))
}

tasks.withType(LinkSharedLibrary::class.java).configureEach {
  group = "build"
  description = "Link shared library"
  onlyIf { HostManager.hostIsMac }
  linkerArgs.addAll(listOf(
    "-Wl,-platform_version,macos,11.0,11.0",
  ))
}

tasks.processResources {
  val resources = layout.projectDirectory.dir("src/main/resources")
  val libs = layout.buildDirectory.dir("lib/main/release")
  val compiles = tasks.withType(CppCompile::class)
  val linkages = tasks.withType(LinkSharedLibrary::class)
  val stripped = tasks.withType(StripSymbols::class)

  dependsOn(compiles, linkages, stripped)

  inputs.dir(resources)
  inputs.dir(libs)

  from("build/lib/main/release") {
    exclude("**/stripped/**")
    into("META-INF/native/")
  }
}