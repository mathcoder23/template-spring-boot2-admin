package org.pettyfox.framework.restweb.config;

import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static org.pettyfox.framework.service.user.config.StaticConfig.DS_USER_DB;


/**
 * 基于flyway的多数据,数据结构同步配置.取到数据源列表后,根据名称依次校验表sql
 *
 * @author eface
 */
@Component("flyway")
@Slf4j
public class FlywayConfiguration {
    private static final String DS_PREFIX = "";
    private static final String DS_MASTER = "master";
    /**
     * 动态数据源列表
     */
    @Resource
    private DynamicDataSourceProvider dataSourceList;

    /**
     * 初始化多数据源sql
     */
    @PostConstruct
    public void migrate() {
        FluentConfiguration configuration = Flyway.configure();
        Flyway flyway;
        //主数据源根据flyway
        configuration.locations("classpath:" + DS_PREFIX+DS_MASTER)
                .dataSource(dataSourceList.loadDataSources().get("master"))
                .encoding("utf-8")
                .validateOnMigrate(true);
        flyway = new Flyway(configuration);
        flyway.migrate();


        List<String> dbs = Arrays.asList(DS_USER_DB);
        for (String db : dbs) {
            configuration.locations("classpath:" +db)
                    .dataSource(dataSourceList.loadDataSources().get(db))
                    .encoding("utf-8")
                    .validateOnMigrate(true);
            flyway = new Flyway(configuration);
            flyway.migrate();
            log.info("init sql finished for db:{}",db);
        }
    }
}
