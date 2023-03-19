import server.encoder.PacketEncoder
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import server.decoder.PacketDecoder
import server.handler.LoginHandler
import server.handler.MainMessageHandler

fun main(args: Array<String>) {
    val bossGroup = NioEventLoopGroup()
    val workerGroup = NioEventLoopGroup()
    println("Starting server at ${args[0]}:${args[1]}")
    try {
        val bootstrap = ServerBootstrap()
            .group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(object: ChannelInitializer<SocketChannel>() {
                override fun initChannel(ch: SocketChannel) {
                    val handler = MainMessageHandler()
                        .handle(LoginHandler())
                    ch.pipeline().addLast(PacketEncoder()).addLast(PacketDecoder(), handler)
                }
            })
        bootstrap.bind(args[0], args[1].toInt()).sync().channel().closeFuture().sync()
    } finally {
        println("Stopping the server...")
        bossGroup.shutdownGracefully()
        workerGroup.shutdownGracefully()
        println("Server stopped.")
    }
}
