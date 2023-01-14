@file:Suppress(
  "UnstableApiUsage",
  "unused",
  "DSL_SCOPE_VIOLATION",
  "UNUSED_VARIABLE",
)

import io.netifi.flatbuffers.plugin.tasks.FlatBuffers

plugins {
  `maven-publish`
  distribution
  signing
  id("dev.elide.build.kotlin")
  alias(libs.plugins.flatbuffers)
}

group = "dev.elide"
version = rootProject.version as String

val javaLanguageVersion = project.properties["versions.java.language"] as String
val javaLanguageTarget = project.properties["versions.java.target"] as String

sourceSets {
  /**
   * Variant: Flatbuffers
   */
  val main by getting
  val test by getting
}

configurations {
  // `flatInternal` uses the flatbuffers implementation only, rather than the full cruft of Protocol Buffers non-lite.
  create("flatInternal") {
    isCanBeResolved = false
    isCanBeConsumed = true

    extendsFrom(configurations["implementation"])
  }
}

kotlin {
  target.compilations.all {
    kotlinOptions {
      jvmTarget = javaLanguageTarget
      javaParameters = true
      apiVersion = Elide.kotlinLanguage
      languageVersion = Elide.kotlinLanguage
      allWarningsAsErrors = false
      freeCompilerArgs = Elide.jvmCompilerArgsBeta.plus(listOf(
        // do not warn for generated code
        "-nowarn"
      ))
    }
  }

  // force -Werror to be off
  afterEvaluate {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
      kotlinOptions.allWarningsAsErrors = false
    }
  }
}

flatbuffers {
  language = "kotlin"
}

tasks.withType<JavaCompile>().configureEach {
  sourceCompatibility = javaLanguageTarget
  targetCompatibility = javaLanguageTarget
  options.isFork = true
  options.isIncremental = true
  options.isWarnings = false
}

tasks {
  test {
    useJUnitPlatform()
  }

  /**
   * Variant: Flatbuffers
   */
  val compileFlatbuffers by creating(FlatBuffers::class) {
    description = "Generate Flatbuffers code for Kotlin/JVM"
    inputDir = file("${rootProject.projectDir}/proto")
    outputDir = file("$projectDir/src/main/flat")
  }

  artifacts {
    archives(jar)
    add("flatInternal", jar)
  }
}

publishing {
  publications {
    /** Publication: Flatbuffers */
    create<MavenPublication>("maven") {
      artifactId = "proto-flatbuffers"
      from(components["kotlin"])

      pom {
        name.set("Elide Protocol: Flatbuffers")
        description.set("Elide protocol implementation for Flatbuffers")
      }
    }
  }
}

dependencies {
  // Common
  api(libs.kotlinx.datetime)
  implementation(kotlin("stdlib"))
  implementation(kotlin("stdlib-jdk8"))
  implementation(project(":packages:core"))
  implementation(project(":packages:base"))
  testImplementation(project(":packages:test"))
  testImplementation(libs.truth)
  testImplementation(libs.truth.java8)

  // Variant: Flatbuffers
  api(project(":packages:proto:proto-core"))
  implementation(kotlin("stdlib"))
  implementation(kotlin("stdlib-jdk8"))
  implementation(libs.flatbuffers.java.core)
  testImplementation(project(":packages:proto:proto-core", configuration = "testBase"))
}