package com.eface.comm.base.web;

import cn.hutool.core.util.ObjectUtil;
import com.eface.base.comm.log.ApiLog;
import com.eface.base.comm.log.ApiLogType;
import com.eface.comm.base.web.dao.BaseEntityNotId;
import com.eface.comm.base.web.rest.RestObjectResponse;
import com.eface.comm.base.web.dao.BaseEntity;
import com.eface.comm.base.web.dao.BaseService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

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
