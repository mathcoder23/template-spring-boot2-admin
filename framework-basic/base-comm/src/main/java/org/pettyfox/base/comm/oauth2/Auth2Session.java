package org.pettyfox.base.comm.oauth2;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 基于oauth2登录的会话信息
 *
 * @author pettyfox
 * @version 1.0
 * @date 2020/8/13 18:23
 */
@Setter
@Getter
public class Auth2Session implements Serializable {
    /**
     * 过期阀值预警
     */
    public static final float EXPIRE_THRESHOLD = 0.75f;
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private Integer expiresIn;
    private String scope;
    /**
     * 登录日期
     */
    private Date loginDate;
    /**
     * 原始数据
     */
    private String originalData;

    public boolean isExpire() {
        return System.currentTimeMillis() - loginDate.getTime() > this.expiresIn;
    }

    public boolean isApproachExpire() {
        return System.currentTimeMillis() - loginDate.getTime() > this.expiresIn * EXPIRE_THRESHOLD;
    }
}
