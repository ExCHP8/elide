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

import elide.internal.conventions.kotlin.KotlinTarget
import elide.internal.conventions.native.NativeTarget
import elide.internal.conventions.publishing.publish

plugins {
  alias(libs.plugins.micronaut.graalvm)

  kotlin("jvm")
  kotlin("kapt")
  kotlin("plugin.allopen")

  alias(libs.plugins.elide.conventions)
}

elide {
  publishing {
    id = "graalvm-kt"
    name = "Elide Kotlin for GraalVM"
    description = "Integration package with GraalVM, Espresso, and the Kotlin compiler."

    publish("jvm") {
      from(components["kotlin"])
    }
  }

  kotlin {
    target = KotlinTarget.JVM
    explicitApi = true
  }

  java {
    // disable module-info processing (not present)
    configureModularity = false
  }

  native {
    target = NativeTarget.LIB
  }
}

dependencies {
  api(projects.packages.engine)
  implementation(projects.packages.graalvmJvm)

  implementation(libs.kotlin.scripting.jvm)
  implementation(libs.kotlin.scripting.jvm.host)
  implementation(libs.kotlin.compiler.embedded)

  // Testing
  testImplementation(projects.packages.test)
  testImplementation(projects.packages.graalvm)
  testImplementation(project(":packages:graalvm", configuration = "testBase"))
}
