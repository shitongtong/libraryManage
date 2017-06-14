package cn.stt.test1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/5/19.
 */
@Configuration // 一般这个注解，我们用来标识main方法所在的类
@EnableAutoConfiguration // 根据maven依赖的jar来自动猜测完成正确的spring的对应配置，只要引入了spring-boot-starter-web的依赖，默认会自动配置SpringMVC和tomcat容器
@ComponentScan(basePackages = { "cn.stt" }) // 自动扫描加载所有的Spring组件包括Bean注入，一般用在main方法所在的类上
//@ImportResource({"classpath:dubbo-consumer.xml"})
public class Test1Application extends WebMvcConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(Test1Application.class);

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Test1Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        Map<String,Object> defaultMap = new HashMap<>();
        defaultMap.put("spring.profiles.active","dev");
        app.setDefaultProperties(defaultMap);
        app.run(args);
        logger.debug("容器启动成功");
    }

    /**
     * 解决跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }
}
