package com.stt.batch;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by stt on 2017/2/11.
 */
public class SampleBatchApplicationTests {
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testDefaultSettings() throws Exception {
        assertThat(SpringApplication
                .exit(SpringApplication.run(SampleBatchApplication.class))).isEqualTo(0);
        String output = this.outputCapture.toString();
        System.out.println("output=="+output);
        assertThat(output).contains("completed with the following parameters");
    }
}
