package org.pettyfox.examples.service.account.doamin.account.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.pettyfox.base.comm.utils.GatherCompareResult;
import org.pettyfox.base.comm.utils.GatherComputer;
import org.pettyfox.base.web.dao.BaseService;
import org.pettyfox.examples.service.account.doamin.account.biz.RolePermissionBiz;
import org.pettyfox.examples.service.account.doamin.account.po.RolePermission;
import org.pettyfox.examples.service.account.doamin.account.repository.RolePermissionMapper;
import org.pettyfox.examples.service.account.interfaces.dto.params.RolePermissionEditParams;
import org.pettyfox.examples.service.account.interfaces.dto.params.RolePermissionQueryParams;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Petty Fox
 * @since 2021-04-14
 */
@Service
public class RolePermissionBizImpl extends BaseService<RolePermissionMapper, RolePermission> implements RolePermissionBiz {

    @Override
    public void savePermission(RolePermissionEditParams p) {
        p.validator();
        List<Long> existIds = getPermissionIdsByRoleId(p.getId());
        GatherComputer.gatherCompare(existIds, p.getPermissionIds(), new GatherCompareResult<Long>() {
            @Override
            public void onRemoves(List<Long> removeList) {
                remove(p.getId(), removeList);
            }

            @Override
            public void onNoChange(List<Long> noChangeList) {

            }

            @Override
            public void onAdds(List<Long> addList) {
                List<RolePermission> list = addList.stream().map(pid -> {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setPermissionId(pid);
                    rolePermission.setRoleId(p.getId());
                    return rolePermission;
                }).collect(Collectors.toList());
                saveBatch(list);
            }

            @Override
            public void finish() {

            }
        });
    }

    private List<Long> getPermissionIdsByRoleId(Long roleId) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(RolePermission::getPermissionId);
        queryWrapper.eq(RolePermission::getRoleId, roleId);
        return this.list(queryWrapper).stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
    }

    private void remove(Long roleId, List<Long> pIds) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId, roleId);
        queryWrapper.in(RolePermission::getPermissionId, pIds);
        remove(queryWrapper);
    }

    @Override
    public PageInfo<RolePermission> listPage2(RolePermissionQueryParams p) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId, p.getRoleId());
        PageHelper.startPage(p.getPageNo(), p.getPageSize());
        List<RolePermission> list = list(queryWrapper);
        return new PageInfo<>(list, p.getPageNo());
    }

    @Override
    public List<RolePermission> list(List<Long> roleIds) {
        if (null == roleIds || roleIds.isEmpty()) {
            return null;
        }
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(RolePermission::getRoleId, roleIds);
        return list(queryWrapper);
    }

}
