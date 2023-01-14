// automatically generated by the FlatBuffers compiler, do not modify

package elide.page.Context_.Metadata_

import java.nio.*
import kotlin.math.sign
import com.google.flatbuffers.*

@Suppress("unused")
class OpenGraph : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : OpenGraph {
        __init(_i, _bb)
        return this
    }
    val siteName : String?
        get() {
            val o = __offset(4)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val siteNameAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(4, 1)
    fun siteNameInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 4, 1)
    val title : String?
        get() {
            val o = __offset(6)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val titleAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(6, 1)
    fun titleInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 6, 1)
    val description : String?
        get() {
            val o = __offset(8)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val descriptionAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(8, 1)
    fun descriptionInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 8, 1)
    val locale : elide.base.LanguageSpec? get() = locale(elide.base.LanguageSpec())
    fun locale(obj: elide.base.LanguageSpec) : elide.base.LanguageSpec? {
        val o = __offset(10)
        return if (o != 0) {
            obj.__assign(__indirect(o + bb_pos), bb)
        } else {
            null
        }
    }
    val type : String?
        get() {
            val o = __offset(12)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val typeAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(12, 1)
    fun typeInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 12, 1)
    val url : String?
        get() {
            val o = __offset(14)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val urlAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(14, 1)
    fun urlInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 14, 1)
    val twitter : elide.page.Context_.Metadata_.Twitter? get() = twitter(elide.page.Context_.Metadata_.Twitter())
    fun twitter(obj: elide.page.Context_.Metadata_.Twitter) : elide.page.Context_.Metadata_.Twitter? {
        val o = __offset(16)
        return if (o != 0) {
            obj.__assign(__indirect(o + bb_pos), bb)
        } else {
            null
        }
    }
    val canonical : String?
        get() {
            val o = __offset(18)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val canonicalAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(18, 1)
    fun canonicalInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 18, 1)
    val fbApp : String?
        get() {
            val o = __offset(20)
            return if (o != 0) __string(o + bb_pos) else null
        }
    val fbAppAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(20, 1)
    fun fbAppInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 20, 1)
    fun image(j: Int) : elide.page.Context_.Metadata_.OpenGraph_.OpenGraphImage? = image(elide.page.Context_.Metadata_.OpenGraph_.OpenGraphImage(), j)
    fun image(obj: elide.page.Context_.Metadata_.OpenGraph_.OpenGraphImage, j: Int) : elide.page.Context_.Metadata_.OpenGraph_.OpenGraphImage? {
        val o = __offset(22)
        return if (o != 0) {
            obj.__assign(__indirect(__vector(o) + j * 4), bb)
        } else {
            null
        }
    }
    val imageLength : Int
        get() {
            val o = __offset(22); return if (o != 0) __vector_len(o) else 0
        }
    fun video(j: Int) : elide.page.Context_.Metadata_.OpenGraph_.OpenGraphVideo? = video(elide.page.Context_.Metadata_.OpenGraph_.OpenGraphVideo(), j)
    fun video(obj: elide.page.Context_.Metadata_.OpenGraph_.OpenGraphVideo, j: Int) : elide.page.Context_.Metadata_.OpenGraph_.OpenGraphVideo? {
        val o = __offset(24)
        return if (o != 0) {
            obj.__assign(__indirect(__vector(o) + j * 4), bb)
        } else {
            null
        }
    }
    val videoLength : Int
        get() {
            val o = __offset(24); return if (o != 0) __vector_len(o) else 0
        }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsOpenGraph(_bb: ByteBuffer): OpenGraph = getRootAsOpenGraph(_bb, OpenGraph())
        fun getRootAsOpenGraph(_bb: ByteBuffer, obj: OpenGraph): OpenGraph {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createOpenGraph(builder: FlatBufferBuilder, siteNameOffset: Int, titleOffset: Int, descriptionOffset: Int, localeOffset: Int, typeOffset: Int, urlOffset: Int, twitterOffset: Int, canonicalOffset: Int, fbAppOffset: Int, imageOffset: Int, videoOffset: Int) : Int {
            builder.startTable(11)
            addVideo(builder, videoOffset)
            addImage(builder, imageOffset)
            addFbApp(builder, fbAppOffset)
            addCanonical(builder, canonicalOffset)
            addTwitter(builder, twitterOffset)
            addUrl(builder, urlOffset)
            addType(builder, typeOffset)
            addLocale(builder, localeOffset)
            addDescription(builder, descriptionOffset)
            addTitle(builder, titleOffset)
            addSiteName(builder, siteNameOffset)
            return endOpenGraph(builder)
        }
        fun startOpenGraph(builder: FlatBufferBuilder) = builder.startTable(11)
        fun addSiteName(builder: FlatBufferBuilder, siteName: Int) = builder.addOffset(0, siteName, 0)
        fun addTitle(builder: FlatBufferBuilder, title: Int) = builder.addOffset(1, title, 0)
        fun addDescription(builder: FlatBufferBuilder, description: Int) = builder.addOffset(2, description, 0)
        fun addLocale(builder: FlatBufferBuilder, locale: Int) = builder.addOffset(3, locale, 0)
        fun addType(builder: FlatBufferBuilder, type: Int) = builder.addOffset(4, type, 0)
        fun addUrl(builder: FlatBufferBuilder, url: Int) = builder.addOffset(5, url, 0)
        fun addTwitter(builder: FlatBufferBuilder, twitter: Int) = builder.addOffset(6, twitter, 0)
        fun addCanonical(builder: FlatBufferBuilder, canonical: Int) = builder.addOffset(7, canonical, 0)
        fun addFbApp(builder: FlatBufferBuilder, fbApp: Int) = builder.addOffset(8, fbApp, 0)
        fun addImage(builder: FlatBufferBuilder, image: Int) = builder.addOffset(9, image, 0)
        fun createImageVector(builder: FlatBufferBuilder, data: IntArray) : Int {
            builder.startVector(4, data.size, 4)
            for (i in data.size - 1 downTo 0) {
                builder.addOffset(data[i])
            }
            return builder.endVector()
        }
        fun startImageVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(4, numElems, 4)
        fun addVideo(builder: FlatBufferBuilder, video: Int) = builder.addOffset(10, video, 0)
        fun createVideoVector(builder: FlatBufferBuilder, data: IntArray) : Int {
            builder.startVector(4, data.size, 4)
            for (i in data.size - 1 downTo 0) {
                builder.addOffset(data[i])
            }
            return builder.endVector()
        }
        fun startVideoVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(4, numElems, 4)
        fun endOpenGraph(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}