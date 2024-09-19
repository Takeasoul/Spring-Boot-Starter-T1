package org.example.logginghttpspringbootstarter.config;

import org.example.logginghttpspringbootstarter.filter.LoggingFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(LoggingProperties.class)
public class LoggingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LoggingFilter loggingFilter(LoggingProperties loggingProperties) {
        return new LoggingFilter(loggingProperties);
    }
}