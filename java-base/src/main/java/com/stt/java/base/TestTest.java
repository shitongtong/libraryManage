package com.stt.java.base;

/**
 * Created by Administrator on 2017-02-24.
 *
 * @author shitongtong
 */

public class TestTest {
    Person person = new Person("Test");
    static{
        System.out.println("test static");
    }

    public TestTest() {
        System.out.println("test constructor");
    }

    public static void main(String[] args) {
        new MyClass();
    }
}

class Person{
    static{
        System.out.println("person static");
    }
    public Person(String str) {
        System.out.println("person "+str);
    }
}


class MyClass extends TestTest {
    Person person = new Person("MyClass");
    static{
        System.out.println("myclass static");
    }

    public MyClass() {
        System.out.println("myclass constructor");
    }
}

