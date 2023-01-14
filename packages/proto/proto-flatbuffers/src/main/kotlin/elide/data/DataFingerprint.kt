// automatically generated by the FlatBuffers compiler, do not modify

package elide.data

import java.nio.*
import kotlin.math.sign
import com.google.flatbuffers.*

@Suppress("unused")
class DataFingerprint : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : DataFingerprint {
        __init(_i, _bb)
        return this
    }
    val hash : Int
        get() {
            val o = __offset(4)
            return if(o != 0) bb.getInt(o + bb_pos) else 0
        }
    fun salt(j: Int) : UByte {
        val o = __offset(6)
        return if (o != 0) {
            bb.get(__vector(o) + j * 1).toUByte()
        } else {
            0u
        }
    }
    val saltLength : Int
        get() {
            val o = __offset(6); return if (o != 0) __vector_len(o) else 0
        }
    val saltAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(6, 1)
    fun saltInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 6, 1)
    fun fingerprint(j: Int) : UByte {
        val o = __offset(8)
        return if (o != 0) {
            bb.get(__vector(o) + j * 1).toUByte()
        } else {
            0u
        }
    }
    val fingerprintLength : Int
        get() {
            val o = __offset(8); return if (o != 0) __vector_len(o) else 0
        }
    val fingerprintAsByteBuffer : ByteBuffer get() = __vector_as_bytebuffer(8, 1)
    fun fingerprintInByteBuffer(_bb: ByteBuffer) : ByteBuffer = __vector_in_bytebuffer(_bb, 8, 1)
    val encoding : Int
        get() {
            val o = __offset(10)
            return if(o != 0) bb.getInt(o + bb_pos) else 0
        }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsDataFingerprint(_bb: ByteBuffer): DataFingerprint = getRootAsDataFingerprint(_bb, DataFingerprint())
        fun getRootAsDataFingerprint(_bb: ByteBuffer, obj: DataFingerprint): DataFingerprint {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createDataFingerprint(builder: FlatBufferBuilder, hash: Int, saltOffset: Int, fingerprintOffset: Int, encoding: Int) : Int {
            builder.startTable(4)
            addEncoding(builder, encoding)
            addFingerprint(builder, fingerprintOffset)
            addSalt(builder, saltOffset)
            addHash(builder, hash)
            return endDataFingerprint(builder)
        }
        fun startDataFingerprint(builder: FlatBufferBuilder) = builder.startTable(4)
        fun addHash(builder: FlatBufferBuilder, hash: Int) = builder.addInt(0, hash, 0)
        fun addSalt(builder: FlatBufferBuilder, salt: Int) = builder.addOffset(1, salt, 0)
        fun createSaltVector(builder: FlatBufferBuilder, data: UByteArray) : Int {
            builder.startVector(1, data.size, 1)
            for (i in data.size - 1 downTo 0) {
                builder.addByte(data[i].toByte())
            }
            return builder.endVector()
        }
        fun startSaltVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(1, numElems, 1)
        fun addFingerprint(builder: FlatBufferBuilder, fingerprint: Int) = builder.addOffset(2, fingerprint, 0)
        fun createFingerprintVector(builder: FlatBufferBuilder, data: UByteArray) : Int {
            builder.startVector(1, data.size, 1)
            for (i in data.size - 1 downTo 0) {
                builder.addByte(data[i].toByte())
            }
            return builder.endVector()
        }
        fun startFingerprintVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(1, numElems, 1)
        fun addEncoding(builder: FlatBufferBuilder, encoding: Int) = builder.addInt(3, encoding, 0)
        fun endDataFingerprint(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}