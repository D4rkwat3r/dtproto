package server.handler

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import protocol.DTPacket
import protocol.exception.DTException
import protocol.packet.P0x10Exception
import server.annotation.Handles
import java.net.InetSocketAddress
import kotlin.reflect.full.findAnnotation

class MainMessageHandler : ChannelInboundHandlerAdapter() {
    private val handlers = mutableListOf<BasePacketHandler>()

    override fun channelActive(ctx: ChannelHandlerContext) {
        val address = ctx.channel().remoteAddress() as InetSocketAddress
        println("[Log]: Client ${address.hostString}:${address.port} connected.")
    }

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        if (msg !is DTPacket) {
            println("[Log]: Received invalid message.")
            return
        }
        val address = ctx.channel().remoteAddress() as InetSocketAddress
        println("[Log] Packet with ID {${msg.id}} from ${address.hostString}:${address.port} received.")
        handlers.forEach { handler ->
            if (msg.id in handler::class.findAnnotation<Handles>()!!.ids) {
                val reply = try {
                    handler.onPacket(msg, ctx.channel())
                } catch (e: DTException) {
                    P0x10Exception(e)
                }
                ctx.writeAndFlush(reply.setRequestId(msg.requestId))
            }
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        println("[Log]: Exception occurred: \"${cause?.message ?: "NO_MESSAGE"}\"")
        ctx!!.close()
    }

    fun handle(handler: BasePacketHandler): MainMessageHandler {
        handlers.add(handler)
        return this
    }
}
