package org.pettyfox.base.comm.oauth2;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pettyfox
 * @version 1.0
 * @date 2020/8/13 18:19
 */
@Slf4j
public class Auth2HttpUtil {
    private Auth2Session session;
    private Auth2User user;
    private String loginPrefix;

    public Auth2HttpUtil(Auth2User user, String loginPrefix) {
        this.user = user;
        this.loginPrefix = loginPrefix;
    }
    public HttpRequest setAuth(HttpRequest request){
        checkSession();
        return request.header("Authorization", String.format("%s %s", session.getTokenType(), session.getAccessToken()));
    }

    public void checkSession() {
        if (null == session) {
            login();
            return;
        }
        if(session.isExpire()){
            login();
            return;
        }
        if(session.isApproachExpire()){
            refreshToken();
        }
    }

    public void login() {
        Map<String, Object> params = new HashMap<>(10);
        params.put("grant_type", user.getGrantType());
        params.put("username", user.getUsername());
        params.put("password", user.getPassword());
        params.put("client_id", user.getClientId());
        params.put("client_secret", user.getClientSecret());
        if (null != user.getScope()) {
            params.put("scope", user.getScope());
        }
        log.info("login to :{}", JSON.toJSONString(user));
        try {
            String rep = HttpUtil.post(user.getUrlLogin(), params);

            if (StringUtils.isNotBlank(loginPrefix)) {
                JSONObject json = JSON.parseObject(rep);
                this.session = JSON.parseObject(json.getString(loginPrefix), Auth2Session.class);
            } else {
                this.session = JSON.parseObject(rep, Auth2Session.class);
            }
            if(null == session){
                log.info("login response:{}", rep);
            }else{
                session.setLoginDate(new Date());
            }
            log.info("login successful.session:{}", JSON.toJSONString(session));
        } catch (Exception e) {
            log.error("login failed.", e);
        }
    }
    public void refreshToken(){
        Map<String, Object> params = new HashMap<>(10);
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", session.getRefreshToken());
        params.put("client_id", user.getClientId());
        params.put("client_secret", user.getClientSecret());
        if (null != user.getScope()) {
            params.put("scope", user.getScope());
        }
        log.info("refreshToken to :{}", JSON.toJSONString(user));
        try {
            String rep = HttpUtil.post(user.getUrlLogin(), params);
            if (StringUtils.isNotBlank(loginPrefix)) {
                JSONObject json = JSON.parseObject(rep);
                this.session = JSON.parseObject(json.getString(loginPrefix), Auth2Session.class);
            } else {
                this.session = JSON.parseObject(rep, Auth2Session.class);
            }
            if(null == session){
                log.info("refreshToken response:{}", rep);
            }else{
                session.setLoginDate(new Date());
            }
            log.info("refreshToken successful.session:{}", JSON.toJSONString(session));
        } catch (Exception e) {
            log.error("refreshToken failed.", e);
        }
    }
}
