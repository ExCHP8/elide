// automatically generated by the FlatBuffers compiler, do not modify

package elide.page.SemanticMetadata_

import java.nio.*
import kotlin.math.sign
import com.google.flatbuffers.*

@Suppress("unused")
class Anonymous2 : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : Anonymous2 {
        __init(_i, _bb)
        return this
    }
    val content : String?
        get() {
            val o = __offset(4)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val contentAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(4, 1)
    fun contentInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 4, 1)
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsAnonymous2(_bb: ByteBuffer): Anonymous2 = getRootAsAnonymous2(_bb, Anonymous2())
        fun getRootAsAnonymous2(_bb: ByteBuffer, obj: Anonymous2): Anonymous2 {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createAnonymous2(builder: FlatBufferBuilder, contentOffset: Int) : Int {
            builder.startTable(1)
            addContent(builder, contentOffset)
            return endAnonymous2(builder)
        }
        fun startAnonymous2(builder: FlatBufferBuilder) = builder.startTable(1)
        fun addContent(builder: FlatBufferBuilder, content: Int) = builder.addOffset(0, content, 0)
        fun endAnonymous2(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}