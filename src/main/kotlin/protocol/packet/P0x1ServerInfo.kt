package protocol.packet

import protocol.DTPacket

class P0x1ServerInfo : DTPacket() {
    override val id: PacketID = PacketID.SERVER_INFO
}
