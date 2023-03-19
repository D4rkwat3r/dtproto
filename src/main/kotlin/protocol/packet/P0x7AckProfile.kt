package protocol.packet

import protocol.DTPacket
import protocol.model.UserProfile

class P0x7AckProfile(val profile: UserProfile) : DTPacket(profile) {
    override val id: PacketID = PacketID.ACK_PROFILE
}
