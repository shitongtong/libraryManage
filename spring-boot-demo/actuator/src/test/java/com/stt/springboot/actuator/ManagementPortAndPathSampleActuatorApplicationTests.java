package com.stt.springboot.actuator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.LocalManagementPort;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for separate management and main service ports.
 *
 * Created by Administrator on 2017-02-08.
 *
 * @author shitongtong
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,properties = {
        "management.port=0", "management.context-path=/admin"
})
@DirtiesContext
public class ManagementPortAndPathSampleActuatorApplicationTests {

    @Autowired
    private SecurityProperties securityProperties;

    @LocalServerPort
    private int port = 9010;

    @LocalManagementPort
    private int managementPort = 9011;

    private String getPassword(){
        return this.securityProperties.getUser().getPassword();
    }

    @Test
    public void testHome(){
        ResponseEntity<Map> entity = new TestRestTemplate("user", getPassword()).getForEntity("http://localhost:" + this.port, Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().get("message")).isEqualTo("Hello Phil");
    }

    @Test
    public void testMetrics() throws Exception {
        testHome(); // makes sure some requests have been made
        ResponseEntity<Map> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.managementPort + "/admin/metrics", Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testHealth() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.managementPort + "/admin/health",
                String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("\"status\":\"UP\"");
    }

    @Test
    public void testMissing(){
        ResponseEntity<String> entity = new TestRestTemplate("user", getPassword()).getForEntity("http://localhost:" + this.managementPort + "/admin/missing", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(entity.getBody()).contains("\"status\":404");
    }

    @Test
    public void testErrorPage(){
        ResponseEntity<Map> entity = new TestRestTemplate().getForEntity("http://localhost:" + this.port + "error", Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(entity.getBody().get("status")).isEqualTo(999);
    }

    @Test
    public void testManagementErrorPage() throws Exception {
        ResponseEntity<Map> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.managementPort + "/error", Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map<String, Object> body = entity.getBody();
        assertThat(body.get("status")).isEqualTo(999);
    }
}
