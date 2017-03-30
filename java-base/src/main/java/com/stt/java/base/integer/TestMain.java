package com.stt.java.base.integer;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/23.
 */
public class TestMain {

    /*****************************获取int数据的长度***************************************************/

    final static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE };

    // Requires positive x
    static int stringSize(int x) {
        for (int i=0; ; i++)
            if (x <= sizeTable[i])
                return i+1;
    }

    /***************************************************************************************/

    public static void main(String[] args){
        System.out.println(stringSize(1000000000));
    }
}
