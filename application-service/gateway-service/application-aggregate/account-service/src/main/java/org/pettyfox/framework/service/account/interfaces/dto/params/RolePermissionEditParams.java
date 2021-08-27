package org.pettyfox.framework.service.account.interfaces.dto.params;

import lombok.Getter;
import lombok.Setter;
import org.pettyfox.base.web.dto.params.BaseIdParams;

import java.util.List;

/**
 * @author eface .FW
 * @version 1.0
 * @date 2021/4/15 15:12
 */
@Getter
@Setter
public class RolePermissionEditParams extends BaseIdParams {
    List<Long> permissionIds;
}
