package com.feginapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.datasource")
@Data
public class JdbcProperties {
    private String driverClassName;
    private String username;
    private String password;
    private String url;
}
