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

package dev.elide.cli.tooling.api

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable @JvmRecord public data class DiagnosticTimings(
  public val start: Long = 0,
  public val end: Long = 0,
)

@Serializable @JvmRecord public data class DiagnosticSuite(
  public val maxSeverity: Severity,
  public val notes: List<DiagnosticNote>,
  public val timings: DiagnosticTimings? = null,
)

public object Diagnostics {
  @OptIn(ExperimentalSerializationApi::class)
  private val diagnosticJson: Json by lazy {
    Json {
      isLenient = true
      allowComments = true
      allowTrailingComma = true
      ignoreUnknownKeys = true
    }
  }

  @JvmStatic public fun empty(): DiagnosticSuite = DiagnosticSuite(Severity.INFO, emptyList())

  @JvmStatic public fun fromJson(json: String): DiagnosticSuite = diagnosticJson.decodeFromString(
    DiagnosticSuite.serializer(),
    json,
  )
}