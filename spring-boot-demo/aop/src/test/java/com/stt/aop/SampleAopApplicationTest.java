package com.stt.aop;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Tests for {@link com.stt.aop.SampleAopApplication}.
 *
 * Created by stt on 2017/2/11.
 */
public class SampleAopApplicationTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private String profiles;

    @Before
    public void init(){
        this.profiles = System.getProperty("spring.profiles.active");
        System.out.println("profiles=="+this.profiles);
    }

    @After
    public void after(){
        if (this.profiles != null){
            System.setProperty("spring.profiles.active",this.profiles);
        }else{
            System.clearProperty("spring.profiles.active");
        }
    }

    @Test
    public void testDefaultSettings(){
        SampleAopApplication.main(new String[0]);
        String output = this.outputCapture.toString();
        assertThat(output).contains("Hello Phil");
    }

    @Test
    public void testCommandLineOvrrides(){
        SampleAopApplication.main(new String[]{"--name=Gordon"});
        String output = this.outputCapture.toString();
        assertThat(output).contains("Hello Gordon");
    }
}
