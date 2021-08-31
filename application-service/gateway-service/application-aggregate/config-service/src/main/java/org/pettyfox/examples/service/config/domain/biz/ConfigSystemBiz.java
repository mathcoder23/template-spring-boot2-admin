package org.pettyfox.examples.service.config.domain.biz;


import org.pettyfox.examples.service.config.domain.po.SystemConfig;
import org.pettyfox.examples.service.config.interfaces.dto.data.SystemConfigModifyData;
import org.pettyfox.examples.service.config.interfaces.dto.vo.SystemConfigVO;

import java.util.List;

public interface ConfigSystemBiz {

    List<SystemConfigVO> listAll();
    void simpleBatchModify(List<SystemConfigModifyData> list);
    SystemConfig getByConfigKey(String key);
}
