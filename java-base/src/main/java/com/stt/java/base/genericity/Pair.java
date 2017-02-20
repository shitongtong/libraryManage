package com.stt.java.base.genericity;

/**
 * Created by Administrator on 2017-02-20.
 *
 * @author shitongtong
 */
public class Pair<T> {
    private T value;
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    /*public boolean equals(T value){
        return false;
    }*/
}
