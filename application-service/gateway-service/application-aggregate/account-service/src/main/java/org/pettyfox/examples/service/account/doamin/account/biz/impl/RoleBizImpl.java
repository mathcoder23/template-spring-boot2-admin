package org.pettyfox.examples.service.account.doamin.account.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.pettyfox.base.web.dao.BaseService;
import org.pettyfox.base.web.dto.params.BasePageParam;
import org.pettyfox.examples.service.account.doamin.account.biz.RoleBiz;
import org.pettyfox.examples.service.account.doamin.account.po.Role;
import org.pettyfox.examples.service.account.doamin.account.repository.RoleMapper;
import org.pettyfox.examples.service.account.interfaces.assembler.RoleAssembler;
import org.pettyfox.examples.service.account.interfaces.dto.vo.RoleVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Petty Fox
 * @since 2021-04-14
 */
@Service
public class RoleBizImpl extends BaseService<RoleMapper, Role> implements RoleBiz {

    @Override
    public PageInfo<RoleVO> list(BasePageParam p) {
        PageHelper.startPage(p.getPageNo(), p.getPageSize());
        PageInfo<Role> pageInfo = new PageInfo<>(this.list());
        return new PageInfo<>(RoleAssembler.convert(pageInfo.getList()));
    }
}
