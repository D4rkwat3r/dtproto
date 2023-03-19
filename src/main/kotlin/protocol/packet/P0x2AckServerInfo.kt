package protocol.packet

import protocol.DTPacket

class P0x2AckServerInfo(val protocolVersion: Int, val time: Long) : DTPacket(protocolVersion, time) {
    override val id: PacketID = PacketID.ACK_SERVER_INFO
}
