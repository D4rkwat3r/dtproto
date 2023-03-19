package server.handler

import io.netty.channel.Channel
import protocol.DTPacket

abstract class BasePacketHandler {
    abstract fun onPacket(packet: DTPacket, channel: Channel): DTPacket
}
