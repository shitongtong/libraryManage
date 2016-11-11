package cn.stt.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DateSecret {

    private static String keyCode = "liunian1";//秘钥可以任意改，只要总长度是8个字节就行
    private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};

    public String encryptDES(String encryptString)
            throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(keyCode.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));
        cn.stt.controller.Base64 base = new cn.stt.controller.Base64();
        return base.encode(encryptedData);
    }

    public String decryptDES(String decryptString)
            throws Exception {
        Base64 base = new Base64();
        byte[] byteMi = base.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(keyCode.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData, "utf-8");
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return String
     */
    public String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
}