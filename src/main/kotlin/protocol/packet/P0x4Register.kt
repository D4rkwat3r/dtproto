package protocol.packet

import annotation.Packet
import protocol.DTPacket

@Packet
class P0x4Register(val nickname: String, val deepId: String, val password: String) : DTPacket(nickname, deepId, password) {
    override val id: PacketID = PacketID.REGISTER
}
