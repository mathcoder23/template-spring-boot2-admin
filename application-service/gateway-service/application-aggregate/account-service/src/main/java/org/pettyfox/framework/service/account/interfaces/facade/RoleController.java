package org.pettyfox.framework.service.account.interfaces.facade;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.pettyfox.base.comm.log.ApiLog;
import org.pettyfox.base.comm.log.ApiLogType;
import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.base.web.dto.params.BaseIdsParams;
import org.pettyfox.base.web.dto.params.BasePageParam;
import org.pettyfox.framework.service.account.doamin.account.biz.RoleBiz;
import org.pettyfox.framework.service.account.doamin.account.po.Role;
import org.pettyfox.framework.service.account.interfaces.dto.vo.RoleVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Petty Fox
 * @since 2021-04-14
 */
@RestController
@Api(tags = "系统角色")
public class RoleController extends BaseCrudController<Role, RoleVO> {

    @Resource
    private RoleBiz roleService;

    @PostMapping("/role/list")
    @ApiOperation("系统角色列表")
    @Override
    public RestObjectResponse<PageInfo<RoleVO>> list(@RequestBody BasePageParam p) {
        return RestObjectResponse.ok(roleService.list(p));
    }

    @PostMapping("/role/save")
    @Override
    @ApiLog(log = "保存", optionType = ApiLogType.OptionType.SAVE)
    public RestObjectResponse<Role> save(@RequestBody Role entity) {
        boolean result = roleService.saveOrUpdate(entity);
        return RestObjectResponse.ok(null);
    }

    @ApiLog(log = "删除", optionType = ApiLogType.OptionType.DELETE_BATCH)
    @PostMapping("/role/delete")
    @Override
    public RestObjectResponse<String> delete(@RequestBody BaseIdsParams p) {
        roleService.removeByIds(p.getIds());
        return RestObjectResponse.ok("删除成功");
    }

}
