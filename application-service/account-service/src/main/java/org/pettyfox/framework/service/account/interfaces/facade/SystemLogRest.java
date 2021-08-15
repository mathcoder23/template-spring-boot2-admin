package org.pettyfox.framework.service.account.interfaces.facade;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.framework.service.account.doamin.account.biz.SystemLogBiz;
import org.pettyfox.framework.service.account.doamin.account.po.SystemLog;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Api(tags = "系统日志")
public class SystemLogRest extends BaseController {

    @Resource
    private SystemLogBiz systemLogBiz;

    @PostMapping(value = "/systemLog/list")
    @ResponseBody
    @ApiOperation("日志列表")
    public RestObjectResponse<PageInfo<SystemLog>> list(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "ip", required = false) String ip,
            @RequestParam(value = "api", required = false) String api,
            @RequestParam(value = "log", required = false) String log,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
            HttpServletResponse response) throws IOException {
        PageInfo<SystemLog> page = systemLogBiz.listPage(pageNum, pageSize, startDate, endDate, type, ip, api, log);

        return RestObjectResponse.ok(page);
    }
}
