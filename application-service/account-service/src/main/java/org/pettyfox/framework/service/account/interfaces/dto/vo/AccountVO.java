package org.pettyfox.framework.service.account.interfaces.dto.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("账户信息")
public class AccountVO implements Serializable {

    private Long id;

    @ApiModelProperty("登陆账户名")
    private String username;

    @ApiModelProperty("用户昵称")
    private String nick;

    @ApiModelProperty("用户角色")
    private Long roleId;
}
