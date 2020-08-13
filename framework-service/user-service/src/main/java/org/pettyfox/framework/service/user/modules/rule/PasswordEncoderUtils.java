package org.pettyfox.framework.service.user.modules.rule;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;

/**
 * @ClassName PasswordEncoderUtils
 * @Description 用户信息加密算法
 * @Author eface
 * @Date 2020/3/24 15:33
 * @Version 1.0
 */
public class PasswordEncoderUtils {
    public static String oemUsernameHash(String username,String secret){
        return  Base64.encode(("{md5}"+DigestUtil.md5Hex(DigestUtil.md5Hex(username)+secret)).toLowerCase());
    }
    public static String oemPassword(String passwordHash){
        return  "{MD5}"+DigestUtil.md5Hex(passwordHash).toLowerCase();
    }
    public static String oemPasswordHash(String password,String secret){
        return  Base64.encode(("{md5}"+DigestUtil.md5Hex(DigestUtil.md5Hex(password)+secret)).toLowerCase());
    }
    public static String urlSign(String timestamp,String clientId,String uriPath,String secret){
        String text = timestamp+"#"+clientId+"#"+uriPath;
        return  Base64.encode(("{md5}"+DigestUtil.md5Hex(DigestUtil.md5Hex(text.toLowerCase())+secret)).toLowerCase());
    }
}
