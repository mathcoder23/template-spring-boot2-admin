package org.pettyfox.framework.service.config.domain.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.pettyfox.base.web.dao.BaseService;
import org.pettyfox.framework.service.config.domain.biz.ConfigSystemBiz;
import org.pettyfox.framework.service.config.domain.po.SystemConfig;
import org.pettyfox.framework.service.config.domain.repository.SystemConfigMapper;
import org.pettyfox.framework.service.config.interfaces.assembler.ConfigSystemAssembler;
import org.pettyfox.framework.service.config.interfaces.dto.data.SystemConfigModifyData;
import org.pettyfox.framework.service.config.interfaces.dto.vo.SystemConfigVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigSystemBizImpl extends BaseService<SystemConfigMapper, SystemConfig> implements ConfigSystemBiz {

    @Override
    public List<SystemConfigVO> listAll() {
        return this.list().stream().map(ConfigSystemAssembler::convert).collect(Collectors.toList());
    }

    @Override
    public void simpleBatchModify(List<SystemConfigModifyData> list) {
        if (null == list || list.isEmpty()) {
            return;
        }
        List<SystemConfig> systemConfigs = list.stream().map(d -> {
            SystemConfig config = new SystemConfig();
            config.setId(d.getId());
            config.setValue(d.getValue());
            return config;
        }).collect(Collectors.toList());
        this.updateBatchById(systemConfigs);
    }

    @Override
    public SystemConfig getByConfigKey(String key) {
        LambdaQueryWrapper<SystemConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemConfig::getConfigKey, key);
        return getOne(queryWrapper);
    }
}
