package org.pettyfox.framework.service.config.interfaces.dto.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ApiModel("系统配置组")
public class SystemConfigGroupVO implements Serializable {

    private Long id;
    @ApiModelProperty("组名")
    private String name;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("配置列表")
    private List<SystemConfigVO> list;
}
