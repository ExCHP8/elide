package elide.runtime.gvm.internals.intrinsics.js.struct.map

import elide.annotations.core.Polyglot
import elide.runtime.intrinsics.js.MapLike
import java.util.*

/** Implements a mutable and sorted map for use with JavaScript; backed by a [TreeMap]. */
@Suppress("unused")
internal class JsSortedMap<K: Comparable<K>, V> private constructor (
  backingMap: MutableMap<K, V>
) : BaseMutableJsMap<K, V>(backingMap, sorted = true), MutableSortedMap<K, V>, SortedMap<K, V> {
  /**
   * Constructor: Empty.
   *
   * Internal-use-only constructor for an empty backed map.
   */
  constructor() : this(mapImpl())

  /** Immutable sorted map factory. */
  @Suppress("unused") internal companion object Factory : SortedMapFactory<JsSortedMap<*, *>> {
    // Singleton empty map instance.
    private val EMPTY_MAP = JsSortedMap<Comparable<Any>, Any?>(mapImpl())

    // Internal function to create a backing-map implementation.
    @JvmStatic private fun <K: Comparable<K>, V> mapImpl(): MutableMap<K, V> = TreeMap()

    /**
     * Return a sorted and mutable [JsSortedMap] instance, which wraps the provided [map].
     *
     * @param map Existing map instance to wrap.
     * @return Wrapped JS map instance.
     */
    @JvmStatic override fun <K: Comparable<K>, V> of(map: MutableMap<K, V>): JsSortedMap<K, V> =
      JsSortedMap(TreeMap(map))

    /**
     * Return a copy of the provided sorted [map], but made sorted and mutable.
     *
     * @param map Existing map instance to wrap.
     * @return Copy of the provided JS map instance.
     */
    @JvmStatic override fun <K : Comparable<K>, V> copyOf(map: Map<K, V>): JsSortedMap<K, V> {
      return JsSortedMap(mapImpl<K, V>().apply {
        putAll(map)
      })
    }

    /**
     * Return a sorted and mutable [JsSortedMap] instance, created from the provided set of [pairs], each an
     * instance of [Pair] of type [K] and [V].
     *
     * @param pairs Pairs from which to create a JS map.
     * @return Created JS map instance.
     */
    @JvmStatic override fun <K: Comparable<K>, V> fromPairs(pairs: Collection<Pair<K, V>>) =
      JsSortedMap(mapImpl<K, V>().apply {
        pairs.forEach {
          put(it.first, it.second)
        }
      })

    /**
     * Return a sorted and mutable [JsSortedMap] instance, created from the provided sized collection of
     * [entries], each an instance of a normal Java [Map.Entry] of type [K] and [V].
     *
     * @param entries Map entries from which to create a JS map.
     * @return Created JS map instance.
     */
    @JvmStatic override fun <K: Comparable<K>, V> fromEntries(
      entries: Collection<Map.Entry<K, V>>,
    ) = JsSortedMap(mapImpl<K, V>().apply {
        entries.forEach {
          put(it.key, it.value)
        }
      })

    /**
     * Return a sorted and mutable [JsSortedMap] instance, created from the provided sized collection of
     * [entries], each an instance of a JS [MapLike.Entry] of type [K] and [V].
     *
     * @param entries Map entries from which to create a JS map.
     * @return Created JS map instance.
     */
    @JvmStatic override fun <K: Comparable<K>, V> from(entries: Collection<MapLike.Entry<K, V>>) =
      JsSortedMap(mapImpl<K, V>().apply {
        entries.forEach {
          put(it.key, it.value)
        }
      })

    /**
     * Return a sorted and mutable JavaScript map instance, created from the provided set of [entries], each an instance
     * of a normal Java [Map.Entry] of type [K] and [V].
     *
     * This variant explicitly creates a map from an unbounded [Iterable]. If possible, [fromPairs] should be preferred,
     * so that the underlying map implementation can be size-optimized during construction.
     *
     * @param entries Map entries from which to create a JS map.
     * @return Created JS map instance.
     */
    @JvmStatic override fun <K : Comparable<K>, V> unboundedEntries(
      entries: Iterable<Map.Entry<K, V>>
    ): JsSortedMap<K, V> = JsSortedMap(mapImpl<K, V>().apply {
      entries.forEach {
        put(it.key, it.value)
      }
    })

    /**
     * Return a sorted and mutable JavaScript map instance, created from the provided set of [pairs], each an instance
     * of [Pair] of type [K] and [V].
     *
     * This variant explicitly creates a map from an unbounded [Iterable]. If possible, [fromPairs] should be preferred,
     * so that the underlying map implementation can be size-optimized during construction.
     *
     * @param pairs Pairs from which to create a JS map.
     * @return Created JS map instance.
     */
    @JvmStatic override fun <K : Comparable<K>, V> unboundedPairs(
      pairs: Iterable<Pair<K, V>>
    ) = JsSortedMap(mapImpl<K, V>().apply {
      pairs.forEach {
        put(it.first, it.second)
      }
    })

    /**
     * Return a sorted and mutable JavaScript map instance, created from the provided set of [entries], each an instance
     * of a JS [MapLike.Entry] of type [K] and [V].
     *
     * This variant explicitly creates a map from an unbounded [Iterable]. If possible, [fromPairs] should be preferred,
     * so that the underlying map implementation can be size-optimized during construction.
     *
     * @param entries Map entries from which to create a JS map.
     * @return Created JS map instance.
     */
    @JvmStatic override fun <K : Comparable<K>, V> unbounded(
      entries: Iterable<MapLike.Entry<K, V>>
    ): JsSortedMap<K, V> = JsSortedMap(mapImpl<K, V>().apply {
      entries.forEach {
        put(it.key, it.value)
      }
    })

    /**
     * Return an empty mutable JS map instance that maintains sort order.
     *
     * @return Empty JS map instance.
     */
    @Suppress("UNCHECKED_CAST")
    @JvmStatic override fun <K: Comparable<K>, V> empty(): JsSortedMap<K, V> =
      EMPTY_MAP as JsSortedMap<K, V>
  }

  // Shortcut to cast the current backing-map as a sorted map, which it is supposed to be.
  private fun asSorted(): SortedMap<K, V> = backingMap as SortedMap<K, V>

  /** @inheritDoc */
  override fun comparator(): Comparator<in K>? = asSorted().comparator()

  /** @inheritDoc */
  override fun subMap(fromKey: K, toKey: K): SortedMap<K, V> = asSorted().subMap(fromKey, toKey)

  /** @inheritDoc */
  override fun headMap(toKey: K): SortedMap<K, V> = asSorted().headMap(toKey)

  /** @inheritDoc */
  override fun tailMap(fromKey: K): SortedMap<K, V> = asSorted().tailMap(fromKey)

  /** @inheritDoc */
  override fun firstKey(): K = asSorted().firstKey()

  /** @inheritDoc */
  override fun lastKey(): K = asSorted().lastKey()

  /** @inheritDoc */
  @Polyglot override fun sort() = Unit  // no-op: map is already sorted

  /** @inheritDoc */
  @Polyglot override fun toString(): String = "Map(mutable, sorted, size=$size)"
}