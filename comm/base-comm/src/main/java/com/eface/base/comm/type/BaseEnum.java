package com.eface.base.comm.type;

import lombok.SneakyThrows;

import java.util.Objects;

public interface BaseEnum<E extends Enum<E>, T> {
    @SneakyThrows
    static BaseEnum locateEnumStatus(String value, Class clazz) {
        BaseEnum[] enums = (BaseEnum[]) clazz.getEnumConstants();
        for (BaseEnum e :enums) {
            String a = Objects.toString(e.getValue());
            if (a.equals(value)) {
                return e;
            }
        }
        return null;
    }
    /**
     * 获取枚举的值
     * @return 枚举的值
     */
    T getValue();

    /**
     * 枚举名称，在json序列号显示
     * @return
     */
    String getName();
}