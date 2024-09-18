package com.example.flowabledemo.config;

import liquibase.pro.packaged.S;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 君墨笑
 * @date 2024/9/18
 */
@Configuration
@ConfigurationProperties(prefix = "datasource")
@Getter
@Setter
public class DataSourceProperties {

    private String jdbcUrl;

    private String userName;

    private String password;

    private int maximumPoolSize;

    private int minimumIdle;

    private int idleTimeout;

    private int connectionTimeout;
}
