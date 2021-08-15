package org.pettyfox.framework.service.account.interfaces.dto.params;

import lombok.Getter;
import lombok.Setter;
import org.pettyfox.base.web.dto.params.BaseQueryParam;

/**
 * @author eface .FW
 * @version 1.0
 * @date 2021/4/15 15:50
 */
@Getter
@Setter
public class RolePermissionQueryParams extends BaseQueryParam {
    private Long roleId;
}
