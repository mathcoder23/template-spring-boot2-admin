package org.pettyfox.base.comm.oauth2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Auth2HttpUtilTest {

    @Test
    void login() {
        Auth2User user =Auth2User.builder()
                .grantType("password")
                .username("admin")
                .password("123456")
                .clientId("admin")
                .clientSecret("123456")
                .urlLogin("http://localhost:8081/oauth/token")
                .build();
        Auth2HttpUtil auth2HttpUtil = new Auth2HttpUtil(user,"data");
        auth2HttpUtil.login();
        auth2HttpUtil.refreshToken();
    }

    @Test
    void refreshToken() {
    }
}