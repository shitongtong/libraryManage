package com.stt.springboot.activemq;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * OutputCapture is a JUnit Rule that you can use to capture System.out and System.err output.
 * Simply declare the capture as a @Rule then use toString() for assertions:
 *
 * Created by Administrator on 2017-02-07.
 *
 * @author shitongtong
 */
public class OutputCaptureTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void test(){
        System.out.println("Hello");
//        assertThat(outputCapture.toString(),containsString("Hello"));
        assertThat(this.outputCapture.toString().contains("Test Message")).isTrue();
    }
}
