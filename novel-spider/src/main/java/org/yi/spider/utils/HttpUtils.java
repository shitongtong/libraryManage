//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.utils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import javax.net.ssl.SSLException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public HttpUtils() {
    }

    public static String getContent(CloseableHttpClient client, String url, String charset, String userAgent) throws Exception {
        try {
            HttpGet e = new HttpGet(url);
            e.addHeader("User-Agent", userAgent);
            CloseableHttpResponse response = client.execute(e);
            HttpEntity entity = response.getEntity();

            String var12;
            try {
                int e1 = response.getStatusLine().getStatusCode();
                if(e1 != 200) {
                    throw new RemoteException("访问对方页面出错， URL:" + url + "，错误码: " + e1);
                }

                long start = System.currentTimeMillis();
                String responseBody = EntityUtils.toString(entity, charset);
                logger.debug("URL:" + url + ",耗时：" + (System.currentTimeMillis() - start));
                var12 = responseBody;
            } catch (IOException var16) {
                throw new IOException("获取目标网页异常， 目标地址：" + url, var16);
            } finally {
                response.close();
            }

            return var12;
        } catch (Exception var18) {
            throw new Exception("访问目标站异常， 目标地址：" + url, var18);
        }
    }

    public static CloseableHttpClient buildClient(int timeOut) {
        HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
            static final int MAX_RETRY_COUNT = 5;

            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if(executionCount >= 5) {
                    return false;
                } else if(exception instanceof InterruptedIOException) {
                    return false;
                } else if(exception instanceof UnknownHostException) {
                    return false;
                } else if(exception instanceof ConnectTimeoutException) {
                    return false;
                } else if(exception instanceof SSLException) {
                    return false;
                } else {
                    HttpClientContext clientContext = HttpClientContext.adapt(context);
                    HttpRequest request = clientContext.getRequest();
                    boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                    return idempotent;
                }
            }
        };
        RequestConfig requestConfig = RequestConfig.custom().setCookieSpec("compatibility").setExpectContinueEnabled(true).setStaleConnectionCheckEnabled(true).setSocketTimeout(timeOut).setConnectTimeout(timeOut).setConnectionRequestTimeout(timeOut).build();
        CloseableHttpClient client = HttpClients.custom().setRetryHandler(retryHandler).setDefaultRequestConfig(requestConfig).build();
        return client;
    }

    public static void closeHttpClient(CloseableHttpClient client) throws IOException {
        try {
            client.close();
        } catch (IOException var2) {
            throw new IOException("关闭连接出错，" + var2.getMessage());
        }
    }
}
