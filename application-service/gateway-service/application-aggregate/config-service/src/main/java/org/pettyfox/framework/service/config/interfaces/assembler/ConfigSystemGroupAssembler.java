package org.pettyfox.framework.service.config.interfaces.assembler;


import org.pettyfox.framework.service.config.domain.po.SystemConfigGroup;
import org.pettyfox.framework.service.config.interfaces.dto.vo.SystemConfigGroupVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ConfigSystemGroupAssembler {
    public static SystemConfigGroupVO convert(SystemConfigGroup configSystem) {
        SystemConfigGroupVO vo = new SystemConfigGroupVO();
        BeanUtils.copyProperties(configSystem, vo);
        return vo;
    }

    public static List<SystemConfigGroupVO> convert(List<SystemConfigGroup> list) {
        return list.stream().map(ConfigSystemGroupAssembler::convert).collect(Collectors.toList());
    }
}
