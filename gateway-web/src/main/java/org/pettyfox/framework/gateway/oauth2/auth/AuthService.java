package org.pettyfox.framework.gateway.oauth2.auth;

import org.pettyfox.framework.service.user.modules.biz.UserBiz;
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
 * @Description 登录认证服务
 * @Author eface
 * @Date 2020/3/23 13:49
 * @Version 1.0
 */
@Service
public class AuthService implements UserDetailsService {
    @Resource
    private UserBiz userBiz;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       org.pettyfox.framework.service.user.modules.entity.User user = userBiz.getUserByUsernameHashBase64(s);
       if(null == user){
           throw new UsernameNotFoundException("用户名或密码错误");
       }

       return new User(user.getId()+"",user.getPassword(),getAuthorities());
    }
    private Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authList;
    }
}
