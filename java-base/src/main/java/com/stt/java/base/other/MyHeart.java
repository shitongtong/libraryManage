package com.stt.java.base.other;

import java.util.Date;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/9/14.
 */
public class MyHeart {
    private static HuaLi huaLi;

    private MyHeart() {

    }

    public static HuaLi getHuaLi() {
        if (huaLi == null) {
            synchronized (HuaLi.class) {
                if (huaLi == null) {
                    huaLi = new HuaLi();
                    huaLi.setRealName("花梨");
                    huaLi.setGender(2);
                    huaLi.setBirthday(new Date());
                    huaLi.setIntroduce("温柔美丽善良可爱知书达礼...");
                }
            }
        }
        return huaLi;
    }
}
