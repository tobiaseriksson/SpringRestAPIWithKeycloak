package com.tsoft.playground.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * This class is intended to show how to test using MockMvc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
// @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SpringContextIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCallToRootEndpoint() throws Exception {
        this.mockMvc
                .perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(content().string("Please use GET /api/names to get a list of the N first names, or a GET /api/names/idx to get a specific name"));
    }


    @Test
    public void testAll() throws Exception {
    this.mockMvc
        .perform(get("/api/names"))
        .andDo(print())
        .andExpect(
            content()
                .json(
                    "[\"Aaron\",\"Abbas\",\"Abbe\",\"Abdallah\",\"Abdirahim\",\"Abdirahman\",\"Abdulahi \",\"Abdullah\",\"Abdullahi\",\"Abdulrahman \",\"Abel \",\"Abraham\",\"Acke\",\"Adam\",\"Adel\"]"));
    }


}