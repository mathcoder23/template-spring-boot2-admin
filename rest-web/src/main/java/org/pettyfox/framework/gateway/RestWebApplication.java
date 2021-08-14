package org.pettyfox.framework.gateway;

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

    public static void main(String[] args) {
        SpringApplication.run(RestWebApplication.class, args);
    }

}
