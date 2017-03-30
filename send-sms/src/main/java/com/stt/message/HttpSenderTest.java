package com.stt.message;

import com.stt.message.util.HttpSender;
import com.stt.message.util.SendSMSUtil;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/30.
 */
public class HttpSenderTest {
    public static void main(String[] args) {

        String url = "http://sms.253.com/msg/";// 应用地址
        url = "http://sapi.253.com/msg/";
        String un = "angli1";// 账号
        String pw = "Haiketang_2016";// 密码
        String phone = "18012345678";// 手机号码，多个号码使用","分割
        String msg = "【你的签名】您好，你的验证码是123456";// 短信内容
        String rd = "1";// 是否需要状态报告，需要1，不需要0
        String ex = null;// 扩展码


        try {
            String result = HttpSender.batchSend(url, un, pw, phone, msg, rd, ex);
            System.out.println(result);
            // TODO 处理返回值,参见HTTP协议文档
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
        } catch (Exception e) {
            // TODO 处理异常
            e.printStackTrace();
        }
    }
}
