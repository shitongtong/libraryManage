package com.stt.springboot.actuator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for /health with {@code endpoints.health.sensitive=false}.
 *
 * Created by Administrator on 2017-02-08.
 *
 * @author shitongtong
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "endpoints.health.sensitive=false" })
@DirtiesContext
public class NonSensitiveHealthTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSecureHealth() throws Exception {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/health",
                String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).doesNotContain("\"hello\":1");
    }
}
