package com.tsoft.playground.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan( basePackages = "com.tsoft.playground.spring" )
public class WebConfig {

}
