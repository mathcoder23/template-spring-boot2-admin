package org.pettyfox.framework.gateway.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"org.pettyfox.framework.service.account.doamin.account.repository", "org.pettyfox.framework.service.config.domain.repository"})
public class MybatisConfiguration implements IdentifierGenerator {

    private static final Snowflake SNOWFLAKE = IdUtil.createSnowflake(1, 1);

    /**
     * 乐观锁
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 雪花id
     *
     * @param entity
     * @return
     */
    @Override
    public Long nextId(Object entity) {
        return SNOWFLAKE.nextId();
    }

    /**
     * 分页配置
     *
     * @return
     */
    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
