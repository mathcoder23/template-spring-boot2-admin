package org.pettyfox.examples.service.account.interfaces.facade;


import com.github.pagehelper.PageInfo;
import org.pettyfox.base.comm.log.ApiLog;
import org.pettyfox.base.comm.log.ApiLogType;
import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.base.web.BaseController;
import org.pettyfox.examples.service.account.doamin.account.biz.impl.RolePermissionBizImpl;
import org.pettyfox.examples.service.account.interfaces.dto.params.RolePermissionEditParams;
import org.pettyfox.examples.service.account.interfaces.dto.params.RolePermissionQueryParams;
import org.pettyfox.examples.service.account.doamin.account.po.RolePermission;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Petty Fox
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/api/account/role-permission")
public class RolePermissionController extends BaseController<RolePermissionBizImpl, RolePermission> {

    @ApiLog(log = "保存权限", optionType = ApiLogType.OptionType.SAVE)
    @PostMapping("/savePermission")
    public RestObjectResponse<String> savePermission(@RequestBody RolePermissionEditParams p){
        baseService.savePermission(p);
        return RestObjectResponse.ok("");
    }
    @PostMapping("list2")
    public RestObjectResponse<PageInfo<RolePermission>> list2(@RequestBody RolePermissionQueryParams p){
        return RestObjectResponse.ok(baseService.listPage2(p));
    }
}
