package org.pettyfox.framework.gateway.oauth2;

import org.pettyfox.framework.gateway.oauth2.auth.AuthServiceImpl;
import org.pettyfox.framework.gateway.oauth2.handler.AuthExceptionEntryPoint;
import org.pettyfox.framework.gateway.oauth2.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class OAuth2ServerConfig {

    private static final String DEMO_RESOURCE_ID = "order";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        @Resource
        private AuthExceptionEntryPoint authExceptionEntryPoint;
        @Resource
        private CustomAccessDeniedHandler customAccessDeniedHandler;
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
            resources.authenticationEntryPoint(authExceptionEntryPoint)
                    .accessDeniedHandler(customAccessDeniedHandler);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    // Since we want the protected resources to be accessible in the UI as well we need
                    // session creation to be allowed (it's disabled by default in 2.0.6)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .requestMatchers().anyRequest()
                    .and()
                    .anonymous()
                    .and()
                    .authorizeRequests() .antMatchers(
                    "/webjars/**",
                    "/resources/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**",
                    "/v2/api-docs")
                    .permitAll()
                    .antMatchers(
                            "/openApi/produce/device/v1/oauth/login"
                            ,"/openApi/discoveryWebsocketNode/v1/**"
                            ,"/commApi/**").permitAll()
                    .antMatchers("/**").authenticated();//配置order访问控制，必须认证过后才可以访问
            // @formatter:on
        }
    }


    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Resource
        AuthenticationManager authenticationManager;
        @Resource
        AuthServiceImpl authService;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            String key = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456");
            String key2 = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456");
            //配置两个客户端,一个用于password认证一个用于client认证
            clients.inMemory().withClient("client_1")
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("select")
                    .authorities("client")
                    .secret("123456")
                    .and().withClient("client_2")
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("password", "refresh_token")
                    .accessTokenValiditySeconds(60*60*24*7)
                    .refreshTokenValiditySeconds(60*60*24*7)
                    .scopes("admin")
                    .authorities("admin")
                    .secret(key)
                    .and().withClient("version")
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("password", "refresh_token")
                    .accessTokenValiditySeconds(60*60*24*7)
                    .refreshTokenValiditySeconds(60*60*24*7)
                    .scopes("select")
                    .authorities("uploader")
                    .secret(key2);
        }
        @Resource
        WebResponseExceptionTranslator webResponseExceptionTranslator;
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .userDetailsService(authService)
                    .tokenStore(new InMemoryTokenStore())
                    .tokenEnhancer(new CustomTokenEnhancer())
                    .authenticationManager(authenticationManager).exceptionTranslator(webResponseExceptionTranslator);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            //允许表单认证
            oauthServer.allowFormAuthenticationForClients();
        }

    }

}