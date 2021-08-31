package org.pettyfox.examples.service.account.interfaces.dto.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.pettyfox.examples.service.account.doamin.account.po.Account;

import java.io.Serializable;
import java.util.Date;

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

    @ApiModelProperty("用户角色")
    private Boolean enable;

    @ApiModelProperty("用户类型")
    private Account.Type type;

    @ApiModelProperty("最近活跃时间")
    private Date lastActiveTime;
}
