package com.stt.java.base.annotation.anntype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 注解NoDBColumn仅可用于注解类的成员变量
 *
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
@Target(ElementType.FIELD)
public @interface NoDBColumn {
}
