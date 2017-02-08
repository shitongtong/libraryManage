package com.stt.springboot.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017-02-07.
 *
 * @author shitongtong
 */
@SpringBootApplication
public class ActuatorApplication implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up().withDetail("hello","world").build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class,args);
    }

}
