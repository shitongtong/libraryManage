package com.stt.java.base.java8.lambdas.book.examples.chapter3;

/**
 * 6. 计算一个字符串中小写字母的个数（提示：参阅String 对象的chars 方法）。
 *
 * Created by Administrator on 2017-02-19.
 *
 * @author shitongtong
 */
public class Question6 {

    public static void main(String[] args) {
        String str = "HHHdsds";


        /*
        //传统做法：
        int count = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 97 && chars[i]<122){
                ++count;
            }
        }
        System.out.println(count);//4
        */

//        long count = str.chars().filter(a -> Character.isLowerCase(a)).count();

        //author
        long count = str.chars().filter(Character::isLowerCase).count();

        System.out.println(count);

    }

    public static int getLowerCaseCount(String str){
        return (int) str.chars().filter(Character::isLowerCase).count();
    }
}
