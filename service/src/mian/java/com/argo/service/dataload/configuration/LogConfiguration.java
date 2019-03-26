package com.argo.service.dataload.configuration;

import com.ARGO.log.config.EnableLogs;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableLogs
@ComponentScan(basePackages = "com.ARGO.common")
public class LogConfiguration {
}
