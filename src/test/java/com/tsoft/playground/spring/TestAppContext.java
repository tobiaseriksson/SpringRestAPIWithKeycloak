package com.tsoft.playground.spring;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = TestAppContext.class)
@Profile("test")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TestAppContext {


    @Bean
    @Primary
    public TestRestTemplate restTemplate() {
        return new TestRestTemplate();
    }

}
