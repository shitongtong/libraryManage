package com.stt.java.base.annotation.demo2;

import java.lang.annotation.Annotation;

/**
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
public class FruitRun {
    public static void main(String[] args) {
        /**结果：
         * 水果名称：Apple
         水果颜色：RED
         供应商编号：1 供应商名称：陕西红富士集团 供应商地址：陕西省西安市延安路89号红富士大厦
         */
        AnnHandle.getFruitInfo(Apple.class);

        /*Annotation[] annotations = Apple.class.getDeclaredAnnotations();
        for (Annotation annotation : annotations){
            System.out.println(annotation);
        }*/
    }
}
