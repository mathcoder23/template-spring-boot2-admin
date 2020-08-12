package org.pettyfox.base.comm.log;

import org.pettyfox.base.comm.type.BaseEnum;

public class ApiLogType {
    public enum Type implements BaseEnum<Type, Integer> {
        ACCOUNT_OPTION_LOG(1,"账户操作日志"),
        SYSTEM_LOG(2,"系统日志")
        ;
        Integer value;
        String name;
        Type(Integer value,String name){
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
    public enum OptionType implements BaseEnum<OptionType, Integer> {
        INSERT(1,"插入"),
        UPDATE(2,"修改"),
        DELETE(3,"删除"),
        DELETE_BATCH(4,"批量删除"),
        OTHER(0,"其他"),
        SAVE(10,"保存")
        ;
        Integer value;
        String name;
        OptionType(Integer value,String name){
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
}
