package org.pettyfox.framework.service.message.interfaces.facade;

import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.framework.service.message.domain.biz.AuthorizeBiz;
import org.pettyfox.framework.service.message.domain.biz.MessageConfigBiz;
import org.pettyfox.framework.service.message.interfaces.dto.vo.MessageConnectConfigVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class WebsocketConfigController extends BaseController{

    @Resource
    private AuthorizeBiz authorizeBiz;

    @Resource
    private MessageConfigBiz messageConfigBiz;

    @PostMapping("/websocket/getConfig")
    public RestObjectResponse<MessageConnectConfigVo> getConfig() {
        MessageConnectConfigVo vo = new MessageConnectConfigVo();
        vo.setServerIp(messageConfigBiz.getWebsocketIp());
        vo.setProto(messageConfigBiz.getWebsocketProto());
        vo.setServerPort(messageConfigBiz.getWebsocketPort());
        vo.setUri("/message");
        vo.setToken(authorizeBiz.createToken(null, null));
        return RestObjectResponse.ok(vo);
    }
}
