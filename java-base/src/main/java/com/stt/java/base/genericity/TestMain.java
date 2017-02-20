package com.stt.java.base.genericity;

import java.lang.reflect.Array;
import java.util.Vector;

/**
 * Created by Administrator on 2017-02-20.
 *
 * @author shitongtong
 */
public class TestMain {
    public static void main(String[] args) {
        Pair[] table = new Pair[]{};

        //这是因为擦除后，table的类型变为Pair[]，可以转化成一个Object[]。
//        Pair<String>[] table = new Pair<String>(10); //ERROR

        Object[] objarray =table;

        //数组可以记住自己的元素类型，下面的赋值会抛出一个ArrayStoreException异常。
//        objarray ="Hello"; //ERROR

        //对于泛型而言，擦除降低了这个机制的效率。下面的赋值可以通过数组存储的检测，但仍然会导致类型错误。
//        objarray =new Pair<Integer>();

        //如果需要收集参数化类型对象，直接使用ArrayList：ArrayList<Pair<String>> 最安全且有效。




        Vector<? extends Number> x1 = new Vector<Integer>();//这是正确的

//        Vector<? extends Number> x = new Vector<String>();//这是错误的


        Vector<? super Integer> x3 = new Vector<Number>();//这是正确的

//        Vector<? super Integer> x2 = new Vector<Byte>();//这是错误的

    }

    /*public<T> T[] minMax(T[] a){
//        T[] mm = new T[2]; //ERROR
//        ...
    }*/
    /*
    类似的，擦除会使这个方法总是构靠一个Object[2]数组。但是，可以用反射构造泛型对象和数组。
   利用反射，调用Array.newInstance:
     */
    public static <T extends Comparable> T[] minmax(T[] a)

    {

        T[] mm = (T[]) Array.newInstance(a.getClass().getComponentType(),2);

        // 以替换掉以下代码
        // Obeject[] mm = new Object[2];
        // return (T[]) mm;

        return mm;
    }


}
