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
 * Created by Administrator on 2017-02-07.
 *
 * @author shitongtong
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("endpoints")
public class EndpointsPropertiesSampleActuatorApplicationTests {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void exampleTest() {
//        String body = this.restTemplate.withBasicAuth("user", getPassword()).getForObject("/oops", String.class);
//        System.out.println(body);
//        String helloPhil = this.restTemplate.postForObject("/", "{value:HelloPhil}", String.class);
//        System.out.println(helloPhil);
//        assertThat(body).isEqualTo("Hello World");

//        ResponseEntity<String> entity = this.restTemplate.getForEntity("/health",String.class);

    }

    @Test
    public void testCustomErrorPath(){
        String password = getPassword();
        System.out.println("password:"+password);
        ResponseEntity<Map> entity = this.restTemplate.withBasicAuth("user", password).getForEntity("/oops",Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        Map<String,Object> body = entity.getBody();
        assertThat(body.get("error")).isEqualTo("None");
        assertThat(body.get("status")).isEqualTo(999);
    }

    @Test
    public void testCustomContextPath(){
//        System.setProperty("http.maxRedirects", "100");
        //发生异常：java.net.ProtocolException: Server redirected too many  times (20)
        //原因：security:require_ssl:  设置为了true
        ResponseEntity<String> entity = this.restTemplate.withBasicAuth("user", getPassword()).getForEntity("/admin/health",String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("\"status\":\"UP\"");
        assertThat(entity.getBody()).contains("\"hello\":\"world\"");
    }

    private String getPassword(){
        return this.securityProperties.getUser().getPassword();
    }


}
