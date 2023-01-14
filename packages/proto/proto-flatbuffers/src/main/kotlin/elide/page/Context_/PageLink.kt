// automatically generated by the FlatBuffers compiler, do not modify

package elide.page.Context_

import java.nio.*
import kotlin.math.sign
import com.google.flatbuffers.*

@Suppress("unused")
class PageLink : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : PageLink {
        __init(_i, _bb)
        return this
    }
    val relevance : String?
        get() {
            val o = __offset(4)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val relevanceAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(4, 1)
    fun relevanceInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 4, 1)
    val type : String?
        get() {
            val o = __offset(6)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val typeAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(6, 1)
    fun typeInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 6, 1)
    val href : webutil.html.types.TrustedResourceUrlProto? get() = href(webutil.html.types.TrustedResourceUrlProto())
    fun href(obj: webutil.html.types.TrustedResourceUrlProto) : webutil.html.types.TrustedResourceUrlProto? {
        val o = __offset(8)
        return if (o != 0) {
            obj.__assign(__indirect(o + bb_pos), bb)
        } else {
            null
        }
    }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsPageLink(_bb: ByteBuffer): PageLink = getRootAsPageLink(_bb, PageLink())
        fun getRootAsPageLink(_bb: ByteBuffer, obj: PageLink): PageLink {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createPageLink(builder: FlatBufferBuilder, relevanceOffset: Int, typeOffset: Int, hrefOffset: Int) : Int {
            builder.startTable(3)
            addHref(builder, hrefOffset)
            addType(builder, typeOffset)
            addRelevance(builder, relevanceOffset)
            return endPageLink(builder)
        }
        fun startPageLink(builder: FlatBufferBuilder) = builder.startTable(3)
        fun addRelevance(builder: FlatBufferBuilder, relevance: Int) = builder.addOffset(0, relevance, 0)
        fun addType(builder: FlatBufferBuilder, type: Int) = builder.addOffset(1, type, 0)
        fun addHref(builder: FlatBufferBuilder, href: Int) = builder.addOffset(2, href, 0)
        fun endPageLink(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}