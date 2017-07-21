package cn.stt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/20.
 */
public class LogstashTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogstashTest.class);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 20; i++) {
            LOGGER.info("logback 会计师卡德加萨拉开房记录 logback [" + i + "]");
        }
    }
}
