package org.pettyfox.framework.gateway.oauth2.auth;

import org.pettyfox.framework.service.account.doamin.account.biz.AccountBiz;
import org.pettyfox.framework.service.account.doamin.account.po.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName AuthService
 * @Description 登录认证服务, 默认登录
 * @Author pettyfox
 * @Date 2020/3/23 13:49
 * @Version 1.0
 */
@Service
public class AuthServiceImpl implements UserDetailsService {

    @Resource
    private AccountBiz userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = userService.getByUsername(s);
        if (null == account) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new User(account.getId() + "", account.getPassword(), getAuthorities());
    }

    private Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authList;
    }
}
