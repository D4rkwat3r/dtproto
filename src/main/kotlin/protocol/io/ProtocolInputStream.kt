package protocol.io

import protocol.Auth
import protocol.DTPacket
import protocol.error.IncorrectAuthSignatureException
import protocol.error.TokenMismatchException
import protocol.error.UnknownPacketException
import java.io.DataInputStream
import java.io.InputStream
import java.util.Date

class ProtocolInputStream(s: InputStream) : DataInputStream(s) {
    fun isNextNull() = readUnsignedByte() == DTPacket.MagicNumber.FIELD_NULL
    fun nextBoolean() = readBoolean()
    fun nextByte() = readByte()
    fun nextShort() = readShort()
    fun nextInt() = readInt()
    fun nextLong() = readLong()
    fun nextFloat() = readFloat()
    fun nextDouble() = readDouble()
    fun nextDatetime() = Date(readLong())

    fun nextString() = readUTF()
    fun nextBooleans() = BooleanArray(readUnsignedShort()) { readBoolean() }
    fun nextBytes() = ByteArray(readInt()).apply { read(this) }
    fun nextShorts() = ShortArray(readUnsignedShort()) { readUnsignedShort().toShort() }
    fun nextInts() = IntArray(readUnsignedShort()) { readInt() }
    fun nextLongs() = LongArray(readUnsignedShort()) { readLong() }
    fun nextFloats() = FloatArray(readUnsignedShort()) { readFloat() }
    fun nextDoubles() = DoubleArray(readUnsignedShort()) { readDouble() }

    fun nextDataObject(): List<Any?> {
        beginObject()
        val values = Array(readUnsignedByte()) {
            when (val typeId = readUnsignedByte()) {
                DTPacket.MagicNumber.FIELD_NULL -> null
                DTPacket.MagicNumber.FIELD_BOOLEAN -> nextBoolean()
                DTPacket.MagicNumber.FIELD_BYTE -> nextByte()
                DTPacket.MagicNumber.FIELD_SHORT -> nextShort()
                DTPacket.MagicNumber.FIELD_INT -> nextInt()
                DTPacket.MagicNumber.FIELD_LONG -> nextLong()
                DTPacket.MagicNumber.FIELD_FLOAT -> nextFloat()
                DTPacket.MagicNumber.FIELD_DOUBLE -> nextDouble()
                DTPacket.MagicNumber.FIELD_DATETIME -> nextDatetime()

                DTPacket.MagicNumber.FIELD_STRING -> nextString()
                DTPacket.MagicNumber.FIELD_BOOLEAN_ARRAY -> nextBooleans()
                DTPacket.MagicNumber.FIELD_BYTE_ARRAY -> nextBytes()
                DTPacket.MagicNumber.FIELD_SHORT_ARRAY -> nextShorts()
                DTPacket.MagicNumber.FIELD_INT_ARRAY -> nextInts()
                DTPacket.MagicNumber.FIELD_LONG_ARRAY -> nextLongs()
                DTPacket.MagicNumber.FIELD_FLOAT_ARRAY -> nextFloats()
                DTPacket.MagicNumber.FIELD_DOUBLE_ARRAY -> nextDoubles()

                DTPacket.MagicNumber.FIELD_OBJECT -> nextDataObject()

                else -> {
                    println("[ProtocolInputStream] Failed to determine type of the field with type id $typeId")
                    null
                }
            }
        }
        endObject()
        return values.toList()
    }

    fun beginMessage(): DTPacket.PacketID {
        val id = readUnsignedShort()
        return DTPacket.PacketID.values().find { it.value.toInt() == id }
            ?: throw UnknownPacketException()
    }

    fun beginFlags() {
        if (readUnsignedByte() != DTPacket.MagicNumber.BEGIN_FLAGS) {
            throw TokenMismatchException()
        }
    }
    fun readFlag(): DTPacket.PacketFlag? {
        val id = readUnsignedByte()
        return DTPacket.PacketFlag.values().find { it.value == id }
    }
    fun endFlags() {
        if (readUnsignedByte() != DTPacket.MagicNumber.END_FLAGS) {
            throw TokenMismatchException()
        }
    }

    fun beginAuth() {
        if (readUnsignedByte() != DTPacket.MagicNumber.AUTHENTICATED) {
            throw TokenMismatchException()
        }
    }
    fun readAuth(key: ByteArray? = null): Auth {
        beginAuth()
        return Auth(ByteArray(32).apply { read(this) }, Auth.PacketAuth(readLong(), readByte(), readLong())).apply {
            if (key != null) {
                if (!validate(key)) throw IncorrectAuthSignatureException()
            }
        }
    }

    fun beginObject() {
        if (readUnsignedByte() != DTPacket.MagicNumber.BEGIN_OBJECT) {
            throw TokenMismatchException()
        }
    }
    fun endObject() {
        if (readUnsignedByte() != DTPacket.MagicNumber.END_OBJECT) {
            throw TokenMismatchException()
        }
    }

    fun endMessage() {
        if (readUnsignedByte() != DTPacket.MagicNumber.MESSAGE_ENDING) {
            throw TokenMismatchException()
        }
    }

    fun bytesAfter(after: Int, count: Int): ByteArray {
        mark(after + count)
        skip(after.toLong())
        return ByteArray(count).apply {
            read(this)
            reset()
        }
    }
    fun byteAfter(after: Int) = bytesAfter(after, 1)[0]
    fun uByteAfter(after: Int) = byteAfter(after).toUByte()
}
