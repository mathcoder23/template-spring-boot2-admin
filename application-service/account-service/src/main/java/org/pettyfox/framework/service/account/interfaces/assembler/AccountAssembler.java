package org.pettyfox.framework.service.account.interfaces.assembler;

import org.pettyfox.framework.service.account.doamin.account.po.Account;
import org.pettyfox.framework.service.account.interfaces.dto.vo.AccountVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class AccountAssembler {

    public static AccountVO convert(Account account) {
        AccountVO vo = new AccountVO();
        BeanUtils.copyProperties(account, vo);
        return vo;
    }

    public static List<AccountVO> convert(List<Account> account) {
        return account.stream().map(AccountAssembler::convert).collect(Collectors.toList());
    }
}
