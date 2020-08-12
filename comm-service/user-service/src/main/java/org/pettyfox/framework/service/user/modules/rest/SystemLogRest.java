package org.pettyfox.framework.service.user.modules.rest;

import io.swagger.annotations.ApiOperation;
import org.pettyfox.framework.service.user.config.StaticConfig;
import org.pettyfox.framework.service.user.modules.biz.SystemLogBiz;
import org.pettyfox.framework.service.user.modules.entity.SystemLog;
import org.pettyfox.base.web.BaseController;
import org.pettyfox.base.web.rest.RestObjectResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(StaticConfig.API_PREFIX+"system-log")
public class SystemLogRest extends BaseController<SystemLogBiz, SystemLog> {
    @Resource
    private SystemLogBiz systemLogBiz;
    @RequestMapping(value = "list2", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("列表")
    public RestObjectResponse list(
            @RequestParam(value = "startDate",required = false) String startDate,
            @RequestParam(value = "endDate",required = false) String endDate,
            @RequestParam(value = "ip",required = false) String ip,
            @RequestParam(value = "api",required = false) String api,
            @RequestParam(value = "log",required = false) String log,
            @RequestParam(value = "exportType",defaultValue = "0" ,required = false) Integer exportType,
            @RequestParam(value = "type",defaultValue = "1" ,required = false) Integer type,
            @RequestParam(value = "exportAll",defaultValue = "false" ,required = false) Boolean exportAll,
            @RequestParam(value = "pageNum",defaultValue = "20") int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "1") int pageSize,
            HttpServletResponse response) throws IOException {
//        if(isExport(exportType)){
//            pageSize = 200000;
//        }
        PageInfo<SystemLog> page = systemLogBiz.listPage(pageNum,pageSize,startDate,endDate,type,ip,api,log);
//        if(isExport(exportType)){
//            excelExport(response,page.getList());
//            return apiOK();
//        }
        return RestObjectResponse.ok(page);
    }
}
