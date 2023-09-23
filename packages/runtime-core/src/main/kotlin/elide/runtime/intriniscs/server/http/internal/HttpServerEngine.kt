package elide.runtime.intriniscs.server.http.internal

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelOption
import java.net.InetSocketAddress
import elide.runtime.Logging
import elide.runtime.core.DelicateElideApi
import elide.runtime.intriniscs.server.http.netty.NettyChannelInitializer
import elide.runtime.intriniscs.server.http.netty.NettyRequestHandler
import elide.runtime.intriniscs.server.http.netty.NettyTransport

@DelicateElideApi internal class HttpServerEngine(private val router: HttpRouter) {
  /** Private logger instance. */
  private val logging by lazy { Logging.of(HttpServerEngine::class) }

  private fun prepareChannelInitializer(): ChannelHandler {
    return NettyChannelInitializer(NettyRequestHandler(router))
  }

  internal fun start(port: Int) {
    // acquire platform-specific Netty components
    val transport = NettyTransport.resolve()
    logging.debug { "Using transport: $transport" }

    with(ServerBootstrap()) {
      // server channel options
      option(ChannelOption.SO_BACKLOG, SERVER_BACKLOG)
      option(ChannelOption.SO_REUSEADDR, true)

      // apply transport options
      logging.debug { "Applying options from $transport" }
      transport.bootstrap(this)

      // attach custom handler pipeline and configure client channels
      childHandler(prepareChannelInitializer())
      childOption(ChannelOption.SO_REUSEADDR, true)

      // start listening
      val address = InetSocketAddress(port)
      bind(address).sync().channel()
      logging.debug { "Server listening at $address" }
    }
  }

  private companion object {
    private const val SERVER_BACKLOG = 8192
  }
}