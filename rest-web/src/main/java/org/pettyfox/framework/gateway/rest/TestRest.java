package org.pettyfox.framework.gateway.rest;

import io.swagger.annotations.Api;
import org.pettyfox.base.web.rest.RestObjectResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Petty Fox
 * @version 1.0
 * @date 2020/8/12 9:14
 */
@Api(tags = "用户管理相关接口")
@RestController
@RequestMapping("/")
public class TestRest {
    @ApiOperation("测试接口")
    @GetMapping("/test")
    public RestObjectResponse<String> test(){
        return RestObjectResponse.ok("ok");
    }
}
