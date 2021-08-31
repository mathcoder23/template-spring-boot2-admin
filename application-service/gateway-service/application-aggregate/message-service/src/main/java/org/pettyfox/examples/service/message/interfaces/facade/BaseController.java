package org.pettyfox.examples.service.message.interfaces.facade;

import org.pettyfox.examples.service.message.infrastructure.config.MessageAggregateConfig;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(MessageAggregateConfig.API_PREFIX)
public abstract class BaseController {

}
