package org.pettyfox.framework.service.account.doamin.account.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.pettyfox.base.comm.exception.DirtyException;
import org.pettyfox.base.web.dao.BaseService;
import org.pettyfox.base.web.dto.params.BasePageParam;
import org.pettyfox.framework.service.account.doamin.account.biz.AccountBiz;
import org.pettyfox.framework.service.account.doamin.account.po.Account;
import org.pettyfox.framework.service.account.doamin.account.repository.AccountMapper;
import org.pettyfox.framework.service.account.infrastructure.util.PasswordEncoderUtils;
import org.pettyfox.framework.service.account.interfaces.assembler.AccountAssembler;
import org.pettyfox.framework.service.account.interfaces.dto.dto.EditAccountDTO;
import org.pettyfox.framework.service.account.interfaces.dto.vo.AccountVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

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

        PageHelper.startPage(p.getPageNo(), p.getPageSize());
        return new PageInfo<>(AccountAssembler.convert(this.list()));
    }

    @Override
    public void updateActiveTime(Long id) {
        Account account = new Account();
        account.setId(id);
        account.setLastActiveTime(new Date());
        this.updateById(account);
    }

    @Override
    public void save(EditAccountDTO d) {
        d.validator();
        Account account = new Account();
        BeanUtils.copyProperties(d, account);
        if (null == d.getId()) {
            if (null != getByUsername(d.getUsername())) {
                throw new DirtyException("账户名已存在");
            }
        }
        if (null != d.getId() || StringUtils.isNotBlank(d.getPassword())) {
            account.setPassword(PasswordEncoderUtils.password(account.getUsername(), account.getPassword()));
        }
        this.saveOrUpdate(account);
    }

}
