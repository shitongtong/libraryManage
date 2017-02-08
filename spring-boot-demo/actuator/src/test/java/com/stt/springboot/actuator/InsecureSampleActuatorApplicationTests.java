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
        "security.basic.enabled:false"
})
@DirtiesContext
public class InsecureSampleActuatorApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testHome(){
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity("/", Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map body = entity.getBody();
        assertThat(body.get("message")).isEqualTo("Hello Phil");
        assertThat(entity.getHeaders()).doesNotContainKey("Set-Cookie");
    }

}
