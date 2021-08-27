package org.pettyfox.framework.service.account.interfaces.facade;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.framework.service.account.doamin.account.biz.SystemLogBiz;
import org.pettyfox.framework.service.account.doamin.account.po.SystemLog;
import org.pettyfox.framework.service.account.interfaces.dto.params.SystemLogParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@Api(tags = "系统日志")
public class SystemLogRest extends BaseController {

    @Resource
    private SystemLogBiz systemLogBiz;

    @PostMapping(value = "/systemLog/list")
    @ResponseBody
    @ApiOperation("日志列表")
    public RestObjectResponse<PageInfo<SystemLog>> list(@RequestBody SystemLogParams p) throws IOException {
        PageInfo<SystemLog> page = systemLogBiz.list(p);

        return RestObjectResponse.ok(page);
    }
}
