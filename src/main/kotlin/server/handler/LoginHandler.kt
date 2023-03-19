package server.handler

import io.netty.channel.Channel
import protocol.Auth
import protocol.DTPacket
import protocol.model.UserProfile
import protocol.packet.P0x3Login
import protocol.packet.P0x5AckAuth
import server.annotation.Handles

@Handles(DTPacket.PacketID.LOGIN)
class LoginHandler : BasePacketHandler() {
    override fun onPacket(packet: DTPacket, channel: Channel): DTPacket {
        packet as P0x3Login
        val authData = Auth.PacketAuth(12345, DTPacket.Role.SUPER_ADMIN, System.currentTimeMillis())
        return P0x5AckAuth(authData.signature("DTKey".toByteArray(Charsets.UTF_8)), UserProfile(
            packet.deepId,
            packet.deepId,
            1,
            DTPacket.Role.SUPER_ADMIN
        ))
    }
}
