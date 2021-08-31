package org.pettyfox.examples.service.account.doamin.account.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.pettyfox.base.web.dao.BaseService;
import org.pettyfox.examples.service.account.doamin.account.biz.PermissionBiz;
import org.pettyfox.examples.service.account.doamin.account.biz.RolePermissionBiz;
import org.pettyfox.examples.service.account.doamin.account.po.Permission;
import org.pettyfox.examples.service.account.doamin.account.po.RolePermission;
import org.pettyfox.examples.service.account.doamin.account.repository.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Petty Fox
 * @since 2021-04-15
 */
@Service
public class PermissionBizImpl extends BaseService<PermissionMapper, Permission> implements PermissionBiz {

    @Resource
    private RolePermissionBiz rolePermissionService;

    @Override
    public List<Permission> listByRoleIds(List<Long> roleIds) {
        if (null == roleIds || roleIds.isEmpty()) {
            return null;
        }
        List<Long> pIds = rolePermissionService
                .list(roleIds)
                .stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());
        if (pIds.isEmpty()) {
            return null;
        }
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Permission::getId, pIds);
        return list(queryWrapper);
    }
}
