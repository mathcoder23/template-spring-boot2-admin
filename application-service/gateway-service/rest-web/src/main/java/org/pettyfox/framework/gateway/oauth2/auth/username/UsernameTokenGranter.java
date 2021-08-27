package org.pettyfox.framework.gateway.oauth2.auth.username;

import org.pettyfox.framework.service.account.infrastructure.util.PasswordEncoderUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2019/7/30 下午1:33
 */
public class UsernameTokenGranter extends AbstractTokenGranter {
    public static final String GRANT_TYPE = "username";
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    /**
     * 构造方法提供一些必要的注入的参数
     * 通过这些参数来完成我们父类的构建
     *
     * @param tokenServices        tokenServices
     * @param clientDetailsService clientDetailsService
     * @param oAuth2RequestFactory oAuth2RequestFactory
     * @param userDetailsService   userDetailsService
     */
    public UsernameTokenGranter(AuthorizationServerTokenServices tokenServices,
                                ClientDetailsService clientDetailsService,
                                OAuth2RequestFactory oAuth2RequestFactory,
                                UserDetailsService userDetailsService,
                                PasswordEncoder encoder) {
        super(tokenServices, clientDetailsService, oAuth2RequestFactory, GRANT_TYPE);
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = encoder;
    }

    /**
     * 在这里查询我们用户，构建用户的授权信息
     *
     * @param client       客户端
     * @param tokenRequest tokenRequest
     * @return OAuth2Authentication
     */
    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> params = tokenRequest.getRequestParameters();
        String username = params.getOrDefault("username", "");
        String password = params.getOrDefault("password", "");
        // 获取用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (Objects.isNull(userDetails)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        password = PasswordEncoderUtils.passwordHash(password, username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 构建用户授权信息
        Authentication user = new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(), userDetails.getAuthorities());
        return new OAuth2Authentication(tokenRequest.createOAuth2Request(client), user);
    }
}