package com.stt.util2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/7/7.
 */
public class ComparePhone {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("d:\\oldphone.txt")));
        String temp = null;
        List<String> oldPhoneList  = new ArrayList<>();
        while ((temp = bufferedReader.readLine())!=null){
            String phone = temp;
            String oldPhone = phone.replace("'", "").replace(",", "");
            oldPhoneList.add(oldPhone);
        }
        bufferedReader.close();

        BufferedReader reader = new BufferedReader(new FileReader(new File("d:\\newphone.txt")));
        String temp1 = null;
        List<String> newPhoneList  = new ArrayList<>();
        while ((temp1 = reader.readLine())!=null){
            String phone = temp1;
            newPhoneList.add(phone);
        }
        reader.close();


        for (String phone:oldPhoneList){
            if(!newPhoneList.contains(phone)){
                System.out.println(phone);
            }
        }
    }
}
