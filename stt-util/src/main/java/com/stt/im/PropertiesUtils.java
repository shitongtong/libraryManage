package com.stt.im;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/18.
 */
public class PropertiesUtils {
    public static Properties getProperties() {

        Properties p = new Properties();

        try {
            InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(
                    "RestAPIConfig.properties");

            p.load(inputStream);

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return p;
    }
}
