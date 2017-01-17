package com.stt.java.base;

/**
 * 同一个类调用属性和方法，用不用this貌似没有区别啊？？
 * 反编译class文件，发现java编译时都加上了this，那么编写代码时，加上this会提高编译效率吗？？
 *
 * Created by Administrator on 2017-01-11.
 *
 * @author shitongtong
 */
public class TestThis {


    public static void main(String[] args) {
        long nanoTimeStart = System.nanoTime();
        long startTime = System.currentTimeMillis();
        System.out.println(nanoTimeStart);//692577241028390
        System.out.println(startTime);//1484100197544
        long nanoTimeEnd = System.nanoTime();
        long endTime = System.currentTimeMillis();
        System.out.println(nanoTimeEnd);//692577241282044
        System.out.println(endTime);//1484100197544
        System.out.println(nanoTimeEnd - nanoTimeStart);//253654
        System.out.println(endTime - startTime);//0

    }

    private String str  = "abc";
    public void getStr(){
//        str = "qqq";
//        this.str = "kkk";
    }

    public void testMethodInvoke(){
        getStr();
        this.getStr();

        String a = str;
        a = this.str;
    }

    /*
   javap -c TestThis

    警告: 二进制文件TestThis包含com.stt.java.base.TestThis
Compiled from "TestThis.java"
public class com.stt.java.base.TestThis {
  public com.stt.java.base.TestThis();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":
()V
       4: aload_0
       5: ldc           #2                  // String
       7: putfield      #3                  // Field str:Ljava/lang/String;
      10: return

  public static void main(java.lang.String[]);
    Code:
       0: return

  public void getStr();
    Code:
       0: aload_0
       1: ldc           #4                  // String qqq
       3: putfield      #3                  // Field str:Ljava/lang/String;
       6: aload_0
       7: ldc           #5                  // String kkk
       9: putfield      #3                  // Field str:Ljava/lang/String;
      12: return

  public void testMethodInvoke();
   Code:
      0: aload_0
      1: invokevirtual #6                  // Method getStr:()V
      4: aload_0
      5: invokevirtual #6                  // Method getStr:()V
      8: return
}


     */

}
