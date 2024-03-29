package org.pettyfox.examples.service.account.doamin.account.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pettyfox.examples.service.account.doamin.account.po.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Petty Fox
 * @since 2021-04-14
 */
public interface PermissionBiz extends IService<Permission> {
    List<Permission> listByRoleIds(List<Long> roleIds);
}
