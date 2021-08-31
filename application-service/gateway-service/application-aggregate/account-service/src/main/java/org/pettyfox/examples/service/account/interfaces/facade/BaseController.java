package org.pettyfox.examples.service.account.interfaces.facade;

import org.pettyfox.examples.service.account.infrastructure.config.AccountAggregateConfig;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AccountAggregateConfig.API_PREFIX)
public abstract class BaseController {

}
