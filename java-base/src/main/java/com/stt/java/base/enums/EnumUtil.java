package com.stt.java.base.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/5/27.
 */
public class EnumUtil {

    public static List<EnumObj> getEnumObjeList(BaseEnum baseEnum){
         List<EnumObj> enumObjList = new ArrayList<>();
        if (enumObjList.size() > 0){
            System.out.println("enumObjList已存在");
            return enumObjList;
        }
        EnumObj enumObj = new EnumObj();
        for(BaseEnum leadsSource : baseEnum.values()){
            enumObj = new EnumObj();
            enumObj.setKey(leadsSource.key);
            enumObj.setValue(leadsSource.value);
            enumObjList.add(enumObj);
        }
        System.out.println("enumObjList新建");
        return enumObjList;
    }
}
