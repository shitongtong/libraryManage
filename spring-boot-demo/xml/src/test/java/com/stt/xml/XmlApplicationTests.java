package com.stt.xml;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 2017-02-15.
 *
 * @author shitongtong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlApplicationTests {

    @Rule
    public OutputCapture output = new OutputCapture();

    @Test
    public void testDefaultSettings() {
        XmlApplication.main(new String[0]);
        String s = this.output.toString();
        System.out.println("===s===" + s + "======");
        assertThat(s).contains("Hello World");
    }

}
