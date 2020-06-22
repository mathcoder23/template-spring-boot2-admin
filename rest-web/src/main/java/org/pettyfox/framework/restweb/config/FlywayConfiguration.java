package org.pettyfox.framework.restweb.config;

import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 基于flyway的多数据,数据结构同步配置.取到数据源列表后,根据名称依次校验表sql
 *
 * @author eface
 */
@Component("flyway")
public class FlywayConfiguration {
    private static final String DS_PREFIX = "dbsql/";
    private static final String DS_MASTER = "dbCore";
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


        List<String> dbs = Arrays.asList();
        for (String db : dbs) {
            configuration.locations("classpath:" + DS_PREFIX+db)
                    .dataSource(dataSourceList.loadDataSources().get(db))
                    .encoding("utf-8")
                    .validateOnMigrate(true);
            flyway = new Flyway(configuration);
            flyway.migrate();
        }


    }
}
