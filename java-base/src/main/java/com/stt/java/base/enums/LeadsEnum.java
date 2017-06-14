package com.stt.java.base.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/5/19.
 */
public class LeadsEnum {

    public static enum LEADS_SOURCE{
        NEWLIST(0,"新名单"),
        DISCARDLIST(1,"藏经阁"),
        DIMISSIONLIST(2,"离职leads名单");

        public int key;
        public String value;
        private static List<EnumObj> enumObjList = new ArrayList<>();

        private LEADS_SOURCE(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public static String getEnumValueByKey(int key){
            for(LEADS_SOURCE leadsSource : LEADS_SOURCE.values()){
                if(key == leadsSource.key){
                    return leadsSource.value;
                }
            }
            return null;
        }

        public static List<EnumObj> getEnumObjeList(){
            if (enumObjList.size() > 0){
                System.out.println("enumObjList已存在");
                return enumObjList;
            }
            EnumObj enumObj = new EnumObj();
            for(LEADS_SOURCE leadsSource : LEADS_SOURCE.values()){
                enumObj = new EnumObj();
                enumObj.setKey(leadsSource.key);
                enumObj.setValue(leadsSource.value);
                enumObjList.add(enumObj);
            }
            System.out.println("enumObjList新建");
            return enumObjList;
        }
    }

    public static enum LEADS_STATUS {
        WAIT_DISTRIBUTION(0,"待分配"),
        WAIT_FOLLOW(1,"待跟进"),
        WAIT_COURSE(2,"待排课"),
        WAIT_FEEDBACK(3,"待反馈"),
        WAIT_PAY(4,"待付费"),
        WAIT_HANDOVER(5,"待移交"),
        GIVE_UP(6,"放弃");

        public int key;
        public String value;

        private LEADS_STATUS(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
