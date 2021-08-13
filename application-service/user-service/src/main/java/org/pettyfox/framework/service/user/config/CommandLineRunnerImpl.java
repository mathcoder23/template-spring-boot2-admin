package org.pettyfox.framework.service.user.config;

import org.pettyfox.framework.service.user.modules.biz.SystemLogBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Resource
    private SystemLogBiz systemLogBiz;
    @Value("${project.build.date}")
    private String serviceBuildDate;
    @Value("${project.version}")
    private String serviceVersion;
    @Override
    public void run(String... args) throws Exception {
        log.info(String.format("系统启动完成,软件版本:%s,构建时间:%s",serviceVersion,serviceBuildDate));
        systemLogBiz.saveSystemLog(null,null,String.format("系统启动完成,软件版本:%s,构建时间:%s",serviceVersion,serviceBuildDate));
    }
}