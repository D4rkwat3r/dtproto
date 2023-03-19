package protocol

import annotation.PacketData
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

data class Auth(
    val signature: ByteArray,
    val data: PacketAuth
) {
    @PacketData
    data class PacketAuth(
        val userId: Long,
        val role: Byte,
        val authTime: Long
    ) {
        constructor(userId: Long, role: DTPacket.Role, authTime: Long): this(userId, role.value, authTime)

        fun signature(key: ByteArray): ByteArray {
            val signatureData = ByteArrayOutputStream().apply {
                DataOutputStream(this).apply {
                    writeLong(userId)
                    writeByte(role.toInt())
                    writeLong(authTime)
                }
            }.toByteArray()
            val mac = Mac.getInstance("HmacSHA256")
            mac.init(SecretKeySpec(key, "HmacSHA256"))
            return mac.doFinal(signatureData)
        }
    }

    fun validate(key: ByteArray): Boolean {
        return data.signature(key).contentEquals(signature)
    }

    fun toRawHeader(): ByteArray {
        val bytes = ByteArrayOutputStream()
        DataOutputStream(bytes).apply {
            write(signature)
            writeLong(data.userId)
            writeByte(data.role.toInt())
            writeLong(data.authTime)
        }
        return bytes.toByteArray()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Auth

        if (!signature.contentEquals(other.signature)) return false
        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        var result = signature.contentHashCode()
        result = 31 * result + data.hashCode()
        return result
    }
}
