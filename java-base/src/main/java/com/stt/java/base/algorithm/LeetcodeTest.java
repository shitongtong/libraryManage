package com.stt.java.base.algorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author shitt7
 * @Date 2021/4/1 9:54
 */
public class LeetcodeTest {
    public static void main(String[] args) {
        LeetcodeTest leetcodeTest = new LeetcodeTest();
        System.out.println(leetcodeTest.clumsy(10));
    }

    /**
     * 1006 笨阶乘
     * @param N
     * @return
     */
    public int clumsy(int N) {
        // 栈模拟实现
        Deque<Integer> stack = new LinkedList<>();
        stack.push(N);
        // 控制 * / + -
        int index = 0;
        while(N-->1){
            if(index%4==0){
                stack.push(stack.pop()*N);
            }else if(index%4==1){
                stack.push(stack.pop()/N);
            }else if(index%4==2){
                stack.push(N);
            }else{
                stack.push(-N);
            }
            index++;
        }
        // 将栈中元素依次弹出取和
        int sum = 0;
        while(!stack.isEmpty()){
            sum+=stack.pop();
        }
        return sum;
    }
}
