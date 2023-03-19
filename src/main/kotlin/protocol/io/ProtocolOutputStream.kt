package protocol.io

import annotation.PacketData
import annotation.UseInstead
import protocol.Auth
import protocol.DTPacket
import java.io.DataOutputStream
import java.io.OutputStream
import java.util.Date
import kotlin.reflect.KVisibility
import kotlin.reflect.full.*

class ProtocolOutputStream(s: OutputStream) : DataOutputStream(s) {
    fun writeNull() = writeByte(DTPacket.MagicNumber.FIELD_NULL)
    fun writeBooleanField(value: Boolean) {
        writeByte(DTPacket.MagicNumber.FIELD_BOOLEAN)
        writeBoolean(value)
    }
    fun writeByteField(value: Byte) {
        writeByte(DTPacket.MagicNumber.FIELD_BYTE)
        writeByte(value.toInt())
    }
    fun writeShortField(value: Short) {
        writeByte(DTPacket.MagicNumber.FIELD_SHORT)
        writeShort(value.toInt())
    }
    fun writeIntField(value: Int) {
        writeByte(DTPacket.MagicNumber.FIELD_INT)
        writeInt(value)
    }
    fun writeLongField(value: Long) {
        writeByte(DTPacket.MagicNumber.FIELD_LONG)
        writeLong(value)
    }
    fun writeFloatField(value: Float) {
        writeByte(DTPacket.MagicNumber.FIELD_LONG)
        writeFloat(value)
    }
    fun writeDoubleField(value: Double) {
        writeByte(DTPacket.MagicNumber.FIELD_DOUBLE)
        writeDouble(value)
    }
    fun writeDatetimeField(date: Date) {
        writeByte(DTPacket.MagicNumber.FIELD_DATETIME)
        writeLong(date.time)
    }

    fun writeStringField(value: String) {
        writeByte(DTPacket.MagicNumber.FIELD_STRING)
        writeUTF(value)
    }
    fun writeBooleans(value: BooleanArray) {
        writeByte(DTPacket.MagicNumber.FIELD_BOOLEAN_ARRAY)
        writeShort(value.size)
        value.forEach { bool -> writeBoolean(bool) }
    }
    fun writeBytes(value: ByteArray) {
        writeByte(DTPacket.MagicNumber.FIELD_BYTE_ARRAY)
        writeInt(value.size)
        write(value)
    }
    fun writeShorts(value: ShortArray) {
        writeByte(DTPacket.MagicNumber.FIELD_SHORT_ARRAY)
        writeShort(value.size)
        value.forEach { short -> writeShort(short.toInt()) }
    }
    fun writeInts(value: IntArray) {
        writeByte(DTPacket.MagicNumber.FIELD_INT_ARRAY)
        writeShort(value.size)
        value.forEach { int -> writeInt(int) }
    }
    fun writeLongs(value: LongArray) {
        writeByte(DTPacket.MagicNumber.FIELD_LONG_ARRAY)
        writeShort(value.size)
        value.forEach { long -> writeLong(long) }
    }
    fun writeFloats(value: FloatArray) {
        writeByte(DTPacket.MagicNumber.FIELD_FLOAT_ARRAY)
        writeShort(value.size)
        value.forEach { float -> writeFloat(float) }
    }
    fun writeDoubles(value: DoubleArray) {
        writeByte(DTPacket.MagicNumber.FIELD_DOUBLE_ARRAY)
        writeShort(value.size)
        value.forEach { double -> writeDouble(double) }
    }

    fun writeDataObject(data: List<Any?>) {
        beginObject()
        writeByte(data.size)
        for (field in data) {
            when (field) {
                null -> writeNull()
                is Boolean -> writeBooleanField(field)
                is Byte -> writeByteField(field)
                is Short -> writeShortField(field)
                is Int -> writeIntField(field)
                is Long -> writeLongField(field)
                is Float -> writeFloatField(field)
                is Double -> writeDoubleField(field)
                is Date -> writeDatetimeField(field)

                is String -> writeStringField(field)
                is BooleanArray -> writeBooleans(field)
                is ByteArray -> writeBytes(field)
                is ShortArray -> writeShorts(field)
                is IntArray -> writeInts(field)
                is LongArray -> writeLongs(field)
                is FloatArray -> writeFloats(field)
                is DoubleArray -> writeDoubles(field)

                else -> {
                    val dataObject = if (field is List<*>) field
                    else if (field is Array<*>) field.toList()
                    else if (field is Set<*>) field.toList()
                    else if (field::class.hasAnnotation<PacketData>()) field::class.java.declaredFields
                        .map {
                            it.isAccessible = true
                            it.get(field)
                        }
                    else {
                        println("[ProtocolOutputStream] Field type ${field::class.simpleName} is unsupported.")
                        continue
                    }
                    writeByte(DTPacket.MagicNumber.FIELD_OBJECT)
                    writeDataObject(dataObject)
                }
            }
        }
        endObject()
    }

    fun beginMessage(id: DTPacket.PacketID) = writeShort(id.value.toInt())

    fun beginFlags() = writeByte(DTPacket.MagicNumber.BEGIN_FLAGS)
    fun writeFlag(flag: DTPacket.PacketFlag) = writeByte(flag.value)
    fun endFlags() = writeByte(DTPacket.MagicNumber.END_FLAGS)

    fun beginAuth() = writeByte(DTPacket.MagicNumber.AUTHENTICATED)
    @UseInstead("write()")
    fun writeAuth(auth: Auth) {
        write(auth.signature)
        auth.data.apply {
            writeLong(userId)
            writeByte(role.toInt())
            writeLong(authTime)
        }
    }

    fun beginObject() = writeByte(DTPacket.MagicNumber.BEGIN_OBJECT)
    fun endObject() = writeByte(DTPacket.MagicNumber.END_OBJECT)

    fun endMessage() = writeByte(DTPacket.MagicNumber.MESSAGE_ENDING)
}
