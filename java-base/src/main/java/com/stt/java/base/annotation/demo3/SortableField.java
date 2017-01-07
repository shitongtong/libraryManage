package com.stt.java.base.annotation.demo3;

import java.lang.reflect.Field;

/**
 * 获取到注解的帮助类
 *
 * Created by Administrator on 2017-01-06.
 *
 * @author shitongtong
 */
public class SortableField {

    private FieldMeta fieldMeta;
    private Field field;
    private String name;
    private Class<?> type;

    public SortableField() {
    }

    public SortableField(FieldMeta fieldMeta, Field field) {
        super();
        this.fieldMeta = fieldMeta;
        this.field = field;
        this.name = field.getName();
        this.type = field.getType();
    }

    public SortableField(FieldMeta fieldMeta, String name, Class<?> type) {
        super();
        this.fieldMeta = fieldMeta;
        this.name = name;
        this.type = type;
    }

    public FieldMeta getFieldMeta() {
        return fieldMeta;
    }

    public void setFieldMeta(FieldMeta fieldMeta) {
        this.fieldMeta = fieldMeta;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
