package com.stt.java.base.java8.demo1;

/**
 * Created by Administrator on 2017-01-13.
 *
 * @author shitongtong
 */
public class TestFormula {

    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return this.sqrt(a*100);
            }
        };

        double calculate = formula.calculate(100);
        double sqrt = formula.sqrt(100);
        System.out.println(calculate);//100.0
        System.out.println(sqrt);//10.0
    }
}
