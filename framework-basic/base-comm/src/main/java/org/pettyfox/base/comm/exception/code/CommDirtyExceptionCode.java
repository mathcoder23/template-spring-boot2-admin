package org.pettyfox.base.comm.exception.code;

import org.pettyfox.base.comm.type.BaseEnum;

/**
 * @ClassName CommDirtyExceptionCode
 * @Description 公共异常响应码
 * @Author eface
 * @Date 2020/3/30 9:49
 * @Version 1.0
 */
public enum CommDirtyExceptionCode implements BaseEnum<CommDirtyExceptionCode,Integer> {
    /**
     * 公共异常响应码
     */
    SIGN_ERROR(410,"url签名错误")
    ,SIGN_ERROR_EXPIRED(411,"url请求过期")
    ;
    Integer value;
    String name;
    CommDirtyExceptionCode(Integer value,String name){
        this.value = value;
        this.name = name;
    }
    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }
}
