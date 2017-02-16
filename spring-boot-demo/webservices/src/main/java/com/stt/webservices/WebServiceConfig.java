package com.stt.webservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Created by Administrator on 2017-02-15.
 *
 * @author shitongtong
 */
@Configuration
public class WebServiceConfig {

    @Bean(name = "holiday")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema){
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("HumanResource");
        wsdl.setLocationUri("/holidayService");
        wsdl.setTargetNamespace("http://mycompany.com/hr/definitions");
        wsdl.setSchema(countriesSchema);
        return wsdl;
    }

    @Bean
    public XsdSchema countriesSchema(){
        return new SimpleXsdSchema(new ClassPathResource("META-INF/schemas/hr.xsd"));
    }

}
