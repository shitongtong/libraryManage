package com.stt.springboot.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

import java.util.Collections;

/**
 * Created by Administrator on 2017-02-07.
 *
 * @author shitongtong
 */
public class ExampleInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("example", Collections.singletonMap("someKey","someValue"));
    }
}
