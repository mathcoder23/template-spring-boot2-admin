package org.pettyfox.framework.service.account.interfaces.facade;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.pettyfox.base.web.rest.RestObjectResponse;
import org.pettyfox.framework.service.account.doamin.account.biz.SystemLogBiz;
import org.pettyfox.framework.service.account.doamin.account.po.SystemLog;
import org.pettyfox.framework.service.account.infrastructure.config.AccountAggregateConfig;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(AccountAggregateConfig.API_PREFIX + "/system-log")
public class SystemLogRest {

    @Resource
    private SystemLogBiz systemLogBiz;

    @RequestMapping(value = "list2", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("列表")
    public RestObjectResponse<PageInfo<SystemLog>> list(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "ip", required = false) String ip,
            @RequestParam(value = "api", required = false) String api,
            @RequestParam(value = "log", required = false) String log,
            @RequestParam(value = "exportType", defaultValue = "0", required = false) Integer exportType,
            @RequestParam(value = "type", defaultValue = "1", required = false) Integer type,
            @RequestParam(value = "exportAll", defaultValue = "false", required = false) Boolean exportAll,
            @RequestParam(value = "pageNum", defaultValue = "20") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "1") int pageSize,
            HttpServletResponse response) throws IOException {
        PageInfo<SystemLog> page = systemLogBiz.listPage(pageNum, pageSize, startDate, endDate, type, ip, api, log);

        return RestObjectResponse.ok(page);
    }
}
