/*
 * Copyright (c) 2023 Elide Ventures, LLC.
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

@file:Suppress(
  "UnstableApiUsage",
  "unused",
  "DSL_SCOPE_VIOLATION",
)

plugins {
  id("io.micronaut.library")
  id("io.micronaut.graalvm")

  kotlin("kapt")
  kotlin("plugin.allopen")
  id("dev.elide.build.native.lib")
}

group = "dev.elide"
version = rootProject.version as String

val encloseSdk = false

kotlin {
  explicitApi()
}

dependencies {
  implementation(libs.kotlinx.coroutines.core)
  implementation(projects.packages.graalvm)
  implementation(projects.packages.graalvmJvm)
  implementation(libs.kotlin.scripting.common)
  implementation(libs.kotlin.scripting.dependencies)
  implementation(libs.kotlin.scripting.dependencies.maven)
  implementation(libs.kotlin.scripting.jvm)
  implementation(libs.kotlin.scripting.jvm.host)
  implementation(libs.kotlin.scripting.jvm.engine)
  compileOnly(libs.graalvm.espresso.polyglot)
  compileOnly(libs.graalvm.espresso.hotswap)
  if (encloseSdk) {
    compileOnly(libs.graalvm.sdk)
    compileOnly(libs.graalvm.truffle.api)
  }

  // Testing
  testImplementation(projects.packages.test)
}

val buildDocs = project.properties["buildDocs"] == "true"
publishing {
  publications.withType<MavenPublication> {
    artifactId = artifactId.replace("graalvm-kt", "elide-graalvm-kt")

    pom {
      name = "Elide Kotlin for GraalVM"
      url = "https://elide.dev"
      description = "Integration package with GraalVM, Espresso, and the Kotlin compiler."

      licenses {
        license {
          name = "MIT License"
          url = "https://github.com/elide-dev/elide/blob/v3/LICENSE"
        }
      }
      developers {
        developer {
          id = "sgammon"
          name = "Sam Gammon"
          email = "samuel.gammon@gmail.com"
        }
      }
      scm {
        url = "https://github.com/elide-dev/elide"
      }
    }
  }
}

if (buildDocs) {
  listOf("dokkaJavadoc").forEach {
    tasks.named(it).configure {
      dependsOn("kaptKotlin")
    }
  }
}