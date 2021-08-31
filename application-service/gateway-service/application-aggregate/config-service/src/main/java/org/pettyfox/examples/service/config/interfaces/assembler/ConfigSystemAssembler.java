package org.pettyfox.examples.service.config.interfaces.assembler;


import org.pettyfox.examples.service.config.domain.po.SystemConfig;
import org.pettyfox.examples.service.config.interfaces.dto.vo.SystemConfigVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ConfigSystemAssembler {
    public static SystemConfigVO convert(SystemConfig configSystem) {
        SystemConfigVO vo = new SystemConfigVO();
        BeanUtils.copyProperties(configSystem, vo);
        return vo;
    }

    public static List<SystemConfigVO> convert(List<SystemConfig> list) {
        return list.stream().map(ConfigSystemAssembler::convert).collect(Collectors.toList());
    }
}
