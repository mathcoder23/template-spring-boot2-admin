package org.pettyfox.examples.service.message.interfaces.dto.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("消息连接配置")
public class MessageConnectConfigVo {

    private String serverIp;
    private String proto;
    private String serverPort;
    private String token;
    private String uri;
}
