package org.pettyfox.framework.gateway.oauth2.auth.username;

import lombok.extern.slf4j.Slf4j;
import org.pettyfox.base.web.context.UserSession;
import org.pettyfox.framework.service.account.doamin.account.biz.AccountBiz;
import org.pettyfox.framework.service.account.doamin.account.po.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 账号密码登录
 *
 * @author Petty Fox
 * @version 1.0
 */
@Component
@Slf4j
public class UsernameAuthServiceImpl implements UserDetailsService {

    @Resource
    private AccountBiz userBiz;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account user = userBiz.getByUsername(s);
        if (null == user) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        if (!user.getEnable()) {
            throw new UsernameNotFoundException("账户已冻结");
        }
        return new UserSession(user.getUsername(), user.getPassword(), user.getId(), user.getRoleId(), getAuthorities(user.getUsername()));
    }

    private Collection<GrantedAuthority> getAuthorities(String username) {
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authList;
    }
}
