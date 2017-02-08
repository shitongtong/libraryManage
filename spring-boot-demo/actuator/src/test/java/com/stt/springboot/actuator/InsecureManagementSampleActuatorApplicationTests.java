package com.stt.springboot.actuator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for insecured service endpoints (even with Spring Security on
 * classpath).
 *
 * Created by Administrator on 2017-02-08.
 *
 * @author shitongtong
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,properties = {
        "management.security.enabled:false"
})
@DirtiesContext
@ActiveProfiles("unsecure-management")
public class InsecureManagementSampleActuatorApplicationTests {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String getPassword(){
        return this.securityProperties.getUser().getPassword();
    }

    @Test
    public void testHomeIsSecure(){
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity("/", Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        Map body = entity.getBody();
        assertThat(body.get("error")).isEqualTo("Unauthorized");
        assertThat(entity.getHeaders()).doesNotContainKey("Set-Cookie");
    }

    @Test
    public void testMetrics() throws Exception {
        try {
            testHomeIsSecure(); // makes sure some requests have been made
        }
        catch (AssertionError ex) {
            // ignore;
        }
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity("/metrics",Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map<String, Object> body = entity.getBody();
        assertThat(body).containsKey("counter.status.401.unmapped");
    }

}
