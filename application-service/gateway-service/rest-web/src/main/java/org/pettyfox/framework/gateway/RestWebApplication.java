package org.pettyfox.framework.gateway;

import org.apache.dubbo.config.annotation.DubboReference;
import org.pettyfox.framework.gateway.dubbo.GreetingsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({"org.pettyfox.framework"})
@EnableAsync
@EnableTransactionManagement
public class RestWebApplication {
    @DubboReference(version = "1.0.0")
    private GreetingsService demoService;
    public static void main(String[] args) {
        SpringApplication.run(RestWebApplication.class, args);
    }

}
