package org.pettyfox.framework.service.config.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.pettyfox.base.comm.type.BaseEnum;
import org.pettyfox.base.web.dao.BaseEntitySnowId;

@Getter
@Setter
@TableName("t_config_system_config")
public class SystemConfig extends BaseEntitySnowId {

    private String configKey;
    private String value;
    private String defaultValue;
    private String label;
    private Type type;
    private Long systemConfigGroupId;

    public enum Type implements BaseEnum<Type, Integer> {
        TEXT("TEXT", 0);
        private String name;
        private Integer value;

        Type(String name, Integer value) {
            this.name = name;
            this.value = value;
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
