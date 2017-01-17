package com.stt.java.base.java8.demo10;


/**
 * Created by Administrator on 2017-01-17.
 *
 * @author shitongtong
 */
public class TestMain {

    public static void main(String[] args) {
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint);//null
//        java编译器会隐性的帮你定义好@Hints注解
        Hints hints = Person.class.getAnnotation(Hints.class);
//        System.out.println(hints.value().length);

        // 即便我们没有在Person类上定义@Hints注解，我们还是可以通过 getAnnotation(Hints.class) 来获取 @Hints注解，
        // 更加方便的方法是使用 getAnnotationsByType 可以直接获取到所有的@Hint注解。
        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);//0

    }
}
