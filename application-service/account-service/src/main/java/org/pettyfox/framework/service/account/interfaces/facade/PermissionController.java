package org.pettyfox.framework.service.account.interfaces.facade;


import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.base.web.BaseController;
import org.pettyfox.framework.service.account.doamin.account.biz.impl.PermissionServiceImpl;
import org.pettyfox.framework.service.account.doamin.account.po.Permission;
import org.pettyfox.framework.service.account.interfaces.dto.data.PermissionTreeData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Petty Fox
 * @since 2021-04-15
 */
@RestController
@RequestMapping("/api/account/permission")
public class PermissionController extends BaseController<PermissionServiceImpl, Permission> {

    @PostMapping("/getTree")
    public RestObjectResponse<List<PermissionTreeData>> getTree() {
        return RestObjectResponse.ok(PermissionTreeData.buildByList(baseService.list()));
    }
}
