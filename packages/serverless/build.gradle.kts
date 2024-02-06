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


import elide.internal.conventions.kotlin.*
import elide.internal.conventions.publishing.publish

plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  
  alias(libs.plugins.micronaut.library)
  alias(libs.plugins.micronaut.graalvm)

  id(libs.plugins.ksp.get().pluginId)
  id("elide.internal.conventions")
}

elide {
  publishing {
    id = "server"
    name = "Elide for Serverless"
    description = "Serverless dispatch interfaces for Elide applications."

    publish("maven") {
      from(components["kotlin"])
    }
  }

  kotlin {
    target = KotlinTarget.Embedded
    explicitApi = true
  }

  java {
    configureModularity = false
  }
}

group = "dev.elide"
version = rootProject.version as String

micronaut {
  version = libs.versions.micronaut.lib.get()

  processing {
    incremental = true
    annotations.addAll(listOf(
      "elide.serverless",
      "elide.serverless.*",
      "elide.serverless.annotations",
      "elide.serverless.annotations.*",
    ))
  }
}

dependencies {
  common {
    api(projects.packages.core)
    api(projects.packages.base)
    api(projects.packages.http)
    api(projects.packages.ssr)
  }

  // Testing
  commonTest {
    api(projects.packages.test)
    api(kotlin("test"))
  }
}