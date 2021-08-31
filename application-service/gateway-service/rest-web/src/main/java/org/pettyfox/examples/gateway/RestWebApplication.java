package org.pettyfox.examples.gateway;

import org.apache.dubbo.config.annotation.DubboReference;
import org.pettyfox.examples.dubbo.GreetingsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan({"org.pettyfox.examples"})
@EnableAsync
@EnableTransactionManagement
@RestController
public class RestWebApplication {
    @DubboReference(version = "1.0.0", group = "test")
    private GreetingsService demoService;

    public static void main(String[] args) {
        SpringApplication.run(RestWebApplication.class, args);
    }

    @GetMapping("dubbo")
    public String get() {
        return demoService.sayHi("aa");
    }

}
