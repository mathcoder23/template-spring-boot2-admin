package org.pettyfox.framework.business.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.pettyfox.framework.dubbo.GreetingsService;

@DubboService(version = "1.0.0", group = "test2")
@Slf4j
public class GreetingsServiceImpl2 implements GreetingsService {
    @Override
    public String sayHi(String name) {
        log.info("handler2 sya hi");
        return "hi2, " + name;
    }
}