package org.pettyfox.examples.service.account.interfaces.facade;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.pettyfox.base.comm.log.ApiLog;
import org.pettyfox.base.comm.log.ApiLogType;
import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.base.web.dto.params.BaseIdsParams;
import org.pettyfox.examples.service.account.doamin.account.biz.PermissionBiz;
import org.pettyfox.examples.service.account.doamin.account.po.Permission;
import org.pettyfox.examples.service.account.interfaces.dto.data.PermissionTreeData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
@Api(tags = "系统权限")
public class PermissionController extends BaseController {

    @Resource
    private PermissionBiz permissionBiz;

    @PostMapping("/permission/getTree")
    @ApiOperation("系统权限树")
    public RestObjectResponse<List<PermissionTreeData>> getTree() {
        return RestObjectResponse.ok(PermissionTreeData.buildByList(permissionBiz.list()));
    }

    @PostMapping("/permission/save")
    @ApiLog(log = "保存", optionType = ApiLogType.OptionType.SAVE)
    public RestObjectResponse<Permission> save(@RequestBody Permission entity) {
        boolean result = permissionBiz.saveOrUpdate(entity);
        return RestObjectResponse.ok(entity);
    }

    @ApiLog(log = "删除", optionType = ApiLogType.OptionType.DELETE_BATCH)
    @PostMapping("/permission/delete")
    public RestObjectResponse<String> delete(@RequestBody BaseIdsParams p) {
        permissionBiz.removeByIds(p.getIds());
        return RestObjectResponse.ok("删除成功");
    }

}
