package com.stt.util2;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本工具类包含了MD5、SHA512、HMACMD5、BASE64算法
 *
 * @author XuYao
 */
public final class SecurityUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";//默认编码
    private static final int INITIAL_CAPACITY = 100;//初始缓存容量

    private static final Map<String, String> sha1Map = new ConcurrentHashMap<>(INITIAL_CAPACITY);//SHA512缓存类
    private static final Map<String, String> md5Map = new ConcurrentHashMap<>(INITIAL_CAPACITY);//MD5缓存类
    private static final Map<String, String> hmacMd5Map = new ConcurrentHashMap<>(INITIAL_CAPACITY);//HMACMD5缓存类
    private static final Map<String, String> encodeBASE64Map = new ConcurrentHashMap<>(INITIAL_CAPACITY);//EncodeBASE64缓存类
    private static final Map<String, String> decodeBASE64Map = new ConcurrentHashMap<>(INITIAL_CAPACITY);//DecodeBASE64缓存类

    /**
     * 将字符串转换成SHA512小写 注：转换是单向的，不可逆
     *
     * @param data
     * @return
     */
    public static String hashSha512Hex(String data) {
        if (ValidateUtil.isNotEmpty(data)) {
            if (sha1Map.containsKey(data)) {
                return sha1Map.get(data);//返回缓存中的数据
            } else {
                String result = DigestUtils.sha512Hex(data);
                sha1Map.put(data, result);//数据写入缓存
                return result;
            }
        }
        return data;
    }

    /**
     * 将字符串转换成MD5小写 注：转换是单向的，不可逆
     *
     * @param data
     * @return
     */
    public static String hashMD5Hex(String data) {
        if (ValidateUtil.isNotEmpty(data)) {
            if (md5Map.containsKey(data)) {
                return md5Map.get(data);//返回缓存中的数据
            } else {
                String result = DigestUtils.md5Hex(data);//转换
                md5Map.put(data, result);//数据写入缓存
                return result;
            }
        }
        return data;
    }

    /**
     * 将字符串转换成HmacMD5小写 注：转换是单向的，不可逆
     *
     * @param data
     * @param key
     * @return
     */
    public static String hashHmacMD5Hex(String key, String data) {
        if (ValidateUtil.isNotEmpty(data)) {
            String sign = key + data;//拼装缓存Map中的Key值
            if (hmacMd5Map.containsKey(sign)) {
                return hmacMd5Map.get(sign);//返回缓存中的数据
            } else {
                String result = HmacUtils.hmacMd5Hex(key, data);//转换
                hmacMd5Map.put(sign, result);//数据写入缓存
                return result;
            }
        }
        return data;
    }

    /**
     * 对字符串进行BASE64编码
     *
     * @param binaryData
     * @return
     */
    public static String encodeBASE64(String binaryData) {
        if (ValidateUtil.isNotEmpty(binaryData)) {
            if (encodeBASE64Map.containsKey(binaryData)) {
                return encodeBASE64Map.get(binaryData);//返回缓存中的数据
            } else {
                String result = Base64.encodeBase64String(binaryData.getBytes(Charset.forName(DEFAULT_CHARSET)));//编码
                encodeBASE64Map.put(binaryData, result);//数据写入缓存
                return result;
            }
        }
        return binaryData;
    }

    /**
     * 对字符串进行BASE64解码
     *
     * @param base64String
     * @return
     */
    public static String decodeBASE64(String base64String) {
        if (ValidateUtil.isNotEmpty(base64String)) {
            if (decodeBASE64Map.containsKey(base64String)) {
                return decodeBASE64Map.get(base64String);//返回缓存中的数据
            } else {
                String result = new String(Base64.decodeBase64(base64String), Charset.forName(DEFAULT_CHARSET));//解码
                decodeBASE64Map.put(base64String, result);//数据写入缓存
                return result;
            }
        }
        return base64String;
    }
    
    public static void main(String[] args) {
		System.out.println(hashSha512Hex("Haikt!@#"));
	}
}
