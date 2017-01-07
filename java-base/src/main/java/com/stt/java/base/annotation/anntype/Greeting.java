package com.stt.java.base.annotation.anntype;

import java.lang.annotation.Inherited;

/**
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
@Inherited
public @interface Greeting {

    public enum FontColor{BULE,RED,GREEN};
    public String name();
    FontColor fontColor() default FontColor.GREEN;
}
