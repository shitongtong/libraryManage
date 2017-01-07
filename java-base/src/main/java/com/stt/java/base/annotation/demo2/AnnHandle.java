package com.stt.java.base.annotation.demo2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 处理注解类
 *
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
public class AnnHandle {

    public static void getFruitInfo(Class<?> clazz){
        String strFruitName=" 水果名称：";
        String strFruitColor=" 水果颜色：";
        String strFruitProvicer="供应商信息：";

        Field[] fields = clazz.getDeclaredFields();



        for (Field field : fields){

            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations){
                System.out.println("field上的注解"+annotation);
            }

            if (field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = field.getAnnotation(FruitName.class);
                strFruitName = strFruitName + fruitName.value();
                System.out.println(strFruitName);
            }else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                strFruitColor = strFruitColor + fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            }else if (field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvicer=" 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
        }
    }
}
