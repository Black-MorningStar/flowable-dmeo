package com.example.flowabledemo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author 君墨笑
 * @date 2024/9/18
 */
@Configuration
public class ProcessEngineConfig {

    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dataSourceProperties.getJdbcUrl());
        config.setUsername(dataSourceProperties.getUserName());
        config.setPassword(dataSourceProperties.getPassword());
        config.setMaximumPoolSize(dataSourceProperties.getMaximumPoolSize());
        config.setMinimumIdle(dataSourceProperties.getMinimumIdle());
        config.setIdleTimeout(dataSourceProperties.getIdleTimeout());
        config.setConnectionTimeout(dataSourceProperties.getConnectionTimeout());
        return new HikariDataSource(config);
    }

    @Bean
    public ProcessEngine processEngine(DataSource dataSource) {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setDataSource(dataSource)
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        return cfg.buildProcessEngine();
    }
}
