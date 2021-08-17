package org.pettyfox.framework.service.config.interfaces.dto.data;

import lombok.Getter;
import lombok.Setter;
import org.pettyfox.base.web.dto.params.BaseValidator;

@Getter
@Setter
public class SystemConfigModifyData implements BaseValidator {

    private Long id;
    private String value;
}
