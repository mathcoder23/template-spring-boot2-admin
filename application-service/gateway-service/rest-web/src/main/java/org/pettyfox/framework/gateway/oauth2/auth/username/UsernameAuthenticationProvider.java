package org.pettyfox.framework.gateway.oauth2.auth.username;

import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 授权提供者
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2019/7/29 下午10:57
 */
@Setter
public class UsernameAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户信息
        UserDetails user = userDetailsService.loadUserByUsername(authentication.getName());
        if (user == null) {
            throw new InternalAuthenticationServiceException("无效认证");
        }
        UsernameAuthenticationToken authenticationResult = new UsernameAuthenticationToken(authentication.getName(), user, user.getAuthorities());
        authenticationResult.setDetails(user);
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 通过类型进行匹配
        return UsernameAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
