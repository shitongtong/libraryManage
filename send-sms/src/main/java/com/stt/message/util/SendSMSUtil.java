package com.stt.message.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/30.
 */
public class SendSMSUtil {
    /**
     *
     * @param url   应用地址，类似于http://ip:port/msg/
     * @param account   账号
     * @param pswd  密码
     * @param mobile    手机号码，多个号码使用","分割
     * @param msg   短信内容
     * @param needstatus    是否需要状态报告，需要true，不需要false
     * @param extno 扩展码
     * @return  返回值定义参见HTTP协议文档
     * @throws Exception
     */
    public static String SendSMS(String url, String account, String pswd, String mobile, String msg,boolean needstatus, String extno) throws Exception {
        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        GetMethod method = new GetMethod();
        try {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "HttpBatchSendSM", false));
            method.setQueryString(new NameValuePair[] {
                    new NameValuePair("account", account),
                    new NameValuePair("pswd", pswd),
                    new NameValuePair("mobile", mobile),
                    new NameValuePair("needstatus", String.valueOf(needstatus)),
                    new NameValuePair("msg", msg),
                    new NameValuePair("extno", extno),
            });
            System.out.println("method===="+method);
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                return URLDecoder.decode(baos.toString(), "UTF-8");
            } else {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        } finally {
            method.releaseConnection();
        }
    }

    //设置代理
    public static String SendSMSByProxy(String url, String account, String pswd, String mobile, String msg,boolean needstatus, String extno) throws Exception {
        HttpClient httpClient = new HttpClient();
        httpClient.getHostConfiguration().setProxy("106.14.46.51",8090);
        //需要验证
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("root", "Onlyhi2016!@#");
        httpClient.getState().setProxyCredentials(AuthScope.ANY, creds);
        //设置http头
//        List<Header> headers = new ArrayList<Header>();
//        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
//        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
        httpClient.getParams().setAuthenticationPreemptive(true);
        // get调用
//        GetMethod method = new GetMethod(url);
//        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//                new DefaultHttpMethodRetryHandler(3, false));
        GetMethod method = new GetMethod();
        URI base = new URI(url, false);
        method.setURI(new URI(base, "HttpBatchSendSM", false));
        method.setQueryString(new NameValuePair[] {
                new NameValuePair("account", account),
                new NameValuePair("pswd", pswd),
                new NameValuePair("mobile", mobile),
                new NameValuePair("needstatus", String.valueOf(needstatus)),
                new NameValuePair("msg", msg),
                new NameValuePair("extno", extno),
        });
        try {
            int result = httpClient.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                return URLDecoder.decode(baos.toString(), "UTF-8");
            } else {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        }finally {
            method.releaseConnection();
        }

    }

    /**
     * 短信发送返回响应状态值说明
     * @param state 错误码
     * @return
     */
    public static String getStateValue(int state){
        String stateValue = "";
        switch (state) {
            case 101:
                stateValue = "无此用户";
                break;
            case 102:
                stateValue = "密码错";
                break;
            case 103:
                stateValue = "提交过快（提交速度超过流速限制）";
                break;
            case 104:
                stateValue = "系统忙（因平台侧原因，暂时无法处理提交的短信）";
                break;
            case 105:
                stateValue = "敏感短信（短信内容包含敏感词）";
                break;
            case 106:
                stateValue = "消息长度错（>536或<=0）";
                break;
            case 107:
                stateValue = "包含错误的手机号码";
                break;
            case 108:
                stateValue = "手机号码个数错（群发>50000或<=0;单发>200或<=0）";
                break;
            case 109:
                stateValue = "无发送额度（该用户可用短信数已使用完）";
                break;
            case 110:
                stateValue = "不在发送时间内";
                break;
            case 111:
                stateValue = "超出该账户当月发送额度限制";
                break;
            case 112:
                stateValue = "无此产品，用户没有订购该产品";
                break;
            case 113:
                stateValue = "extno格式错（非数字或者长度不对）";
                break;
            case 115:
                stateValue = "自动审核驳回";
                break;
            case 116:
                stateValue = "签名不合法，未带签名（用户必须带签名的前提下）";
                break;
            case 117:
                stateValue = "IP地址认证错,请求调用的IP地址不是系统登记的IP地址";
                break;
            case 118:
                stateValue = "用户没有相应的发送权限";
                break;
            case 119:
                stateValue = "用户已过期";
                break;
            case 120:
                stateValue = "测试内容不是白名单";
                break;
            default:
                stateValue = "无法识别错误码";
                break;
        }
        return stateValue;
    }
}
