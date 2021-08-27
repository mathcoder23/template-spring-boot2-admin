package org.pettyfox.framework.service.message.interfaces.facade;

import org.pettyfox.framework.service.message.infrastructure.config.MessageAggregateConfig;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(MessageAggregateConfig.API_PREFIX)
public abstract class BaseController {

}
