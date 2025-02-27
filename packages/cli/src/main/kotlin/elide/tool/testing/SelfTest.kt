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

package elide.tool.testing

import elide.tool.testing.SelfTest.SelfTestContext
import elide.tool.testing.TestContext.TestStage

/**
 * TBD.
 */
abstract class SelfTest : AbstractTest<SelfTestContext>() {
  interface SelfTestContext : TestContext {
    fun notify(stage: TestStage)
    fun assignResult(testResult: TestResult)
  }

  override val name: String get() = this::class.java.name

  override fun testInfo(): TestInfo = TestInfo.of(name, this)
}
