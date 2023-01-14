package elide.runtime.gvm.internals.intrinsics.js.struct.map

import elide.runtime.intrinsics.js.MapLike
import elide.testing.annotations.Test
import elide.testing.annotations.TestCase
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/** Tests for the baseline (non-mutable) [JsMultiMap] implementation. */
@TestCase internal class JsMultiMapTest : AbstractJsMapTest<JsMultiMap<String, Any?>>() {
  /** @inheritDoc */
  override fun empty(): JsMultiMap<String, Any?> = JsMultiMap.empty()

  /** @inheritDoc */
  override fun spawnGeneric(pairs: Collection<Pair<String, Any?>>): JsMultiMap<String, Any?> =
    JsMultiMap.fromPairs(pairs)

  /** @inheritDoc */
  override fun spawnFromMap(map: Map<String, Any?>): JsMultiMap<String, Any?> =
    JsMultiMap.copyOf(map)

  /** @inheritDoc */
  override fun spawnFromEntries(entries: Collection<Map.Entry<String, Any?>>): JsMultiMap<String, Any?> =
    JsMultiMap.fromEntries(entries)

  /** @inheritDoc */
  override fun spawnFromJsEntries(entries: Collection<MapLike.Entry<String, Any?>>): JsMultiMap<String, Any?> =
    JsMultiMap.from(entries)

  /** @inheritDoc */
  override fun spawnUnbounded(pairs: Iterable<Pair<String, Any?>>): JsMultiMap<String, Any?> =
    JsMultiMap.unboundedPairs(pairs)

  /** @inheritDoc */
  override fun spawnUnboundedEntries(entries: Iterable<Map.Entry<String, Any?>>): JsMultiMap<String, Any?> =
    JsMultiMap.unboundedEntries(entries)

  /** @inheritDoc */
  override fun spawnUnboundedJsEntries(entries: Iterable<MapLike.Entry<String, Any?>>): JsMultiMap<String, Any?> =
    JsMultiMap.unbounded(entries)

  /** @inheritDoc */
  override fun implName(): String = "JsMultiMap"

  @Test fun testBasicConstructor() {
    val entry = JsMultiMap<String, Any?>()
    assertNotNull(entry, "should be able to construct an empty map via the constructor")
  }

  @Test fun testPresizedConstructor() {
    val entry = JsMultiMap<String, Any?>(5)
    assertNotNull(entry, "should be able to construct an empty map via the pre-sized constructor")
    val entry2: JsMultiMap<String, Any?> = JsMultiMap.empty(5)
    assertNotNull(entry2, "should be able to construct an empty map via the pre-sized constructor")
  }

  @Test fun testToString() {
    val entry = JsMultiMap<String, Any?>()
    assertEquals("MultiMap(immutable, unsorted, size=0)", entry.toString())
  }

  @Test fun testSpawnMap() {
    val example = mutableMapOf("hi" to "hello")
    val target = JsMultiMap.of(example)
    assertNotNull(target)
    assertEquals("hello", target["hi"])
  }
}