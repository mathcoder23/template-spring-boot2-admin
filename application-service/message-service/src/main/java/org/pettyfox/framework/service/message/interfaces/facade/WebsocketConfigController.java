package org.pettyfox.framework.service.message.interfaces.facade;

import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.framework.service.message.interfaces.dto.vo.MessageConnectConfigVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebsocketConfigController {

    @PostMapping("/websocket/getConfig")
    public RestObjectResponse<MessageConnectConfigVo> getConfig() {
        MessageConnectConfigVo vo = new MessageConnectConfigVo();
        vo.setServerIp("localhost");
        vo.setProto("ws");
        vo.setServerPort("9912");
        vo.setUri("/message");
        vo.setToken("123");
        return RestObjectResponse.ok(vo);
    }
}
