package org.pettyfox.base.comm.oauth2;

import lombok.*;

import java.io.Serializable;

/**
 * 登录用户
 * @author pettyfox
 * @version 1.0
 * @date 2020/8/13 18:19
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auth2User implements Serializable {
    private String urlLogin;
    private String grantType;
    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
    private String scope;
    private String uuid;

}
