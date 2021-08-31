package org.pettyfox.examples.service.config.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.pettyfox.base.web.dao.BaseEntitySnowId;

@Getter
@Setter
@TableName("t_config_system_config_group")
public class SystemConfigGroup extends BaseEntitySnowId {

    private String name;
    private String description;
}
