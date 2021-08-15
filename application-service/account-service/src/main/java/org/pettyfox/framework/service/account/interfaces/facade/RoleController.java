package org.pettyfox.framework.service.account.interfaces.facade;


import org.pettyfox.base.web.BaseController;
import org.pettyfox.framework.service.account.doamin.account.biz.impl.RoleServiceImpl;
import org.pettyfox.framework.service.account.doamin.account.po.Role;
import org.pettyfox.framework.service.account.infrastructure.config.AccountAggregateConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Petty Fox
 * @since 2021-04-14
 */
@RestController
@RequestMapping(AccountAggregateConfig.API_PREFIX + "/role")
public class RoleController extends BaseController<RoleServiceImpl, Role> {

}
