package org.pettyfox.examples.service.account.interfaces.dto.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RoleVO {

    private Long id;
    private String name;
    private Date createTime;
    private Date updateTime;

}
