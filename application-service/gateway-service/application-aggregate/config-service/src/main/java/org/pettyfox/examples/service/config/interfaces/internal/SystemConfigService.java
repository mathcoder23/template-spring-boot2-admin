package org.pettyfox.examples.service.config.interfaces.internal;

import org.pettyfox.examples.service.config.domain.biz.ConfigSystemBiz;
import org.pettyfox.examples.service.config.domain.po.SystemConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SystemConfigService {

    @Resource
    private ConfigSystemBiz configSystemBiz;

    public String getStr(String key) {
        SystemConfig config = configSystemBiz.getByConfigKey(key);
        if (null == config) {
            return "";
        }
        return config.getValue();
    }
}
