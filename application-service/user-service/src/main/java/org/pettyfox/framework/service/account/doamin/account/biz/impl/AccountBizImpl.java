package org.pettyfox.framework.service.account.doamin.account.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.pettyfox.base.web.dao.BaseService;
import org.pettyfox.framework.service.account.doamin.account.biz.AccountBiz;
import org.pettyfox.framework.service.account.doamin.account.po.Account;
import org.pettyfox.framework.service.account.doamin.account.repository.AccountMapper;
import org.springframework.stereotype.Service;

/**
 * @author Petty Fox.m
 */
@Service
public class AccountBizImpl extends BaseService<AccountMapper, Account> implements AccountBiz {

    @Override
    public Account getByUsername(String s) {
        LambdaQueryWrapper<Account> queryChainWrapper = new LambdaQueryWrapper<>();
        queryChainWrapper.eq(Account::getUsername, s);
        return getOne(queryChainWrapper);
    }
}
