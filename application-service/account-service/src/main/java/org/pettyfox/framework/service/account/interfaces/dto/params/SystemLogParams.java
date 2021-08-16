package org.pettyfox.framework.service.account.interfaces.dto.params;

import lombok.Getter;
import lombok.Setter;
import org.pettyfox.base.web.dto.params.BasePageParam;

@Getter
@Setter
public class SystemLogParams extends BasePageParam {

    private String startDate;
    private String endDate;
    private String accountName;
    private String ip;
    private String api;
    private String log;
    private Integer type;
}
