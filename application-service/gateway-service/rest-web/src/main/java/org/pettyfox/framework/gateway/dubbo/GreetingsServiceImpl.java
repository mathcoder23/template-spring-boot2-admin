package org.pettyfox.framework.gateway.dubbo;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0")
public class GreetingsServiceImpl implements GreetingsService {
    @Override
    public String sayHi(String name) {
        return "hi, " + name;
    }
}