package org.pettyfox.framework.service.account.interfaces.facade;

import org.pettyfox.framework.service.account.infrastructure.config.AccountAggregateConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AccountAggregateConfig.API_PREFIX)
public class BaseController {
}
