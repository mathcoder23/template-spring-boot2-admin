package org.pettyfox.framework.service.account.interfaces.facade;


import com.github.pagehelper.PageInfo;
import org.pettyfox.base.comm.web.RestObjectResponse;
import org.pettyfox.base.web.dto.params.BasePageParam;
import org.pettyfox.framework.service.account.doamin.account.biz.AccountBiz;
import org.pettyfox.framework.service.account.interfaces.dto.vo.AccountVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AccountController extends BaseController {

    @Resource
    private AccountBiz accountBiz;

    @PostMapping("/account/list")
    public RestObjectResponse<PageInfo<AccountVO>> list(@RequestBody BasePageParam p) {
        return RestObjectResponse.ok(accountBiz.list(p));
    }
}
