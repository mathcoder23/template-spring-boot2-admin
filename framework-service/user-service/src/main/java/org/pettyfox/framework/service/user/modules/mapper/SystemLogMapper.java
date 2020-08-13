package org.pettyfox.framework.service.user.modules.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.pettyfox.framework.service.user.modules.entity.SystemLog;

import static org.pettyfox.framework.service.user.config.StaticConfig.DS_USER_DB;

/**
 * @author eface
 */
@DS(DS_USER_DB)
public interface SystemLogMapper extends BaseMapper<SystemLog> {
}
