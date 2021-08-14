package org.pettyfox.framework.gateway.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.pettyfox.framework.gateway.oauth2.password.MyPasswordEncoderFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Zhifeng.Zeng
 * @description Security核心配置
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().cors()
                .and()
                .csrf().disable();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return MyPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        PasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin").password("{MD5}"+passwordEncoder.encode("admin")).authorities("admin").build());
//        return manager;
//    }


}