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

/** Static library configuration values. */
object Elide {
  /** Name of the library. */
  const val name = "elide"

  /** Major library version. */
  const val majorVersion = "1.0.0"

  /** Major library version tag. */
  const val versionTag = "alpha10"

  /** Version string for the library. */
  const val version = "$majorVersion-$versionTag"

  /** Latest plugin version. */
  const val pluginVersion = "1.0.0-beta29"

  /** Maven group shared by Elide artifacts. */
  const val group = "dev.elide"

  /** Maven group shared by Elide artifacts. */
  const val substrateGroup = "dev.elide.tools"

  /** Compiler args to include in all Kotlin targets. */
  val compilerArgs = listOf(
    "-Xcontext-receivers",
    "-Xinline-classes",
    "-Xskip-prerelease-check",
    "-Xexpect-actual-classes",

    // Experimental
    "-Xuse-k2",

    // "-Xir-inliner",
    // "-Xenhance-type-parameter-types-to-def-not-null",
    // "-Xtype-enhancement-improvements-strict-mode",
    // "-Xuse-fast-jar-file-system",
    // "-Xenable-builder-inference",
    // "-Xnew-inference",
  )

  /** Compiler args to include in Kotlin JVM targets. */
  val jvmCompilerArgs = compilerArgs.plus(listOf(
    "-no-stdlib",
    "-Xjvm-default=all",
    "-Xjsr305=strict",
    "-Xjvm-enable-preview",
  ))

  /** Compiler args to include in Kotlin JVM targets (beta). */
  val jvmCompilerArgsBeta = jvmCompilerArgs.plus(listOf(
    "-Xallow-unstable-dependencies",
    "-Xemit-jvm-type-annotations",
  ))

  /** Compiler args to include in Kotlin JS targets. */
  val jsCompilerArgs = compilerArgs.plus(listOf(
    "-Xgenerate-dts",
  ))

  /** Compiler args to include in Kotlin MPP targets. */
  val mppCompilerArgs = compilerArgs

  /** Compiler args to include in Kotlin JVM targets which use `kapt`. */
  val kaptCompilerArgs = compilerArgs.plus(listOf(
    "-no-stdlib",
    "-Xallow-unstable-dependencies",
    "-Xemit-jvm-type-annotations",
    "-Xjvm-default=all",
    "-Xjsr305=strict",
  ))

  /** Kotlin SDK and platform version. */
  const val kotlinSdk = "2.0.0"

  /** Kotlin language version. */
  const val kotlinLanguage = "2.0"

  /** Kotlin language version (beta). */
  const val kotlinLanguageBeta = kotlinLanguage

  /** Minimum Java language target. */
  const val javaTargetMinimum = "11"

  /** Maximum Java language target. */
  const val javaTargetMaximum = "21"

  /** Maximum Kotlin JVM language target. */
  const val kotlinJvmTargetMaximum = "21"

  /** Maximum Java language target for Proguard. */
  const val javaTargetProguard = "17"

  /** Sample code modules. */
  val samplesList = listOf(
    ":samples:server:hellocss",
    ":samples:server:helloworld",
    ":samples:fullstack:basic:server",
    ":samples:fullstack:react:server",
    ":samples:fullstack:ssr:server",
    ":samples:fullstack:react-ssr:server",
  )

  /** Kotlin MPP modules. */
  val multiplatformModules = listOf(
    "base",
    "core",
    "http",
    "model",
    "rpc",
    "ssr",
    "test",
    "runtime",
    "embedded",
    "serverless",
    "proto:proto-core",
    "proto:proto-kotlinx",
  )

  /** Server-side only modules. */
  val serverModules = listOf(
    "graalvm",
    "graalvm",
    "graalvm-js",
    "graalvm-jvm",
    "graalvm-kt",
    "graalvm-llvm",
    "graalvm-py",
    "graalvm-rb",
    "graalvm-react",
    "proto:proto-capnp",
    "proto:proto-flatbuffers",
    "proto:proto-protobuf",
    "server",
  )

  /** Packages which are not in use at this time. */
  val disabledPackages = listOf(
    ":packages:ssg",
  )

  /** Browser-side only modules. */
  val frontendModules = listOf(
    "frontend",
    "graalvm-js",
    "graalvm-react",
  )

  /** Modules which should not be reported on for testing.. */
  val noTestModules = listOf(
    "bom",
    "platform",
    "packages",
    "processor",
    "reports",
    "bundler",
    "samples",
    "site",
    "ssg",
    "docs",
    "model",
    "benchmarks",
    "frontend",
    "graalvm-js",
    "graalvm-react",
    "test",
  )

  /** All library modules which are published. */
  val publishedModules = listOf(
    // Library Packages
    "base",
    "core",
    "embedded",
    "frontend",
    "graalvm",
    "graalvm-js",
    "graalvm-jvm",
    "graalvm-kt",
    "graalvm-llvm",
    "graalvm-py",
    "graalvm-rb",
    "graalvm-react",
    "http",
    "model",
    "proto:proto-core",
    "proto:proto-capnp",
    "proto:proto-flatbuffers",
    "proto:proto-kotlinx",
    "proto:proto-protobuf",
    "rpc",
    "server",
    "serverless",
    "ssg",
    "ssr",
    "test",
    "runtime",
  ).map { ":packages:$it" }.plus(listOf(
    // Tools
    "processor",
  ).map { ":tools:$it" }).filter {
    !disabledPackages.contains(it)
  }

  /** All subproject modules which are published. */
  val publishedSubprojects = listOf(
    "bom",
    "compiler-util",
    "redakt",
  ).map { "substrate:$it" }

  /** All publishable targets. */
  val allPublishTargets = publishedModules.plus(
    publishedSubprojects
  )
}
