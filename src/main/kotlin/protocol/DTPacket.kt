package protocol

import annotation.PacketData
import annotation.ProtocolException
import protocol.error.MalformedPacketException
import protocol.error.PacketConverterException
import protocol.io.ProtocolInputStream
import protocol.io.ProtocolOutputStream
import protocol.packet.*
import protocol.util.PacketConverterUtil
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import kotlin.reflect.*
import kotlin.reflect.full.*

abstract class DTPacket(
    var flags: MutableList<PacketFlag>,
    private val data: List<Any?> = emptyList(),
) {
    constructor(flags: List<PacketFlag>, vararg data: Any?): this(flags.toMutableList(), data.toList())
    constructor(flags: List<PacketFlag>): this(flags.toMutableList(), emptyList())
    constructor(vararg data: Any?): this(mutableListOf(), data.toList())
    constructor(): this(mutableListOf(), emptyList())

    object MagicNumber {
        const val BEGIN_FLAGS = 0xA
        const val END_FLAGS = 0xF

        const val FIELD_NULL = 0x01 // 0x1

        const val FIELD_BOOLEAN = 0x10
        const val FIELD_BYTE = 0x11
        const val FIELD_SHORT = 0x12
        const val FIELD_INT = 0x13
        const val FIELD_LONG = 0x14
        const val FIELD_FLOAT = 0x15
        const val FIELD_DOUBLE = 0x16
        const val FIELD_DATETIME = 0x17

        const val FIELD_STRING = 0x20
        const val FIELD_BOOLEAN_ARRAY = 0x21
        const val FIELD_BYTE_ARRAY = 0x22
        const val FIELD_SHORT_ARRAY = 0x23
        const val FIELD_INT_ARRAY = 0x24
        const val FIELD_LONG_ARRAY = 0x25
        const val FIELD_FLOAT_ARRAY = 0x26
        const val FIELD_DOUBLE_ARRAY = 0x27

        const val FIELD_OBJECT = 0x30

        const val BEGIN_OBJECT = 0xAA
        const val END_OBJECT = 0xFF

        const val AUTHENTICATED = 0x1A

        const val MESSAGE_ENDING = 0x00
    }

    enum class PacketID(val value: Short, val clazz: KClass<*>) {
        SERVER_INFO(0x1, P0x1ServerInfo::class),
        ACK_SERVER_INFO(0x2, P0x2AckServerInfo::class),

        LOGIN(0x3, P0x3Login::class),
        REGISTER(0x4, P0x4Register::class),
        ACK_AUTH(0x5, P0x5AckAuth::class),

        PROFILE(0x6, P0x6Profile::class),
        ACK_PROFILE(0x7, P0x7AckProfile::class),


        EXCEPTION(0x10, P0x10Exception::class)
    }

    enum class PacketFlag(val value: Int) {
        NO_ACK(0x1)
    }

    enum class Role(val value: Byte) {
        USER(0x1),
        ADMIN(0x2),
        SUPER_ADMIN(0x3)
    }

    var requestId = 0
    var auth: ByteArray? = null
    abstract val id: PacketID

    fun setRequestId(requestId: Int): DTPacket {
        this.requestId = requestId
        return this
    }

    fun setFlags(flags: List<PacketFlag>): DTPacket {
        this.flags = flags.toMutableList()
        return this
    }

    fun addFlag(flag: PacketFlag): DTPacket {
        this.flags.add(flag)
        return this
    }

    fun setAuth(auth: ByteArray): DTPacket {
        this.auth = auth
        return this
    }

    fun serialize(): ByteArray {
        val bytes = ByteArrayOutputStream()
        ProtocolOutputStream(bytes).apply {
            beginMessage(id)
            writeShort(requestId)
            if (flags.isNotEmpty()) {
                beginFlags()
                writeByte(flags.size)
                flags.forEach { flag -> writeFlag(flag) }
                endFlags()
            }
            if (auth != null) {
                beginAuth()
                write(auth!!)
            }
            if (data.isNotEmpty()) {
                writeDataObject(data)
            }
            endMessage()
        }
        return bytes.toByteArray()
    }

    override fun toString(): String {
        val builder = StringBuilder(this::class.simpleName ?: this::class.java.simpleName).append("(")
        data.forEach { value ->
            if (builder[builder.lastIndex] != '(') builder.append(", ")
            builder.append(value)
        }
        return builder.append(")").toString()
    }

    companion object {
        private fun packetFromByteArray(packet: ByteArray): DTPacket {
            ProtocolInputStream(ByteArrayInputStream(packet)).apply {
                val packetId = beginMessage()
                val requestId = readUnsignedShort()
                val flags = mutableListOf<PacketFlag>()
                var auth: Auth? = null
                var data: List<Any?> = listOf()
                if (uByteAfter(0).toInt() == MagicNumber.BEGIN_FLAGS) {
                    beginFlags()
                    repeat(readUnsignedByte()) {
                        readFlag()?.let { flag -> flags.add(flag) }
                    }
                    endFlags()
                }
                if (uByteAfter(0).toInt() == MagicNumber.AUTHENTICATED) {
                    auth = readAuth()
                }
                if (uByteAfter(0).toInt() == MagicNumber.BEGIN_OBJECT) {
                    data = nextDataObject()
                }
                endMessage()
                return PacketConverterUtil.createObjectFor(packetId, requestId, flags, auth, data) ?: throw PacketConverterException()
            }
        }

        @JvmStatic
        fun deserialize(packet: ByteArray): DTPacket {
            return try {
                packetFromByteArray(packet)
            } catch (e: NotImplementedError) {
                if (e::class.hasAnnotation<ProtocolException>()) throw e
                throw MalformedPacketException()
            }
        }
    }
}
