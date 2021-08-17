package org.utils.netty.annotation;

import org.springframework.context.annotation.Import;
import org.utils.netty.standard.ServerEndpointExporter;

import java.lang.annotation.*;

/**
 * start netty websocket service（因同端口的所有路径会使用相同配置数据(检索第一个)，且会共用一个socket连接，所以建议websocket服务使用不同的端口）
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({ServerEndpointExporter.class})
public @interface EnableNettyWebsocket {
}