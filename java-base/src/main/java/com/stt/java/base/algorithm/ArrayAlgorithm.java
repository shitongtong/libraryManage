package com.stt.java.base.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * leetcode 数组和字符串
 *
 * @Author shitt7
 * @Date 2021/3/15 14:38
 */
public class ArrayAlgorithm {
    /**
     * 寻找数组的中心索引
     * 输入：nums = [1, 7, 3, 6, 5, 6]
     * 输出：3
     *
     * @param nums
     * @return
     */
    public static int pivotIndex(int[] nums) {
        int tsum = 0;
        for (int i = 0; i < nums.length; i++) {
            tsum += nums[i];
        }
        int lsum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (lsum * 2 + nums[i] == tsum) {
                return i;
            }
            lsum += nums[i];
        }
        return -1;
    }

    /**
     * 合并区间
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] < intervals[i + 1][0]) {
                //区间不重叠
                list.add(intervals[i]);
            } else {
                //区间重叠
                intervals[i + 1][0] = intervals[i][0];
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
            }
        }
        list.add(intervals[intervals.length - 1]);
        return list.toArray(new int[list.size()][2]);
    }

    public static int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> list = new ArrayList<>();
        int[] ints = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (ints[1] < intervals[i][0]) {
                //区间不重叠
                list.add(ints);
                ints = intervals[i];
            } else {
                //区间重叠
                ints[1] = Math.max(ints[1], intervals[i][1]);
            }
        }
        list.add(ints);
        return list.toArray(new int[list.size()][2]);
    }

    /**
     * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
     *
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        //为0的行数
        List<Integer> ilist = new ArrayList<>();
        //为0的列数
        List<Integer> jlist = new ArrayList<>();
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            int[] m = matrix[i];
            int mlen = m.length;
            for (int j = 0; j < mlen; j++) {
                if (matrix[i][j] == 0) {
                    ilist.add(i);
                    jlist.add(j);
                }
            }
        }
        //行置为0
        for (int i = 0; i < ilist.size(); i++) {
            int mlen = matrix[ilist.get(i)].length;
            for (int j = 0; j < mlen; j++) {
                matrix[ilist.get(i)][j] = 0;
            }
        }
        //列置为0
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < jlist.size(); j++) {
                matrix[i][jlist.get(j)] = 0;
            }
        }
        System.out.println();
    }

    public static String longestPalindrome(String s) {
        //双指针 babad
        int left = 0;
        int right = s.length();
        String result1 = "";
        while(right>=left){
            boolean flag = true;
            for(int i=0;i<right-left;i++){
                if(s.charAt(i)==(s.charAt(right-left-1))){
                    flag = flag&&true;
                }else{
                    flag = flag&&false;
                }
            }
            if(flag){
                result1 = s.substring(left,right);
            }
            left++;
        }
        left = 0;
        right = s.length();
        String result2 = "";
        while(right>=left){
            boolean flag = true;
            for(int i=0;i<right-left;i++){
                char c = s.charAt(i);
                char c1 = s.charAt(right - left - 1);
                if(c == c1){
                    flag = flag&&true;
                }else{
                    flag = flag&&false;
                }
            }
            if(flag){
                result2 = s.substring(left,right);
            }
            right--;
        }
        left = 0;
        right = s.length();
        String result3 = "";
        while(right>=left){
            boolean flag = true;
            for(int i=0;i<right-left;i++){
                if(s.charAt(i)==(s.charAt(right-left-1))){
                    flag = flag&&true;
                }else{
                    flag = flag&&false;
                }
            }
            if(flag){
                result3 = s.substring(left,right);
            }
            left++;
            right--;
        }
        String result4="";
        if(result1.length()>result2.length()){
            result4 = result1;
        }else{
            result4=result2;
        }
        String result="";
        if(result4.length()>result3.length()){
            result = result4;
        }else{
            result=result3;
        }
        return result;
    }

    /**
     * 翻转字符串里的单词
     * 输入：s = "  Bob    Loves  Alice   "
     输出："Alice Loves Bob"
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        StringBuilder sb = trimSpaces(s);

        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }

    public static StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            ++left;
        }
        return sb;
    }

    public static void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public static void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }

    public static void main(String[] args) {
//        int[] nums = {1, 7, 3, 6, 5, 6};
//        System.out.println(pivotIndex(nums));

//        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] merge = merge2(intervals);
//        System.out.println(Arrays.toString(merge));

//        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
//        setZeroes(matrix);
//        String s = "babad";
//        System.out.println(longestPalindrome(s));

        String s = "  Bob    Loves  Alice   ";
        System.out.println(reverseWords(s));
        s.indexOf(s);
    }
}
