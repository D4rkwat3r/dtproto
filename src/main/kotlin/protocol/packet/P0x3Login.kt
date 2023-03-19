package protocol.packet

import annotation.Packet
import protocol.DTPacket

@Packet
class P0x3Login(val deepId: String, val password: String) : DTPacket(deepId, password) {
    override val id: PacketID = PacketID.LOGIN
}
