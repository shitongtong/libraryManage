package com.stt.java.base.enums;

import org.junit.Test;

import java.util.List;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/5/27.
 */
public class TestMain {

    @Test
    public void test(){
        String value = LeadsEnum.LEADS_SOURCE.getEnumValueByKey(0);
        System.out.println(value);
        List<EnumObj> enumObjeList = LeadsEnum.LEADS_SOURCE.getEnumObjeList();
        for (EnumObj enumObj : enumObjeList){
            System.out.println(enumObj);
        }
        List<EnumObj> enumObjeList1 = LeadsEnum.LEADS_SOURCE.getEnumObjeList();
        for (EnumObj enumObj : enumObjeList1){
            System.out.println(enumObj);
        }
    }
}
