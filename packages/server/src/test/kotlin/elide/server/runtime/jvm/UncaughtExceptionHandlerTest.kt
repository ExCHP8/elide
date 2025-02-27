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

package elide.server.runtime.jvm

import io.micronaut.context.annotation.Replaces
import io.micronaut.context.annotation.Requires
import org.junit.jupiter.api.assertDoesNotThrow
import java.util.function.Supplier
import jakarta.inject.Singleton
import kotlin.test.Test
import elide.annotations.Factory
import elide.annotations.Inject
import elide.runtime.Logger
import elide.runtime.Logging
import elide.testing.annotations.TestCase

@Factory
@Requires(env = ["test"])
@Replaces(UncaughtExceptionHandler.UncaughtExceptionHandlerLoggingProvider::class)
class TestUncaughtExceptionHandlerLoggingProvider : UncaughtExceptionHandler.UncaughtExceptionHandlerLoggingProvider() {
  @Singleton override fun get(): Logger {
    return Logging.named("uncaught-exception-handler-test")
  }
}

/** Test for the default [UncaughtExceptionHandler]. */
@TestCase class UncaughtExceptionHandlerTest {
  @Inject internal lateinit var handler: UncaughtExceptionHandler

  @Test fun testLogUncaughtException() {
    assertDoesNotThrow {
      handler.uncaughtException(
        Thread.currentThread(),
        IllegalStateException("sample exception (not real)")
      )
    }
  }
}
