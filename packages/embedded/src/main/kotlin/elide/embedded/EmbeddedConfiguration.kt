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

package elide.embedded

import java.nio.file.Path
import elide.embedded.EmbeddedGuestLanguage

/**
 * Describes the configuration used by the [ElideEmbedded] runtime during initialization.
 *
 * This configuration defines the data exchange format used for serialized invocations and other dispatch-related
 * structures, and can be used to alter the runtime startup process using feature flags.
 */
public data class EmbeddedConfiguration(
  /**
   * Defines the version of the dispatch protocol used by the runtime.
   *
   * Runtime operations that make use of serialized data structures must adhere to the specified version, even if the
   * protocol itself provides backwards-compatibility guarantees.
   */
  val protocolVersion: EmbeddedProtocolVersion,

  /**
   * Defines the binary firmat of the dispatch protocol used by the runtime.
   *
   * Runtime operations that make use of serialized data structures must adhere to the specified format, even if the
   * protocol itself provides backwards-compatibility guarantees.
   */
  val protocolFormat: EmbeddedProtocolFormat,

  /**
   * Specifies the root directory in which the guest applications are placed. During dispatch, guest entrypoints will
   * be resolved relative to this path, according to their application ID.
   */
  val guestRoot: Path,

  /**
   * Defines the set of languages allowed for guest applications. The runtime must validate that the requested
   * languages are supported during initialization, and must verify applications at the time of registration.
   */
  val guestLanguages: Set<EmbeddedGuestLanguage>,
)
