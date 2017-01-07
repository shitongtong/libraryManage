package com.stt.java.base.annotation.demo3;

import java.util.List;

/**
 * 测试类
 *
 * Created by Administrator on 2017-01-06.
 *
 * @author shitongtong
 */
public class TestAnnotation {

    public static void main(String[] args) {
        Parent p = new Child();
        //获取泛型中类里面的注解
        List<SortableField> list = p.init();
        //输出结果
        for (SortableField sortableField : list){
            System.out.println("字段名称："+sortableField.getName()+"\t字段类型："+sortableField.getType()+
                    "\t注解名称："+sortableField.getFieldMeta().name()+"\t注解描述："+sortableField.getFieldMeta().description());
        }
        /*
        字段名称：id	字段类型：boolean	                注解名称：序列号	注解描述：
        字段名称：age	字段类型：int	                    注解名称：年龄	注解描述：
        字段名称：name	字段类型：class java.lang.String	注解名称：姓名	注解描述：
        字段名称：desc	字段类型：class java.lang.String	注解名称：	注解描述：描述
         */
    }
}
