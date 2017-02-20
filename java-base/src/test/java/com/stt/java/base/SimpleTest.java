package com.stt.java.base;

import org.junit.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Administrator on 2017-01-09.
 *
 * @author shitongtong
 */
public class SimpleTest {

    private static  String AA = "aa";

    public static void main(String[] args) {
//        this.AA = "";//报错
        List<String> list = new ArrayList<String>();
        List<Integer> list1 = new ArrayList<Integer>();
    }



    @Test
    public void testString1() {
        this.AA  = "";
        String str1 = "hello";
        String str2 = new String("hello");
        System.out.println(str1 == str2);//false
        String str3 = str2.intern();
        System.out.println(str1 == str2);//false
        System.out.println(str1 == str3);//true
        System.out.println(str2 == str3);//false
    }

    @Test
    public void testString2() {
        String str = "as" + "ff";
        String str1 = "asff";

        /*
        public void testString2();
  Code:
     0: ldc           #8                  // String asff
     2: astore_1
     3: ldc           #8                  // String asff
     5: astore_2
     6: return
         */

        org.junit.Assert.assertTrue(str == str1);

        String str2 = "as";
        String str3 = str2 + "ff";

        org.junit.Assert.assertFalse(str == str3);
        org.junit.Assert.assertFalse(str1 == str3);

        /*

          public void testString2();
    Code:
       0: ldc           #8                  // String asff
       2: astore_1
       3: ldc           #8                  // String asff
       5: astore_2
       6: aload_1
       7: aload_2
       8: if_acmpne     15
      11: iconst_1
      12: goto          16
      15: iconst_0
      16: invokestatic  #9                  // Method org/junit/Assert.assertTru
e:(Z)V
      19: ldc           #10                 // String as
      21: astore_3
      22: new           #11                 // class java/lang/StringBuilder
      25: dup
      26: invokespecial #12                 // Method java/lang/StringBuilder."<
init>":()V
      29: aload_3
      30: invokevirtual #13                 // Method java/lang/StringBuilder.ap
pend:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      33: ldc           #14                 // String ff
      35: invokevirtual #13                 // Method java/lang/StringBuilder.ap
pend:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      38: invokevirtual #15                 // Method java/lang/StringBuilder.to
String:()Ljava/lang/String;
      41: astore        4
      43: aload_1
      44: aload         4
      46: if_acmpne     53
      49: iconst_1
      50: goto          54
      53: iconst_0
      54: invokestatic  #16                 // Method org/junit/Assert.assertFal
se:(Z)V
      57: aload_2
      58: aload         4
      60: if_acmpne     67
      63: iconst_1
      64: goto          68
      67: iconst_0
      68: invokestatic  #16                 // Method org/junit/Assert.assertFal
se:(Z)V
      71: return


         */
    }

    @Test
    public void testStringBuffer() {
        StringBuffer stringBuffer = new StringBuffer();
        int length = stringBuffer.length();
        System.out.println(length);//0
    }

    @Test
    public void testFinal() {
        final StringBuffer stringBuffer = new StringBuffer("abc");
//        stringBuffer = new StringBuffer();
        stringBuffer.append("vvv");
        final String str = stringBuffer.toString();
        System.out.println(str);
    }

    @Test
    public void testSpringAssert() {
        Assert.notNull(null, "this object must not null");
        System.out.println("hahah");//没有输出,抛出异常：java.lang.IllegalArgumentException: this object must not null
    }

    @Test
    public void testVector() {
        long startTime = System.currentTimeMillis();
        Object obj = new Object();
        Vector v = new Vector(100000);
        for (int i = 0; i < 100000; i++) {
            v.add(0, obj);
        }
        System.out.println(System.currentTimeMillis() - startTime);//1246   1071(未初始化容量)

        long startTime2 = System.currentTimeMillis();
        Object obj2 = new Object();
        Vector v2 = new Vector();
        for (int i = 0; i < 100000; i++) {
            v2.add(obj2);
        }
        System.out.println(System.currentTimeMillis() - startTime2);//3     7
    }
}
