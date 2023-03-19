package protocol.model

import annotation.PacketData
import protocol.DTPacket

@PacketData
data class UserProfile(
    val nickname: String,
    val deepId: String,
    val userId: Long,
    val role: Byte
) {
    constructor(nickname: String, deepId: String, userId: Long, role: DTPacket.Role): this(nickname, deepId, userId, role.value)
}
