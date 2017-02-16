package com.stt.metrics.dropwizard;

import com.codahale.metrics.MetricRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 2017-02-14.
 *
 * @author shitongtong
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class SampleDropwizardMetricsApplicationTests {
    @Autowired
    private MetricRegistry registry;

    @Autowired
    private GaugeService gauges;

    @Test
    public void timerCreated() {
        this.gauges.submit("timer.test", 1234);
        assertThat(this.registry.getTimers().get("timer.test").getCount()).isEqualTo(1);
    }
}
