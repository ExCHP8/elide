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
@file:Suppress(
  "ThrowsCount",
  "TooGenericExceptionCaught",
  "TooManyFunctions",
  "CyclomaticComplexMethod",
  "LargeClass",
  "LongMethod",
)

package elide.runtime.gvm.internals.node.asserts

import io.micronaut.context.annotation.Factory
import org.graalvm.polyglot.Value
import org.graalvm.polyglot.Value.asValue
import org.graalvm.polyglot.proxy.ProxyArray
import org.graalvm.polyglot.proxy.ProxyHashMap
import org.graalvm.polyglot.proxy.ProxyObject
import java.math.BigInteger
import java.util.Optional
import java.util.Optional.empty
import java.util.Optional.of
import java.util.Optional.ofNullable
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import java.util.function.Function
import kotlin.jvm.optionals.getOrNull
import elide.annotations.Singleton
import elide.runtime.gvm.internals.intrinsics.Intrinsic
import elide.runtime.gvm.internals.intrinsics.js.AbstractNodeBuiltinModule
import elide.runtime.gvm.internals.intrinsics.js.JsSymbol.JsSymbols.asJsSymbol
import elide.runtime.intrinsics.GuestIntrinsic.MutableIntrinsicBindings
import elide.runtime.intrinsics.js.JsPromise
import elide.runtime.intrinsics.js.err.JsException
import elide.runtime.intrinsics.js.node.AssertAPI
import elide.runtime.intrinsics.js.node.asserts.AssertionError
import elide.vm.annotations.Polyglot

// Symbol where the internal module implementation is installed.
private const val ASSERT_MODULE_SYMBOL: String = "__Elide_node_assert__"

// Installs the Node assert module into the intrinsic bindings.
@Intrinsic @Factory internal class NodeAssertModule : AbstractNodeBuiltinModule() {
  // Provide a compliant instance of the OS API to the DI context.
  @Singleton fun provide(): AssertAPI = NodeAssert.obtain()

  override fun install(bindings: MutableIntrinsicBindings) {
    bindings[ASSERT_MODULE_SYMBOL.asJsSymbol()] = provide()
  }
}

// Implements Node's `AssertionError` type, which is thrown for assertion failures.
internal class NodeAssertionError (
  private val messageOrErr: Any? = null,
  private val isGenerated: Boolean = false,
  private val actualValue: Optional<Any> = empty(),
  private val expectedValue: Optional<Any> = empty(),
  private val operatorValue: String? = null
) : Throwable(messageOrErr as? String), AssertionError, JsException {
  override val name: String get() = super<AssertionError>.name
  override val generatedMessage: Boolean get() = isGenerated
  override val actual: Any? get() = actualValue.getOrNull()
  override val expected: Any? get() = expectedValue.getOrNull()
  override val operator: String? get() = operatorValue

  companion object {
    private const val DEFAULT_MESSAGE: String = "Failed assertion"
    private const val DEFAULT_OPERATOR: String = "=="

    @JvmStatic @JvmOverloads fun of(
      message: Any? = null,
      isGenerated: Boolean = false,
      actualValue: Optional<Any> = empty(),
      expectedValue: Optional<Any> = empty(),
      operatorValue: String? = null
    ): NodeAssertionError = NodeAssertionError(
      message ?: DEFAULT_MESSAGE,
      isGenerated,
      actualValue,
      expectedValue,
      operatorValue ?: DEFAULT_OPERATOR
    )
  }

  override val message: String? get() = when (messageOrErr) {
    null -> null
    is String -> messageOrErr
    else -> messageOrErr.toString()
  }
}

// Expectation message for a failure from `ok` or `assert`.
private const val OK_EXPECTATION = "Expected value to be truthy"

private fun assertionError(
  message: Any?,
  isGenerated: Boolean = false,
  actualValue: Optional<Any>? = null,
  expectedValue: Optional<Any>? = null,
  operatorValue: String? = null
): NodeAssertionError = NodeAssertionError.of(
  message,
  isGenerated,
  actualValue ?: empty(),
  expectedValue ?: empty(),
  operatorValue
)

internal class NodeAssert : AssertAPI {
  companion object {
    private val SINGLETON = NodeAssert()
    @JvmStatic fun obtain(): AssertAPI = SINGLETON
  }

  // Assert that a given `value` is truthy (or falsy, if `reverse` is true).
  private fun checkTruthy(reverse: Boolean, value: Any?, message: Any?): Boolean {
    return when (value) {
      null -> throw assertionError(message ?: OK_EXPECTATION)
      is Boolean -> value
      is String -> value.isNotEmpty()
      is Array<*> -> value.isNotEmpty()
      is Collection<*> -> value.isNotEmpty()
      is Map<*, *> -> value.isNotEmpty()
      is Iterator<*> -> value.hasNext()
      is Unit -> throw assertionError(message ?: OK_EXPECTATION)
      is Number -> when (value) {
        is Short -> value > 0
        is Int -> value > 0
        is Long -> value > 0L
        is BigInteger -> value > BigInteger.ZERO
        is Float -> value > 0.0f
        is Double -> value > 0.0
        else -> throw assertionError(message ?: OK_EXPECTATION)
      }

      is ProxyArray -> value.size > 0L
      is ProxyHashMap -> value.hashSize > 0L

      is ProxyObject -> value.memberKeys.let { keys ->
        when (keys) {
          is ProxyArray -> keys.size > 0L
          is Array<*> -> keys.size > 0
          is Collection<*> -> keys.size > 0
          else -> throw assertionError(message ?: OK_EXPECTATION)
        }
      }

      is Value -> when {
        value.isBoolean -> value.asBoolean()
        value.isString -> value.asString().isNotEmpty()
        value.isNull -> throw assertionError(message ?: OK_EXPECTATION)

        value.isNumber -> when {
          value.fitsInShort() -> value.asShort() > 0
          value.fitsInInt() -> value.asInt() > 0
          value.fitsInLong() -> value.asLong() > 0L
          value.fitsInBigInteger() -> value.asBigInteger() > BigInteger.ZERO
          value.fitsInFloat() -> value.asFloat() > 0.0f
          value.fitsInDouble() -> value.asDouble() > 0.0
          else -> throw assertionError(message ?: OK_EXPECTATION)
        }

        // Non-empty objects should pass
        value.isProxyObject -> value.memberKeys.isNotEmpty()

        // All of these types are automatically truthy.
        value.hasBufferElements() ||
        value.isDate ||
        value.isTime ||
        value.isTimeZone ||
        value.isInstant ||
        value.isDuration ||
        value.isNativePointer ||
        value.isIterator ||
        value.isMetaObject -> true

        value.isHostObject -> checkTruthy(
          reverse,
          value.asHostObject<Any>(),
          message,
        )

        else -> error("Unrecognized polyglot value type: $value (${value.metaObject} / ${value::class.java.name})")
      }

      else -> error("Unrecognized value type: $value")

    }.let { condition ->
      if (reverse) {
        if (condition) throw assertionError(message ?: OK_EXPECTATION)
      } else {
        if (!condition) throw assertionError(message ?: OK_EXPECTATION)
      }
      condition
    }
  }

  @Polyglot override fun ok(value: Any?, message: Any?) {
    checkTruthy(false, value, message)
  }

  @Polyglot override fun notOk(value: Any?, message: Any?) {
    checkTruthy(true, value, message)
  }

  @Polyglot override fun fail(message: String?) {
    throw assertionError(message)
  }

  @Polyglot override fun fail(actual: Any?, expected: Any?, message: String?, operator: String?, stackStartFn: Any?) {
    throw assertionError(
      message,
      actualValue = ofNullable(actual),
      expectedValue = ofNullable(expected),
      operatorValue = operator,
    )
  }

  @Polyglot override fun ifError(value: Any?) = when (value) {
    null -> {}
    is Value -> when {
      value.isNull -> {}
      value.isString -> value.asString().isEmpty().let {
        if (!it) throw assertionError("ifError got unwanted exception: $value", actualValue = of(value))
      }
      else -> throw assertionError("ifError got unwanted exception: $value", actualValue = of(value))
    }
    else -> throw assertionError("ifError got unwanted exception: $value", actualValue = of(value))
  }

  @Polyglot override fun assert(value: Any?, message: String?) {
    checkTruthy(false, value, message)
  }

  @Polyglot override fun equal(actual: Any?, expected: Any?, message: String?) {
    when {
      actual == null && expected == null -> {}
      actual == null || expected == null ->
        throw assertionError(message, actualValue = empty(), expectedValue = ofNullable(expected))

      actual is Value && expected is Value -> when {
        actual.isNumber && expected.isNumber -> when {
          expected.fitsInShort() && actual.fitsInShort() -> {
            if (actual.asShort() != expected.asShort()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          expected.fitsInInt() && actual.fitsInInt() -> {
            if (actual.asInt() != expected.asInt()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          expected.fitsInLong() && actual.fitsInLong() -> {
            if (actual.asLong() != expected.asLong()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          expected.fitsInFloat() && actual.fitsInFloat() -> {
            if (actual.asFloat() != expected.asFloat()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          expected.fitsInDouble() && actual.fitsInDouble() -> {
            if (actual.asDouble() != expected.asDouble()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          expected.fitsInBigInteger() && actual.fitsInBigInteger() -> {
            if (actual.asBigInteger() != expected.asBigInteger()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          else -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }
        }

        actual.isHostObject && expected.isHostObject -> {
          if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }
        }

        else -> if (actual != expected) {
          throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
        }
      }

      actual !is Value && expected !is Value -> when (actual) {
        is Short -> when (expected) {
          is Short -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Int, is Long, is Float, is Double -> {
            // cast to the larger type and compare
            if (actual.toLong() != expected) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }
        }

        is Int -> when (expected) {
          is Int -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Short -> if (actual != expected.toInt()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Long, is Float, is Double -> {
            // cast to the larger type and compare
            if (actual.toLong() != expected) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }
        }

        is Long -> when (expected) {
          is Long -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Short -> if (actual != expected.toLong()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Int -> if (actual != expected.toLong()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Float, is Double -> {
            // cast to the larger type and compare
            if (actual.toDouble() != expected) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          is BigInteger -> {
            if (actual.toBigInteger() != expected) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          else -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }
        }

        is Float -> when (expected) {
          is Float -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Short -> if (actual != expected.toFloat()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Int -> if (actual != expected.toFloat()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Long -> if (actual != expected.toFloat()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Double -> {
            if (actual.toDouble() != expected) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          else -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }
        }

        is Double -> when (expected) {
          is Double -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Short -> if (actual != expected.toDouble()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Int -> if (actual != expected.toDouble()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Long -> if (actual != expected.toDouble()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Float -> {
            if (actual.toFloat() != expected) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          else -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }
        }

        else -> if (actual != expected) {
          throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
        }
      }

      else -> error("Incomparable types: actual=$actual, expected=$expected, message=$message")
    }
  }

  @Polyglot override fun strict(actual: Any?, expected: Any?, message: String?) {
    when {
      actual == null && expected == null -> {}
      actual == null || expected == null ->
        throw assertionError(message, actualValue = empty(), expectedValue = ofNullable(expected))

      // two guest objects
      actual is Value && expected is Value -> when {
        actual.isNull && expected.isNull -> {}
        actual.isNull || expected.isNull ->
          throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))

        actual.isBoolean && expected.isBoolean -> if (actual.asBoolean() != expected.asBoolean()) {
          throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
        }

        (actual.isBoolean && !expected.isBoolean) || (expected.isBoolean && !actual.isBoolean) ->
          throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))

        actual.isString && expected.isString -> if (actual.asString() != expected.asString()) {
          throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
        }

        (actual.isString && !expected.isString) || (expected.isString && !actual.isString) -> {
          throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
        }

        actual.isNumber -> when {
          actual.fitsInShort() && expected.fitsInShort() -> {
            if (actual.asShort() != expected.asShort()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          actual.fitsInInt() && expected.fitsInInt() -> {
            if (actual.asInt() != expected.asInt()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          actual.fitsInLong() && expected.fitsInLong() -> {
            if (actual.asLong() != expected.asLong()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          actual.fitsInFloat() && expected.fitsInFloat() -> {
            if (actual.asFloat() != expected.asFloat()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          actual.fitsInDouble() && expected.fitsInDouble() -> {
            if (actual.asDouble() != expected.asDouble()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          actual.fitsInBigInteger() && expected.fitsInBigInteger() -> {
            if (actual.asBigInteger() != expected.asBigInteger()) {
              throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
            }
          }

          else -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }
        }

        else -> if (actual != expected) {
          throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
        }
      }

      // two host objects
      actual !is Value && expected !is Value -> when {
        actual is Short -> when (expected) {
          is Short -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Int -> if (actual.toInt() != expected.toInt()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Long -> if (actual.toLong() != expected.toLong()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Float -> if (actual.toFloat() != expected.toFloat()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Double -> if (actual.toDouble() != expected.toDouble()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          else -> throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
        }

        actual is Int -> when (expected) {
          is Int -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Short -> if (actual != expected.toInt()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Long -> if (actual != expected.toInt()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Float -> if (actual != expected.toInt()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Double -> if (actual != expected.toInt()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          else -> throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
        }

        actual is Long -> when (expected) {
          is Long -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Short -> if (actual != expected.toLong()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Int -> if (actual != expected.toLong()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Float -> if (actual != expected.toLong()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Double -> if (actual != expected.toLong()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          else -> throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
        }

        actual is Float -> when (expected) {
          is Float -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Short -> if (actual != expected.toFloat()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Int -> if (actual != expected.toFloat()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Long -> if (actual != expected.toFloat()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Double -> if (actual != expected.toFloat()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          else -> throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
        }

        actual is Double -> when (expected) {
          is Double -> if (actual != expected) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Short -> if (actual != expected.toDouble()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Int -> if (actual != expected.toDouble()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Long -> if (actual != expected.toDouble()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          is Float -> if (actual != expected.toDouble()) {
            throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
          }

          else -> throw assertionError(message, actualValue = ofNullable(actual), expectedValue = ofNullable(expected))
        }

        else -> (expected == actual).let {
          if (!it) throw assertionError(
            message,
            actualValue = ofNullable(actual),
            expectedValue = ofNullable(expected),
          )
        }
      }

      // otherwise, guest-ify if needed
      else -> strict(
        actual = if (actual is Value) actual else asValue(actual),
        expected = if (expected is Value) expected else asValue(expected),
        message = message,
      )
    }
  }

  @Polyglot override fun notEqual(actual: Any?, expected: Any?, message: String?) {
    TODO("Not yet implemented: `assert.notEqual`")
  }

  @Polyglot override fun deepEqual(actual: Any?, expected: Any?, message: String?) {
    TODO("Not yet implemented: `assert.deepEqual`")
  }

  @Polyglot override fun notDeepEqual(actual: Any?, expected: Any?, message: String?) {
    TODO("Not yet implemented: `assert.notDeepEqual`")
  }

  @Polyglot override fun deepStrictEqual(actual: Any?, expected: Any?, message: String?) {
    TODO("Not yet implemented: `assert.deepStrictEqual`")
  }

  @Polyglot override fun notDeepStrictEqual(actual: Any?, expected: Any?, message: String?) {
    TODO("Not yet implemented: `assert.notDeepStrictEqual`")
  }

  @Polyglot override fun match(string: String, regexp: Regex, message: String?) {
    if (!regexp.matches(string)) {
      throw assertionError(message, actualValue = of(string), expectedValue = of(regexp))
    }
  }

  private fun polyglotRegexMatch(shouldMatch: Boolean, string: String, regexp: Value, message: String?) {
    if (!regexp.hasMember("exec")) throw assertionError("Expected `RegExp` with `exec`")
    val match = regexp.getMember("exec")
    if (match.isNull) error("Unexpected missing `exec` function")
    if (!match.canExecute()) throw assertionError("Cannot execute `RegExp.exec`")
    val doesMatch = !match.execute(string).isNull
    if (shouldMatch != doesMatch) {
      throw assertionError(message, actualValue = of(string), expectedValue = of(regexp))
    }
  }

  @Polyglot override fun match(string: String, regexp: Value, message: String?) {
    polyglotRegexMatch(true, string, regexp, message)
  }

  @Polyglot override fun doesNotMatch(string: String, regexp: Regex, message: String?) {
    if (regexp.matches(string)) {
      throw assertionError(message, actualValue = of(string), expectedValue = of(regexp))
    }
  }

  @Polyglot override fun doesNotMatch(string: String, regexp: Value, message: String?) {
    polyglotRegexMatch(false, string, regexp, message)
  }

  private fun runWithCapturedExceptions(expectThrows: Boolean, fn: Any?, error: Any?, message: String?) {
    val didThrow = AtomicBoolean(false)
    val exc = AtomicReference<Throwable>(null)

    when (fn) {
      is Function<*, *> -> try {
        @Suppress("UNCHECKED_CAST")
        (fn as Function<Any?, Any?>).apply(null)
      } catch (e: Throwable) {
        didThrow.set(true)
        exc.set(e)
      }

      is Value -> {
        if (!fn.canExecute()) {
          throw assertionError("Expected a function, but got: $fn (not executable)")
        }
        try {
          fn.execute()
        } catch (e: Throwable) {
          didThrow.set(true)
          exc.set(e)
        }
      }

      else -> throw assertionError("Expected a function, but got: $fn")
    }

    if (didThrow.get() && !expectThrows) {
      if (error != null) {
        val err = exc.get()
        if (err::class.java == error) {
          throw assertionError(message, actualValue = of(err), expectedValue = of(err))
        }
      }

      throw assertionError(message, actualValue = of(exc.get()))
    } else if (!didThrow.get() && expectThrows) {
      throw assertionError(message, actualValue = ofNullable(null), expectedValue = of("an error"))
    }
  }

  private fun runAsyncWithCapturedExceptions(expectRejects: Boolean, fn: Any?, error: Any?, message: String?) {
    val didReject = AtomicBoolean(false)
    val exc = AtomicReference<Throwable>(null)

    val out = when (fn) {
      is Function<*, *> -> try {
        @Suppress("UNCHECKED_CAST")
        (fn as Function<Any?, Any?>).apply(null)
      } catch (e: Throwable) {
        throw assertionError(
          "Expected async function to execute and produce a Promise, but it threw instead",
          actualValue = of(e),
        )
      }

      is Value -> {
        if (!fn.canExecute()) {
          throw assertionError("Expected a function, but got: $fn (not executable)")
        }
        try {
          fn.execute()
        } catch (e: Throwable) {
          throw assertionError(
            "Expected async foreign function to execute and produce a Promise, but it threw instead",
            actualValue = of(e),
          )
        }
      }

      else -> throw assertionError("Expected a function, but got: $fn")
    }

    // `out` should be a promise
    when {
      out is JsPromise<*> -> {
        out.then({}, { didReject.set(true) })
      }

      out is Value -> {
        if (!out.hasMember("then")) {
          throw assertionError("Expected a Promise, but got: $out")
        }
        val then = out.getMember("then")
        if (!then.canExecute()) {
          throw assertionError("Expected a Promise, but got: $out")
        }
        try {
          then.execute()
        } catch (e: Throwable) {
          didReject.set(true)
          exc.set(e)
        }
      }

      else -> throw assertionError("Expected a Promise, but got: $out")
    }

    if (didReject.get() && !expectRejects) {
      if (error != null) {
        throw assertionError(message, actualValue = ofNullable(null), expectedValue = of("an error"))
      }
    } else if (!didReject.get() && expectRejects) {
      throw assertionError(message, actualValue = ofNullable(null), expectedValue = of("an error"))
    }
  }

  @Polyglot override fun throws(error: Any?, message: String?, fn: () -> Unit) {
    try {
      fn()
    } catch (e: Throwable) {
      if (error != null) {
        if (e::class.java != error) {
          throw assertionError(message, actualValue = of(e), expectedValue = of(e))
        }
      }
      return
    }
    throw assertionError(
      "Expected fn to throw, but no error was thrown",
      actualValue = ofNullable(null),
      expectedValue = of("an error")
    )
  }

  @Polyglot override fun throws(fn: Any, error: Any?, message: String?) {
    runWithCapturedExceptions(
      true,
      fn,
      error,
      message ?: "Expected fn to throw, but no error was thrown",
    )
  }

  @Polyglot override fun doesNotThrow(fn: Any, error: Any?, message: String?) {
    runWithCapturedExceptions(
      false,
      fn,
      error,
      message ?: "Expected fn not to throw, but an error was thrown",
    )
  }

  @Polyglot override fun doesNotThrow(error: Any?, message: String?, fn: () -> Unit) {
    try {
      fn()
    } catch (e: Throwable) {
      if (error != null) {
        if (e::class.java == error) {
          throw assertionError(message, actualValue = of(e), expectedValue = of(e))
        }
      }
      throw assertionError(message, actualValue = of(e))
    }
  }

  @Polyglot override fun rejects(asyncFn: Any, error: Any?, message: String?) {
    runAsyncWithCapturedExceptions(true, asyncFn, error, message)
  }

  @Polyglot override fun doesNotReject(asyncFn: Any, error: Any?, message: String?) {
    runAsyncWithCapturedExceptions(false, asyncFn, error, message)
  }
}