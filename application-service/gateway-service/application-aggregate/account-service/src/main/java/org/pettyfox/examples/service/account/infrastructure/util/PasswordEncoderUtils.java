package org.pettyfox.examples.service.account.infrastructure.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * @author Petty Fox
 * @ClassName PasswordEncoderUtils
 * @Description 用户信息加密算法
 * @Date 2020/3/24 15:33
 * @Version 1.0
 */
public class PasswordEncoderUtils {
    public static String password(String username, String password) {
        return "{MD5}" + SecureUtil.md5(passwordHash(username, password)).toLowerCase();
    }

    public static String passwordHash(String username, String password) {
        return Base64.encode(SecureUtil.md5(username) + ":" + password);
    }

    public static String urlSign(String timestamp, String clientId, String uriPath, String secret) {
        String text = timestamp + "#" + clientId + "#" + uriPath;
        return Base64.encode(("{md5}" + DigestUtil.md5Hex(DigestUtil.md5Hex(text.toLowerCase()) + secret)).toLowerCase());
    }

    public static void main(String[] args) {
        System.out.println(password("admin", "admin"));
    }
}
