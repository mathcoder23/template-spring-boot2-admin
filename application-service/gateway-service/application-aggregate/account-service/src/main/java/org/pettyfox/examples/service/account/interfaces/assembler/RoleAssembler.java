package org.pettyfox.examples.service.account.interfaces.assembler;

import org.pettyfox.examples.service.account.interfaces.dto.vo.RoleVO;
import org.pettyfox.examples.service.account.doamin.account.po.Role;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class RoleAssembler {
    public static RoleVO convert(Role account) {
        RoleVO vo = new RoleVO();
        BeanUtils.copyProperties(account, vo);
        return vo;
    }

    public static List<RoleVO> convert(List<Role> account) {
        return account.stream().map(RoleAssembler::convert).collect(Collectors.toList());
    }
}
