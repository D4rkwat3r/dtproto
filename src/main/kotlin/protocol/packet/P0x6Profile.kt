package protocol.packet

import protocol.DTPacket

class P0x6Profile(val userId: Long) : DTPacket(userId) {
    override val id: PacketID = PacketID.PROFILE
}
