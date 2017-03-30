package com.stt.message;

import com.stt.message.util.SendSMSUtil;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/30.
 */
public class TestSendSMS {
    public static void main(String[] args) {
        String url = "http://sapi.253.com/msg/";// 应用地址
		String account = "angli1";// 账号
		String pswd = "Haiketang_2016";// 密码

        String mobile = "13916593205";
        String msg = "你好！123 ……&*￥# haha";
        boolean needstatus = false;// 是否需要状态报告，需要true，不需要false
        String extno = null;// 扩展码

        String result = "";
        try {
            System.setProperty("http.proxyHost", "106.14.46.51");
            System.setProperty("http.proxyHost", "admin.onlyeduhi.com");
            System.setProperty("http.proxyHost", "crm.onlyhi.com");
            result = SendSMSUtil.SendSMS(url, account, pswd, mobile, msg, needstatus, extno);
//            result = SendSMSUtil.SendSMSByProxy(url, account, pswd, mobile, msg, needstatus, extno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result); //20170330134545,117
        if(!"".equals(result)){ //不为空则解析
            String[] returnStr = result.split("\n");
            String[] timeAndState = returnStr[0].split(",");
            int state = Integer.parseInt(timeAndState[1]);
            if(state == 0){
                System.out.println("OK");
            }else{
                System.out.println(SendSMSUtil.getStateValue(state));
            }
        }
    }
}
