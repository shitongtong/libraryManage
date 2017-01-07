package com.stt.java.base.annotation.demo2;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
public class Apple {

    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String appleColor;

    @FruitProvider(id=1,name="陕西红富士集团",address="陕西省西安市延安路89号红富士大厦")
    private String appleProvider;

    public Apple() {
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields){
            if (field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = field.getAnnotation(FruitName.class);
                this.appleName = fruitName.value();
            }else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                this.appleColor = fruitColor.fruitColor().toString();
            }else if (field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
                this.appleProvider =" 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+fruitProvider.address();
            }
        }
    }

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public void displayName(){
        System.out.println("水果的名字是：苹果");
    }

    @Override
    public String toString() {
        return "Apple{" +
                "appleName='" + appleName + '\'' +
                ", appleColor='" + appleColor + '\'' +
                ", appleProvider='" + appleProvider + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        apple.displayName();
        System.out.println(apple.getAppleName());
        System.out.println(new Apple());
    }
}
