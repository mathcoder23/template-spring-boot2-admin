package org.pettyfox.framework.service.user.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.eface.base.comm.type.BaseEnum;
import com.eface.comm.base.web.dao.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Description User
 * @Author eface
 * @Date 2020/3/23 14:21
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity<Long> {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**ni
     * 用户昵称
     */
    private String nick;
    /**
     * 明文用户名
     */
    private String username;
    /**
     * 加密的用户密码
     */
    private String password;
    /**
     * 用户名hash校验,base64
     */
    private String usernameHashBase64;
    /**
     * 密码hash校验,base64
     */
    private String passwordHashBase64;
    /**
     * 商家(厂家)id
     */
    private Long oemId;
    private String phone;
    private String email;
    private Boolean enable;
    private Boolean emailAuth;
    private Boolean phoneAuth;
    /**
     * 用户状态
     */
    private Integer userStatus;
    /**
     * 用户状态说明
     */
    private String userStatusRemark;

}
