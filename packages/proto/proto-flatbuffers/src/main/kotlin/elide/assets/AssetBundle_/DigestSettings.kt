// automatically generated by the FlatBuffers compiler, do not modify

package elide.assets.AssetBundle_

import java.nio.*
import kotlin.math.sign
import com.google.flatbuffers.*

@Suppress("unused")
class DigestSettings : Table() {

    fun __init(_i: Int, _bb: ByteBuffer)  {
        __reset(_i, _bb)
    }
    fun __assign(_i: Int, _bb: ByteBuffer) : DigestSettings {
        __init(_i, _bb)
        return this
    }
    val algorithm : Int
        get() {
            val o = __offset(4)
            return if(o != 0) bb.getInt(o + bb_pos) else 0
        }
    val tail : UInt
        get() {
            val o = __offset(6)
            return if(o != 0) bb.getInt(o + bb_pos).toUInt() else 0u
        }
    val rounds : UInt
        get() {
            val o = __offset(8)
            return if(o != 0) bb.getInt(o + bb_pos).toUInt() else 0u
        }
    companion object {
        fun validateVersion() = Constants.FLATBUFFERS_22_12_06()
        fun getRootAsDigestSettings(_bb: ByteBuffer): DigestSettings = getRootAsDigestSettings(_bb, DigestSettings())
        fun getRootAsDigestSettings(_bb: ByteBuffer, obj: DigestSettings): DigestSettings {
            _bb.order(ByteOrder.LITTLE_ENDIAN)
            return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb))
        }
        fun createDigestSettings(builder: FlatBufferBuilder, algorithm: Int, tail: UInt, rounds: UInt) : Int {
            builder.startTable(3)
            addRounds(builder, rounds)
            addTail(builder, tail)
            addAlgorithm(builder, algorithm)
            return endDigestSettings(builder)
        }
        fun startDigestSettings(builder: FlatBufferBuilder) = builder.startTable(3)
        fun addAlgorithm(builder: FlatBufferBuilder, algorithm: Int) = builder.addInt(0, algorithm, 0)
        fun addTail(builder: FlatBufferBuilder, tail: UInt) = builder.addInt(1, tail.toInt(), 0)
        fun addRounds(builder: FlatBufferBuilder, rounds: UInt) = builder.addInt(2, rounds.toInt(), 0)
        fun endDigestSettings(builder: FlatBufferBuilder) : Int {
            val o = builder.endTable()
            return o
        }
    }
}