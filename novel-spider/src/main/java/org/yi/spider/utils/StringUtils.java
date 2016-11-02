//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

public class StringUtils {
    private static final String PROTOCAL_SPLIT = "://";
    private static final String EMPTY = "";

    public StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String trimToEmpty(String str) {
        return str == null?"":str.trim();
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null?str2 == null:str1.equals(str2);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null?str2 == null:str1.equalsIgnoreCase(str2);
    }

    public static String stream2String(InputStream inputStream, String charset) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while((line = reader.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        } catch (IOException var14) {
            var14.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }

        return sb.toString();
    }

    public static String removeBlankLine(String source) {
        StringBuffer sb = new StringBuffer();
        StringReader reader = new StringReader(source);

        try {
            BufferedReader e = new BufferedReader(reader);
            String line = null;

            while((line = e.readLine()) != null) {
                if(isNotBlank(line)) {
                    sb.append(line + System.getProperty("line.separator"));
                }
            }

            reader.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return sb.toString();
    }

    public static boolean contains(String[] src, String dest) {
        if(src.length > 0) {
            String[] var5 = src;
            int var4 = src.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                String str = var5[var3];
                if(str.equals(dest)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String replaceHtml(String intro) {
        String[] srcStr = new String[]{"&nbsp;", "<br/>", "<br />"};
        String[] destStr = new String[]{" ", "\n", "\n"};

        for(int i = 0; i < srcStr.length; ++i) {
            intro = intro.replaceAll(srcStr[i], destStr[i]);
        }

        return intro;
    }

    public static String str2Html(String content) {
        if(content != null && !content.isEmpty()) {
            content = content.replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp;");
        }

        return content;
    }

    public static String getFullUrl(String baseUrl, String destUrl) throws Exception {
        try {
            int e = destUrl.indexOf("://");
            if(e > 0) {
                return destUrl;
            } else {
                e = destUrl.indexOf("/");
                if(e >= 0) {
                    destUrl = destUrl.substring(e, destUrl.length());
                }

                if(baseUrl.endsWith("/") && destUrl.startsWith("/")) {
                    baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
                }

                destUrl = baseUrl + destUrl;
                return destUrl;
            }
        } catch (Exception var3) {
            throw new Exception("获取完整地址错误： " + var3.getMessage());
        }
    }
}
