package org.pettyfox.framework.service.config.domain.biz;


import org.pettyfox.framework.service.config.interfaces.dto.data.SystemConfigModifyData;
import org.pettyfox.framework.service.config.interfaces.dto.vo.SystemConfigGroupVO;

import java.util.List;

public interface ConfigSystemGroupBiz {

    List<SystemConfigGroupVO> getAllConfig();


}
