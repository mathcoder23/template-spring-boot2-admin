package org.pettyfox.framework.service.user.modules.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.pettyfox.framework.service.user.modules.entity.User;
import org.pettyfox.framework.service.user.modules.mapper.UserMapper;
import org.pettyfox.framework.service.user.modules.rule.PasswordEncoderUtils;
import org.pettyfox.base.web.dao.BaseService;
import org.pettyfox.framework.service.user.config.StaticConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author eface.m
 */
@Service
@DS(StaticConfig.DS_USER_DB)
public class UserBiz extends BaseService<UserMapper, User> {
    private String oemSecure = "1223";
    @CachePut(value = "user", key = "#p0")
    public User getUserByUsernameHashBase64(String usernameHash){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username_hash_base64",usernameHash);
        return getOne(queryWrapper);
    }
    @Async
    public void createOemUser(User user){
        if(StringUtils.isBlank(oemSecure)){
            throw new RuntimeException("没有配置oem密钥");
        }
        user.setUsernameHashBase64(PasswordEncoderUtils.oemUsernameHash(user.getUsername(),oemSecure));
        user.setPasswordHashBase64(PasswordEncoderUtils.oemPasswordHash(user.getPassword(),oemSecure));
        user.setPassword(PasswordEncoderUtils.oemPassword(user.getPasswordHashBase64()));
        save(user);
    }
    @Async
    public void updateOemUser(User user){
        if(StringUtils.isBlank(oemSecure)){
            throw new RuntimeException("没有配置oem密钥");
        }
        User cuser = getOemUser(user.getOemId());
        if(cuser == null){
            createOemUser(user);
            return;
        }
        user.setId(cuser.getId());
        user.setUsernameHashBase64(PasswordEncoderUtils.oemUsernameHash(user.getUsername(),oemSecure));
        user.setPasswordHashBase64(PasswordEncoderUtils.oemPasswordHash(user.getPassword(),oemSecure));
        user.setPassword(PasswordEncoderUtils.oemPassword(user.getPasswordHashBase64()));
        updateById(user);
    }
    @Async
    public User getOemUser(Long oemId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("oem_id",oemId);
        return getOne(queryWrapper);
    }
    @Async
    public void removeByOemId(Long oemId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("oem_id",oemId);
        remove(queryWrapper);
    }
}
