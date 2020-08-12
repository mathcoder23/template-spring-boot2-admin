package org.pettyfox.base.web;

import org.pettyfox.base.comm.log.ApiLog;
import org.pettyfox.base.comm.log.ApiLogType;
import org.pettyfox.base.web.dao.BaseEntityNotId;
import org.pettyfox.base.web.rest.RestObjectResponse;
import org.pettyfox.base.web.dao.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

public abstract  class BaseController<T extends BaseService ,E extends BaseEntityNotId> {
    @Autowired
    protected T baseService;
    protected void saveLog(E entity){

    }
    protected void deleteLog(String[] ids){

    }

    @PostMapping("save")
    @ApiLog(log = "保存",optionType = ApiLogType.OptionType.SAVE)
    public RestObjectResponse<E> save(@RequestBody E entity){
        boolean result = baseService.saveOrUpdate(entity);
        saveLog(entity);
        return RestObjectResponse.ok(entity);
    }

    @ApiLog(log = "删除",optionType = ApiLogType.OptionType.DELETE_BATCH)
    @PostMapping("delete")
    public RestObjectResponse<String> delete(@RequestParam("ids") String[] ids){
        baseService.removeByIds(Arrays.asList(ids));
        return RestObjectResponse.ok("删除成功");
    }
    @GetMapping("list")
    public RestObjectResponse list(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,@RequestParam(value = "pageSize",defaultValue = "15")Integer pageSize){
        return RestObjectResponse.ok(baseService.listPage(pageNo,pageSize));
    }
}
