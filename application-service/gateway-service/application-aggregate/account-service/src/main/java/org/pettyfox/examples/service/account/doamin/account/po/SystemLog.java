package org.pettyfox.examples.service.account.doamin.account.po;

import com.baomidou.mybatisplus.annotation.TableName;
import org.pettyfox.base.comm.log.ApiLogType;
import org.pettyfox.base.web.dao.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

/**
 * @author Petty Fox
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_system_log")
public class SystemLog extends BaseEntity<Integer> {

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
