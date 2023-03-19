package protocol.packet

import annotation.Packet
import protocol.Auth
import protocol.DTPacket
import protocol.model.UserProfile

@Packet
class P0x5AckAuth(val authHeader: ByteArray, val profile: UserProfile) : DTPacket(
    authHeader,
    profile
) {
    override val id: PacketID = PacketID.ACK_AUTH
}
