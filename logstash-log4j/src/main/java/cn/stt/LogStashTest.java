package cn.stt;

import org.apache.log4j.Logger;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/20.
 */
public class LogStashTest {
    private static final Logger LOGGER = Logger.getLogger(LogStashTest.class);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 20; i++) {
            LOGGER.info("log4j 这是一个坑人的坑log4j [" + i + "]");
        }
    }
}
