package com.stt.atmosphere;

import org.atmosphere.cpr.AtmosphereInitializer;
import org.atmosphere.cpr.AtmosphereServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Collections;

/**
 * Created by stt on 2017/2/11.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
public class SampleAtmosphereApplication {

    public static class EmbeddedAtmosphereInitializer extends AtmosphereInitializer
            implements ServletContextInitializer {

        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {
            onStartup(Collections.<Class<?>>emptySet(), servletContext);
        }
    }

    @Bean
    public EmbeddedAtmosphereInitializer atmosphereInitializer() {
        return new EmbeddedAtmosphereInitializer();
    }

    @Bean
    public ServletRegistrationBean atmosphereServelt() {
        //Dispatcher servlet is mapped is to '/home' to allow the AtmosphereServlet
        //to be mapped to '/chat'
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new AtmosphereServlet(), "/chat/*");
        registrationBean.addInitParameter("org.atmosphere.cpr.packages", "sample");
        registrationBean.addInitParameter("org.atmosphere.interceptor.HearbeatInterceptor.clientHearbeatFrequencyInSeconds", "10");
        registrationBean.setLoadOnStartup(0);
        //Need to occur before EmbeddedAtmosphereInitializer
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

    @Configuration
    static class MvcConfiguration extends WebMvcConfigurerAdapter {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            System.out.println("******************  addViewControllers  *********************");
            registry.addViewController("/").setViewName("forward:/home/home.html");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleAtmosphereApplication.class, args);
    }

}
