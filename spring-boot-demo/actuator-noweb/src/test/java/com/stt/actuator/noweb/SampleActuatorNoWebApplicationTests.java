package com.stt.actuator.noweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Basic integration tests for service demo application.
 *
 * Created by Administrator on 2017-02-08.
 *
 * @author shitongtong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class SampleActuatorNoWebApplicationTests {

    @Autowired
    private MetricsEndpoint endpoint;

    @Test
    public void testEndpointsExits(){
        assertThat(this.endpoint).isNotNull();
    }
}
