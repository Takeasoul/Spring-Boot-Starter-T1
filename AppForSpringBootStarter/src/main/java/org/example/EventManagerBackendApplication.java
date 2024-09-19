package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories
@EnableAsync
@EnableAspectJAutoProxy
public class EventManagerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventManagerBackendApplication.class, args);
    }

}