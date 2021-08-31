package org.pettyfox.examples.service.account.doamin.account.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.pettyfox.base.web.dto.params.BasePageParam;
import org.pettyfox.examples.service.account.doamin.account.po.Role;
import org.pettyfox.examples.service.account.interfaces.dto.vo.RoleVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Petty Fox
 * @since 2021-04-14
 */
public interface RoleBiz extends IService<Role> {

    PageInfo<RoleVO> list(BasePageParam p);
}
