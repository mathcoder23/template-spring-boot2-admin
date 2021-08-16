package org.pettyfox.framework.service.account.doamin.account.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.pettyfox.base.comm.type.BaseEnum;
import org.pettyfox.base.web.dao.BaseEntity;

import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_account")
public class Account extends BaseEntity<Long> {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
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

    private String phone;

    private String email;

    private Boolean enable;

    private Boolean emailAuth;

    private Boolean phoneAuth;

    private Long roleId;

    private Type type;

    private Date lastActiveTime;

    /**
     * 用户状态
     */
    private Integer userStatus;

    /**
     * 用户状态说明
     */
    private String userStatusRemark;

    public enum Type implements BaseEnum<Type, Integer> {
        /**
         * 用户类型,[SUPER_ADMIN(1):超级管理员,USER(10):普通用户]
         */
        SUPER_ADMIN(1, "超级管理员"),
        USER(10, "普通用户");

        private final int value;
        private final String name;

        Type(int value, String name) {
            this.value = value;
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public Integer getValue() {
            return value;
        }
    }
}
