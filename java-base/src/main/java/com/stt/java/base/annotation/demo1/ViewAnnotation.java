package com.stt.java.base.annotation.demo1;

/**
 * Created by Administrator on 2017-01-05.
 *
 * @author shitongtong
 */
@Person(name = "stt",age = 22)
public class ViewAnnotation {

    public static void main(String[] args) {
        print(ViewAnnotation.class);
    }

    public static void print(Class c){
        System.out.println(c.getName());

        //java.lang.Class的getAnnotation方法，如果有注解，则返回注解。否则返回null
        Person person = (Person) c.getAnnotation(Person.class);
        if (person != null) {
            System.out.println("name:"+person.name()+" age:"+person.age());
        }else{
            System.out.println("person unknown!");
        }
    }
}
