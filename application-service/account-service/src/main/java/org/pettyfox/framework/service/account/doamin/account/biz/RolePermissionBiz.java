package org.pettyfox.framework.service.account.doamin.account.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.pettyfox.framework.service.account.doamin.account.po.RolePermission;
import org.pettyfox.framework.service.account.interfaces.dto.params.RolePermissionEditParams;
import org.pettyfox.framework.service.account.interfaces.dto.params.RolePermissionQueryParams;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Petty Fox
 * @since 2021-04-14
 */
public interface RolePermissionBiz extends IService<RolePermission> {

    void savePermission(RolePermissionEditParams p);
    PageInfo<RolePermission> listPage2(RolePermissionQueryParams query);
    List<RolePermission> list(List<Long> roleIds);
}
