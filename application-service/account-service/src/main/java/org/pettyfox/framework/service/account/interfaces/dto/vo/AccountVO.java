package org.pettyfox.framework.service.account.interfaces.dto.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AccountVO implements Serializable {

    private Long id;
    private String username;
    private String nick;
    private Long roleId;
}
