package org.pettyfox.framework.service.account.doamin.account.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.pettyfox.base.web.dao.BaseService;
import org.pettyfox.base.web.dto.params.BasePageParam;
import org.pettyfox.framework.service.account.doamin.account.biz.AccountBiz;
import org.pettyfox.framework.service.account.doamin.account.po.Account;
import org.pettyfox.framework.service.account.doamin.account.repository.AccountMapper;
import org.pettyfox.framework.service.account.interfaces.assembler.AccountAssembler;
import org.pettyfox.framework.service.account.interfaces.dto.vo.AccountVO;
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

    @Override
    public PageInfo<AccountVO> list(BasePageParam p) {

        PageHelper.startPage(p.getPageNo(),p.getPageSize());
        return new PageInfo<AccountVO>(AccountAssembler.convert(this.list()));
    }
}
