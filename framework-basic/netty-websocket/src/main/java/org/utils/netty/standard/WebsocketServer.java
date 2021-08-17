package org.utils.netty.standard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.utils.netty.pojo.PojoEndpointServer;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class WebsocketServer {

    private final PojoEndpointServer pojoEndpointServer;

    private final ServerEndpointConfig config;

    public WebsocketServer(PojoEndpointServer webSocketServerHandler, ServerEndpointConfig serverEndpointConfig) {
        this.pojoEndpointServer = webSocketServerHandler;
        this.config = serverEndpointConfig;
    }


    public void init(ApplicationContext context) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup(config.getBossLoopGroupThreads(), new NamedThreadFactory("netty-boss"));
        EventLoopGroup worker = new NioEventLoopGroup(config.getWorkerLoopGroupThreads(), new NamedThreadFactory("netty-worker"));
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, config.getConnectTimeoutMillis())
                .option(ChannelOption.SO_BACKLOG, config.getSoBacklog())
                .childOption(ChannelOption.WRITE_SPIN_COUNT, config.getWriteSpinCount())
                .childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(config.getWriteBufferLowWaterMark(), config.getWriteBufferHighWaterMark()))
                .childOption(ChannelOption.TCP_NODELAY, config.isTcpNodelay())
                .childOption(ChannelOption.SO_KEEPALIVE, config.isSoKeepalive())
                .childOption(ChannelOption.SO_LINGER, config.getSoLinger())
                .childOption(ChannelOption.ALLOW_HALF_CLOSURE, config.isAllowHalfClosure())
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        if (config.isEnableSsl()) {
//                            String sslEnable = System.getProperty("sslEnable");
//                            if ("1".equals(sslEnable)) {
//                                ch.pipeline().addLast("ssl", SslUtil.createSSLContext(config.getCaPath(), config.getKeyPath()).newHandler(ByteBufAllocator.DEFAULT));
//                            }
//                        }
                        ch.pipeline().addLast(new IdleStateHandler(15, 0, 0, TimeUnit.SECONDS));
                        ch.pipeline().addLast("http-codec", new HttpServerCodec());
                        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
                        ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                        ch.pipeline().addLast(new WebSocketFrameAggregator(1024 * 1024 * 100));
                        if (config.isEnableHeartBeat()) {
                            ch.pipeline().addLast(new HeartBeatServerHandler(pojoEndpointServer));
                        }
                        ch.pipeline().addLast(new HttpServerHandler(pojoEndpointServer, config));

                    }
                });

        if (config.getSoRcvbuf() != -1) {
            bootstrap.childOption(ChannelOption.SO_RCVBUF, config.getSoRcvbuf());
        }

        if (config.getSoSndbuf() != -1) {
            bootstrap.childOption(ChannelOption.SO_SNDBUF, config.getSoSndbuf());
        }

        ChannelFuture channelFuture;
        int port = config.getPort();
        if ("0.0.0.0".equals(config.getHost())) {
            channelFuture = bootstrap.bind(port);

        } else {
            try {
                channelFuture = bootstrap.bind(new InetSocketAddress(InetAddress.getByName(config.getHost()), port));
            } catch (UnknownHostException e) {
                channelFuture = bootstrap.bind(config.getHost(), port);
                log.error("Netty host bind exception. using config host :" + config.getHost(), e);
            }
        }

        Channel channel = channelFuture.channel();
        int finalPort = port;
        channelFuture.addListener(future -> {
            if (!future.isSuccess()) {
                log.error("Netty port bind exception,port:" + finalPort, future.cause());
                System.exit(SpringApplication.exit(context));
            } else {
                if (channel instanceof NioServerSocketChannel) {
                    NioServerSocketChannel socketChannel = (NioServerSocketChannel) channel;
                    int randomPort = socketChannel.localAddress().getPort();
                    log.info("netty websocket port:{}", randomPort);
                }
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            boss.shutdownGracefully().syncUninterruptibly();
            worker.shutdownGracefully().syncUninterruptibly();
        }));

    }

    public PojoEndpointServer getPojoEndpointServer() {
        return pojoEndpointServer;
    }

    /**
     * 自定义线程池
     */
    public static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;


        public NamedThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = name + "-p" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }


}
