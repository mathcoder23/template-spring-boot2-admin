package org.utils.netty.standard;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.utils.netty.pojo.PojoEndpointServer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * netty 心跳包处理
 *
 * @author eface
 * @version 1.0
 * @date 2020/5/9 11:51
 */
@Slf4j
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {
    private static final int MAX_LOSS_COUNT = 2;
    private AtomicInteger lossConnectCount = new AtomicInteger(0);
    private PojoEndpointServer pojoEndpointServer;

    public HeartBeatServerHandler(PojoEndpointServer pojoEndpointServer) {
        this.pojoEndpointServer = pojoEndpointServer;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                if (lossConnectCount.incrementAndGet() >= MAX_LOSS_COUNT) {
                    log.info("websocket:close loss connection channel！ip:{}", ctx.channel().remoteAddress().toString());
                    ctx.channel().close();

                    this.pojoEndpointServer.doOnClose(ctx);
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        lossConnectCount.lazySet(0);
        super.channelRead(ctx, msg);
    }
}
