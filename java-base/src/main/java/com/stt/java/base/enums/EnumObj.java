package com.stt.java.base.enums;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/5/27.
 */
public class EnumObj {
    private int key;
    private String value;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EnumObj{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
