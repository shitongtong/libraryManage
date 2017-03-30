package com.stt.message;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import java.io.*;
import java.net.URLDecoder;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/30.
 */
public class TestSendHttp {
    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient();
        System.setProperty("http.proxyHost", "106.14.46.51");
//        httpClient.getHostConfiguration().setProxy("106.14.46.51",80);
        //需要验证
//        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("root", "Onlyhi2016!@#");
//        httpClient.getState().setProxyCredentials(AuthScope.ANY, creds);
        //设置http头
//        List<Header> headers = new ArrayList<Header>();
//        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
//        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
//        httpClient.getParams().setAuthenticationPreemptive(true);
        // get调用
        GetMethod method = new GetMethod("http://www.baidu.com");
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));

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
                String decode = URLDecoder.decode(baos.toString(), "UTF-8");
                System.out.println("decode==="+decode);
            } else {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        }finally {
            method.releaseConnection();
        }
    }


    @Test
    public void testHttp() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        //设置代理开始。如果代理服务器需要验证的话，可以修改用户名和密码
        //192.168.1.107为代理地址 808为代理端口 UsernamePasswordCredentials后的两个参数为代理的用户名密码
//        client.getCredentialsProvider().setCredentials(new AuthScope("106.14.46.51",80), new UsernamePasswordCredentials("", ""));
        HttpHost proxy = new HttpHost("192.168.1.182", 8080);
        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        //设置代理结束
        HttpGet get = new HttpGet("http://www.baidu.com/");
        HttpResponse response = client.execute(get);
        //打印出状态码
        System.out.println(response.getStatusLine());
        //获得返回的内容，循环遍历出
        HttpEntity entity = response.getEntity();
        String str = null;
        if (entity != null) {
            InputStream instream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            while(( str = reader.readLine()) != null) {
                System.out.println(str);
            }
            instream.close();
            reader.close();
        }
        //遍历内容结束
    }
}
