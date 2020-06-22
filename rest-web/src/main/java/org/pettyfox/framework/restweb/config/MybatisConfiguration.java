package org.pettyfox.framework.restweb.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
@MapperScan("org.pettyfox.framework.service.user.modules.mapper")
public class MybatisConfiguration implements IdentifierGenerator {

    /**
     * 乐观锁
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 雪花id
     * @param entity
     * @return
     */
    @Override
    public Long nextId(Object entity) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        return snowflake.nextId();
    }

    /**
     * 分页配置
     * @return
     */
    @Bean
    public PageInterceptor pageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties p = new Properties();
        p.setProperty("closeConn","false");
        //对于sqlite数据库必须配置数据库方言，否则会发生数据库连接泄露的问题。
        p.setProperty("helperDialect","sqlite");
        pageInterceptor.setProperties(p);
        return pageInterceptor;
    }
}
