package com.stt.java.base.test;

/**
 * @ClassName OperatorsAndExpressions
 * @Description TODO
 * @Author shitt7
 * @Date 2019/4/11 19:24
 * @Version 1.0
 */
public class OperatorsAndExpressions {
    void equalsMethodl() {
        String s1 = new String("how are you");
        String s2 = new String("how are you");
        System.out.println(s1 == s2);
    }

    public static void main(String args[]) {
        OperatorsAndExpressions operAndExp = new OperatorsAndExpressions();
        //用于复合类型数据的"=="运算符
        operAndExp.equalsMethodl();
    }

}
