package com.stt.data.mongodb;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link SampleMongoApplication}.
 *
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleMongoApplicationTests {
    @ClassRule
    public static OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testDefaultSettings() throws Exception {
        String output = SampleMongoApplicationTests.outputCapture.toString();
        System.out.println("output=====start====="+output+"=====end====");
        assertThat(output).contains("firstName='Alice', lastName='Smith'");
    }
}
