package server.decoder

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import protocol.DTPacket

class PacketDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext?, buf: ByteBuf?, out: MutableList<Any>?) {
        val byteArray = ByteArray(buf!!.readableBytes())
        buf.readBytes(byteArray)
        out!!.add(DTPacket.deserialize(byteArray))
    }
}
