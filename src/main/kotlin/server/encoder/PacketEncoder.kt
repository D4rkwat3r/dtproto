package server.encoder

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import protocol.DTPacket

class PacketEncoder : MessageToByteEncoder<DTPacket>() {
    override fun encode(ctx: ChannelHandlerContext?, msg: DTPacket?, out: ByteBuf?) {
        out!!.writeBytes(msg!!.serialize())
    }
}
