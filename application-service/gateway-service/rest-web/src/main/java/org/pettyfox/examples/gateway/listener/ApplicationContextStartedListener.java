package org.pettyfox.examples.gateway.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Petty Fox
 */
@Slf4j
@Component
public class ApplicationContextStartedListener implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${spring.application.name:}")
    private String applicationName;

    @Value("${project.version:}")
    private String webVersion;
    @Value("${project.build.date:}")
    private String webVersionDate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info(String.format("======================== Spring Boot Web Start project version : %s,build date : %s ========================", webVersion, webVersionDate));
        log.info(String.format("#########Applicetion [%s] context started listener execute begin#########", applicationName));

        log.info(String.format("#########Applicetion [%s] context started listener execute end#########", applicationName));
    }


}