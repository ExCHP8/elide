// automatically generated by the FlatBuffers compiler, do not modify

package elide.assets.AssetBundle_

import java.nio.*
import kotlin.math.sign
import com.google.flatbuffers.*

@Suppress("unused")
class ScriptBundle : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : ScriptBundle {
        __init(_i, _bb)
        return this
    }
    val module : String?
        get() {
            val o = __offset(4)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val moduleAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(4, 1)
    fun moduleInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 4, 1)
    fun asset(j: Int) : elide.assets.AssetBundle_.ScriptBundle_.ScriptAsset? = asset(elide.assets.AssetBundle_.ScriptBundle_.ScriptAsset(), j)
    fun asset(obj: elide.assets.AssetBundle_.ScriptBundle_.ScriptAsset, j: Int) : elide.assets.AssetBundle_.ScriptBundle_.ScriptAsset? {
        val o = __offset(6)
        return if (o != 0) {
            obj.__assign(__indirect(__vector(o) + j * 4), bb)
        } else {
            null
        }
    }
    val assetLength : Int
        get() {
            val o = __offset(6); return if (o != 0) __vector_len(o) else 0
        }
    val dependencies : elide.assets.AssetBundle_.AssetDependencies? get() = dependencies(elide.assets.AssetBundle_.AssetDependencies())
    fun dependencies(obj: elide.assets.AssetBundle_.AssetDependencies) : elide.assets.AssetBundle_.AssetDependencies? {
        val o = __offset(8)
        return if (o != 0) {
            obj.__assign(__indirect(o + bb_pos), bb)
        } else {
            null
        }
    }
    val compressable : Boolean
        get() {
            val o = __offset(10)
            return if(o != 0) 0.toByte() != bb.get(o + bb_pos) else false
        }
    val cacheable : Boolean
        get() {
            val o = __offset(12)
            return if(o != 0) 0.toByte() != bb.get(o + bb_pos) else false
        }
    val external : Boolean
        get() {
            val o = __offset(14)
            return if(o != 0) 0.toByte() != bb.get(o + bb_pos) else false
        }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsScriptBundle(_bb: ByteBuffer): ScriptBundle = getRootAsScriptBundle(_bb, ScriptBundle())
        fun getRootAsScriptBundle(_bb: ByteBuffer, obj: ScriptBundle): ScriptBundle {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createScriptBundle(builder: FlatBufferBuilder, moduleOffset: Int, assetOffset: Int, dependenciesOffset: Int, compressable: Boolean, cacheable: Boolean, external: Boolean) : Int {
            builder.startTable(6)
            addDependencies(builder, dependenciesOffset)
            addAsset(builder, assetOffset)
            addModule(builder, moduleOffset)
            addExternal(builder, external)
            addCacheable(builder, cacheable)
            addCompressable(builder, compressable)
            return endScriptBundle(builder)
        }
        fun startScriptBundle(builder: FlatBufferBuilder) = builder.startTable(6)
        fun addModule(builder: FlatBufferBuilder, module: Int) = builder.addOffset(0, module, 0)
        fun addAsset(builder: FlatBufferBuilder, asset: Int) = builder.addOffset(1, asset, 0)
        fun createAssetVector(builder: FlatBufferBuilder, data: IntArray) : Int {
            builder.startVector(4, data.size, 4)
            for (i in data.size - 1 downTo 0) {
                builder.addOffset(data[i])
            }
            return builder.endVector()
        }
        fun startAssetVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(4, numElems, 4)
        fun addDependencies(builder: FlatBufferBuilder, dependencies: Int) = builder.addOffset(2, dependencies, 0)
        fun addCompressable(builder: FlatBufferBuilder, compressable: Boolean) = builder.addBoolean(3, compressable, false)
        fun addCacheable(builder: FlatBufferBuilder, cacheable: Boolean) = builder.addBoolean(4, cacheable, false)
        fun addExternal(builder: FlatBufferBuilder, external: Boolean) = builder.addBoolean(5, external, false)
        fun endScriptBundle(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}