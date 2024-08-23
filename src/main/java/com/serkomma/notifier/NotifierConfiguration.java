package com.serkomma.notifier;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class})
@EnableScheduling
@EnableAsync
@EnableFeignClients(basePackages = "com.serkomma.notifier")
public class NotifierConfiguration {
}
