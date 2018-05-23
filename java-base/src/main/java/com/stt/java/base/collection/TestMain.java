package com.stt.java.base.collection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-02-21.
 *
 * @author shitongtong
 */
public class TestMain {

    public static void main(String[] args) {

//        网传java7的下面两条新特性是假的假的！！
//        List<Integer> piDigits = [ 1,2,3,4,5,8 ];
//        Map map = {name:"xxx",age:18};

        HashMap<String, Object> hashMap = new HashMap<>();
        Object put = hashMap.put("ss", "ss");
        System.out.println(put);
        Object o = hashMap.putIfAbsent("ss", "ss");
        Object o1 = hashMap.putIfAbsent("ss", "sss");
        Object o2 = hashMap.putIfAbsent("s", "ss");
        System.out.println(o);
        System.out.println(o1);
        System.out.println(o2);

//        BufferedReader
    }

    @Test
    public void testRemoveList(){
        List<String> list = new ArrayList<>();
        list.add("hehe");
        list.add("haha");
        list.add("rrr");
//        int size = list.size();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals("hehe")){
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    @Test
    public void testMap1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Map<String, String> map = new HashMap<String, String>(1);
        map.put("hahaha", "hollischuang");
        map.put("hahaha1", "hollischuang");
        map.put("hahaha2", "hollischuang");

        Class<?> mapType = map.getClass();

        Method capacity = mapType.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("capacity : " + capacity.invoke(map));

        Field size = mapType.getDeclaredField("size");
        size.setAccessible(true);
        System.out.println("size : " + size.get(map));

        Field threshold = mapType.getDeclaredField("threshold");
        threshold.setAccessible(true);
        System.out.println("threshold : " + threshold.get(map));

        Field loadFactor = mapType.getDeclaredField("loadFactor");
        loadFactor.setAccessible(true);
        System.out.println("loadFactor : " + loadFactor.get(map));
    }
}
