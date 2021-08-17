package org.pettyfox.framework.service.config.interfaces.facade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.framework.service.config.domain.biz.ConfigSystemBiz;
import org.pettyfox.framework.service.config.domain.biz.ConfigSystemGroupBiz;
import org.pettyfox.framework.service.config.interfaces.dto.data.SystemConfigModifyData;
import org.pettyfox.framework.service.config.interfaces.dto.vo.SystemConfigGroupVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "系统配置管理")
public class SystemConfigController extends BaseController {

    @Resource
    private ConfigSystemGroupBiz configSystemGroupBiz;
    @Resource
    private ConfigSystemBiz configSystemBiz;

    @ApiOperation("获取所有系统配置")
    @PostMapping("/systemConfig/getAllConfig")
    public RestObjectResponse<List<SystemConfigGroupVO>> getAllConfig() {
        return RestObjectResponse.ok(configSystemGroupBiz.getAllConfig());
    }

    @ApiOperation("批量保存配置")
    @PostMapping("/systemConfig/simpleBatchModify")
    public RestObjectResponse<String> simpleBatchModify(@RequestBody List<SystemConfigModifyData> list) {
        configSystemBiz.simpleBatchModify(list);
        return RestObjectResponse.ok("");
    }
}
