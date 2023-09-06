package com.example.report.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.report.config, com.example.report.domain.controller, com.example.report.domain.service, com.example.report.domain.mapper, com.example.report.domain.exception, com.example.report.domain.dto, com.example.report.domain.model, com.example.report.domain.repository, com.example.report.domain.util, com.example.report.domain.util.*"})
@EntityScan(basePackages = "com.example.report.domain.model")
@EnableJpaRepositories(basePackages = "com.example.report.domain.repository")
public class WebServiceApplication extends SpringBootServletInitializer {

}
