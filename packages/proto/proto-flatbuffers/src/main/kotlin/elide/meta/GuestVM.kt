// automatically generated by the FlatBuffers compiler, do not modify

package elide.meta

import java.nio.*
import kotlin.math.sign
import com.google.flatbuffers.*

@Suppress("unused")
class GuestVM : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : GuestVM {
        __init(_i, _bb)
        return this
    }
    fun language(j: Int) : Int {
        val o = __offset(4)
        return if (o != 0) {
            bb.getInt(__vector(o) + j * 4)
        } else {
            0
        }
    }
    val languageLength : Int
        get() {
            val o = __offset(4); return if (o != 0) __vector_len(o) else 0
        }
    val languageAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(4, 4)
    fun languageInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 4, 4)
    val config : elide.meta.GuestConfiguration? get() = config(elide.meta.GuestConfiguration())
    fun config(obj: elide.meta.GuestConfiguration) : elide.meta.GuestConfiguration? {
        val o = __offset(6)
        return if (o != 0) {
            obj.__assign(__indirect(o + bb_pos), bb)
        } else {
            null
        }
    }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsGuestVM(_bb: ByteBuffer): GuestVM = getRootAsGuestVM(_bb, GuestVM())
        fun getRootAsGuestVM(_bb: ByteBuffer, obj: GuestVM): GuestVM {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createGuestVM(builder: FlatBufferBuilder, languageOffset: Int, configOffset: Int) : Int {
            builder.startTable(2)
            addConfig(builder, configOffset)
            addLanguage(builder, languageOffset)
            return endGuestVM(builder)
        }
        fun startGuestVM(builder: FlatBufferBuilder) = builder.startTable(2)
        fun addLanguage(builder: FlatBufferBuilder, language: Int) = builder.addOffset(0, language, 0)
        fun createLanguageVector(builder: FlatBufferBuilder, data: IntArray) : Int {
            builder.startVector(4, data.size, 4)
            for (i in data.size - 1 downTo 0) {
                builder.addInt(data[i])
            }
            return builder.endVector()
        }
        fun startLanguageVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(4, numElems, 4)
        fun addConfig(builder: FlatBufferBuilder, config: Int) = builder.addOffset(1, config, 0)
        fun endGuestVM(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}