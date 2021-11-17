package com.tsoft.playground.spring;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.LinkedList;
import java.util.List;

/**
 * This Test Class is only intended to show an alternative to MVCMock testing, and instead use the TestRestTemplate
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestAppContext.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TestWithTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Query : GET /api/names
     * Should return 15 entries (STRINGs) in JSON format
     */
    @Test
    public void other() {
        ResponseEntity<JsonNode> response = restTemplate.getForEntity("http://localhost:"+port+"/api/names", JsonNode.class );
        System.out.println( response.getBody().toPrettyString() );
        List<JsonNode> jsonAsList = new LinkedList<JsonNode>();
        response.getBody().elements().forEachRemaining(jsonAsList::add);
        Assert.assertEquals( jsonAsList.size(), 15 );
    }


    /**
     * This method allow us to call the RUNNING server
     * and this is hence not suitable for a test, but good to have
     */
    public void testWithWebClient() {
        Object result = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build()
                .get()
                .uri("/api/names")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(15);
    }


}
