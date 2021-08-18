package org.pettyfox.framework.service.message.interfaces.facade;

import com.google.common.util.concurrent.RateLimiter;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.pettyfox.framework.service.message.domain.biz.AuthorizeBiz;
import org.pettyfox.framework.service.message.domain.biz.SessionManagerBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.utils.netty.annotation.*;
import org.utils.netty.enums.PortType;
import org.utils.netty.pojo.ParameterMap;
import org.utils.netty.pojo.Session;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 本类只能使用Autowired不能使用Resource注解注入
 */
@Slf4j
@ServerEndpoint(serverName = WebWebsocketServer.HTML_WS_NAME, value = "/message", portType = PortType.CONFIG, port = 9912, bossLoopGroupThreads = 2, workerLoopGroupThreads = 10,
        childOptionWriteBufferHighWaterMark = 128 * 1024, childOptionWriteBufferLowWaterMark = 64 * 1024, maxFramePayloadLength = 200 * 1024)
@Component
public class WebWebsocketServer {

    public final static String HTML_WS_NAME = "WebWebsocketServer";

    /**
     * 必须静态。注入实例是不同的。
     */
    private static final ConcurrentHashMap<String, Session> SESSION_POOL = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> MSG_REG_POOL = new ConcurrentHashMap<>();
    private static RateLimiter rateLimiter = RateLimiter.create(0.2);
    @Autowired
    private AuthorizeBiz authorizeBiz;
    @Autowired
    private SessionManagerBiz sessionManagerBiz;


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, HttpHeaders httpHeaders, ParameterMap parameterMap) {

        InetSocketAddress address = (InetSocketAddress) session.channel().remoteAddress();
        String clientAddress = address.getHostString() + ":" + address.getPort();

        String token = parameterMap.getParameter("token");
        if (StringUtils.isBlank(token)) {
            log.warn("new websocket connecting reject,token is null.ip:{}", clientAddress);
            session.close();
            return;
        }

        //token 鉴权
        if (!authorizeBiz.checkToken(token)) {
            log.warn("new websocket connecting reject,token is reject.ip:{}", clientAddress);
            session.close();
            return;
        }

        SESSION_POOL.put(token, session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        log.info("websocket close");
        removeFromSessionPool(session);
    }

    /**
     * 错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        removeFromSessionPool(session);
    }

    private void removeFromSessionPool(Session session) {

        Iterator<Map.Entry<String, Session>> iterator = SESSION_POOL.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Session> entry = iterator.next();
            if (entry.getValue().channel().id().asLongText().equals(session.channel().id().asLongText())) {
                iterator.remove();
                break;
            }
        }

    }

}  