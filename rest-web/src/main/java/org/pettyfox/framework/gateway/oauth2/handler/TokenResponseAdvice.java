package org.pettyfox.framework.gateway.oauth2.handler;

import lombok.extern.slf4j.Slf4j;
import org.pettyfox.base.web.dto.rest.RestObjectResponse;
import org.pettyfox.framework.service.account.doamin.account.biz.SystemLogBiz;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

@ControllerAdvice
@Slf4j
public class TokenResponseAdvice implements ResponseBodyAdvice {

    @Resource
    private SystemLogBiz systemLogBiz;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//
        if (body instanceof DefaultOAuth2AccessToken) {
            //记录登录日志
            try {
                InetSocketAddress address = request.getRemoteAddress();
                systemLogBiz.saveSystemLog(address.getHostString() + ":" + address.getPort(), "/auth/token", String.format("账户[%s]登录成功!", ((DefaultOAuth2AccessToken) body).getAdditionalInformation().get("userName")));
            } catch (Exception e) {
                log.error("record system log failed", e);
            }
            return RestObjectResponse.ok(body);
        }
        return body;
    }

}