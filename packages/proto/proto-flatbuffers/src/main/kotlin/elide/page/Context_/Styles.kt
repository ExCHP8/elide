// automatically generated by the FlatBuffers compiler, do not modify

package elide.page.Context_

import java.nio.*
import kotlin.math.sign
import com.google.flatbuffers.*

@Suppress("unused")
class Styles : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : Styles {
        __init(_i, _bb)
        return this
    }
    val lifted : webutil.html.types.SafeStyleProto? get() = lifted(webutil.html.types.SafeStyleProto())
    fun lifted(obj: webutil.html.types.SafeStyleProto) : webutil.html.types.SafeStyleProto? {
        val o = __offset(4)
        return if (o != 0) {
            obj.__assign(__indirect(o + bb_pos), bb)
        } else {
            null
        }
    }
    fun link(j: Int) : elide.page.Context_.Styles_.Stylesheet? = link(elide.page.Context_.Styles_.Stylesheet(), j)
    fun link(obj: elide.page.Context_.Styles_.Stylesheet, j: Int) : elide.page.Context_.Styles_.Stylesheet? {
        val o = __offset(6)
        return if (o != 0) {
            obj.__assign(__indirect(__vector(o) + j * 4), bb)
        } else {
            null
        }
    }
    val linkLength : Int
        get() {
            val o = __offset(6); return if (o != 0) __vector_len(o) else 0
        }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsStyles(_bb: ByteBuffer): Styles = getRootAsStyles(_bb, Styles())
        fun getRootAsStyles(_bb: ByteBuffer, obj: Styles): Styles {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createStyles(builder: FlatBufferBuilder, liftedOffset: Int, linkOffset: Int) : Int {
            builder.startTable(2)
            addLink(builder, linkOffset)
            addLifted(builder, liftedOffset)
            return endStyles(builder)
        }
        fun startStyles(builder: FlatBufferBuilder) = builder.startTable(2)
        fun addLifted(builder: FlatBufferBuilder, lifted: Int) = builder.addOffset(0, lifted, 0)
        fun addLink(builder: FlatBufferBuilder, link: Int) = builder.addOffset(1, link, 0)
        fun createLinkVector(builder: FlatBufferBuilder, data: IntArray) : Int {
            builder.startVector(4, data.size, 4)
            for (i in data.size - 1 downTo 0) {
                builder.addOffset(data[i])
            }
            return builder.endVector()
        }
        fun startLinkVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(4, numElems, 4)
        fun endStyles(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}