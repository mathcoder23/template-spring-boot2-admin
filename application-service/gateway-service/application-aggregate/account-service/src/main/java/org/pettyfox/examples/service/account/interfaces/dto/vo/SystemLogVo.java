package org.pettyfox.examples.service.account.interfaces.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.pettyfox.base.comm.log.ApiLogType;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SystemLogVo implements Serializable {

    private Date datetime;
    private Integer accountId;
    private String accountName;
    private String ip;
    private String api;
    private String log;
    @JsonIgnore
    private String data;
    private ApiLogType.Type type;
    private ApiLogType.OptionType optionType;

}
