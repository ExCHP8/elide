// automatically generated by the FlatBuffers compiler, do not modify

package elide.page.Context_

import java.nio.*
import kotlin.math.sign
import com.google.flatbuffers.*

@Suppress("unused")
class DynamicETag : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : DynamicETag {
        __init(_i, _bb)
        return this
    }
    val enabled : Boolean
        get() {
            val o = __offset(4)
            return if(o != 0) 0.toByte() != bb.get(o + bb_pos) else false
        }
    val strong : Boolean
        get() {
            val o = __offset(6)
            return if(o != 0) 0.toByte() != bb.get(o + bb_pos) else false
        }
    val preimage : elide.data.DataFingerprint? get() = preimage(elide.data.DataFingerprint())
    fun preimage(obj: elide.data.DataFingerprint) : elide.data.DataFingerprint? {
        val o = __offset(8)
        return if (o != 0) {
            obj.__assign(__indirect(o + bb_pos), bb)
        } else {
            null
        }
    }
    val response : elide.data.DataFingerprint? get() = response(elide.data.DataFingerprint())
    fun response(obj: elide.data.DataFingerprint) : elide.data.DataFingerprint? {
        val o = __offset(10)
        return if (o != 0) {
            obj.__assign(__indirect(o + bb_pos), bb)
        } else {
            null
        }
    }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsDynamicETag(_bb: ByteBuffer): DynamicETag = getRootAsDynamicETag(_bb, DynamicETag())
        fun getRootAsDynamicETag(_bb: ByteBuffer, obj: DynamicETag): DynamicETag {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createDynamicETag(builder: FlatBufferBuilder, enabled: Boolean, strong: Boolean, preimageOffset: Int, responseOffset: Int) : Int {
            builder.startTable(4)
            addResponse(builder, responseOffset)
            addPreimage(builder, preimageOffset)
            addStrong(builder, strong)
            addEnabled(builder, enabled)
            return endDynamicETag(builder)
        }
        fun startDynamicETag(builder: FlatBufferBuilder) = builder.startTable(4)
        fun addEnabled(builder: FlatBufferBuilder, enabled: Boolean) = builder.addBoolean(0, enabled, false)
        fun addStrong(builder: FlatBufferBuilder, strong: Boolean) = builder.addBoolean(1, strong, false)
        fun addPreimage(builder: FlatBufferBuilder, preimage: Int) = builder.addOffset(2, preimage, 0)
        fun addResponse(builder: FlatBufferBuilder, response: Int) = builder.addOffset(3, response, 0)
        fun endDynamicETag(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}