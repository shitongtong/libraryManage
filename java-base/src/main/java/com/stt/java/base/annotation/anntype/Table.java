package com.stt.java.base.annotation.anntype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 注解Table 可以用于注解类、接口(包括注解类型) 或enum声明
 *
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
@Target(ElementType.TYPE)
public @interface Table {

    /**
     * 数据表名称注解，默认值为类名称
     * @return
     */
    public String tableName() default "className";
}
