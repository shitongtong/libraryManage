package com.stt.shiro.chapter5;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;

/**
 * Created by Administrator on 2016-12-27.
 *
 * @author shitongtong
 */
public class CodecAndCryptoTest {

    //base64编码/解码操作
    @Test
    public void testBase64(){
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        System.out.println("base64Encoded=="+base64Encoded);//aGVsbG8=
        String str2 = Base64.decodeToString(base64Encoded);
        Assert.assertEquals(str,str2);
    }

    //16进制字符串编码/解码操作
    @Test
    public void testHex(){
        String str = "hello";
        String hexEncoded = Hex.encodeToString(str.getBytes());
        System.out.println("hexEncoded=="+hexEncoded);//68656c6c6f
        String str2 = new String(Hex.decode(hexEncoded.getBytes()));
        Assert.assertEquals(str,str2);
    }

    @Test
    public void testCodecSupport(){
        String str = "hello";
        byte[] bytes = CodecSupport.toBytes(str, "UTF-8");
        String str2 = CodecSupport.toString(bytes, "UTF-8");
        Assert.assertEquals(str,str2);
    }

    //MD5算法
    @Test
    public void testMD5(){
        String str = "hello";
        String salt = "123";//salt:盐
        String md5 = new Md5Hash(str, salt).toString();//还可以转换为 toBase64()/toHex()
        System.out.println("md5=="+md5);//86fcb4c0551ea48ede7df5ed9626eee7
        //指定散列次数，如2次表示md5(md5(str))：“new Md5Hash(str, salt, 2).toString()”。
        String md5_2 = new Md5Hash(str, salt,2).toString();
        System.out.println("md5_2=="+md5_2);//c942f011ced5f36de066dd2d948538cb
    }

    //sha1算法
    @Test
    public void testSha1(){
        String str = "hello";
        String salt = "123";//salt:盐
        String sha1 = new Sha1Hash(str, salt).toString();
        System.out.println(sha1);//f0a139408f7b134c66342e3d1cf4870a293c11c3
        String sha1_2 = new Sha1Hash(str, salt,2).toString();
        System.out.println(sha1_2);//0a91ef959a3c6567fcad2e1ba02fc600200c3b31
    }

    //SHA256算法
    @Test
    public void testSHA256(){
        String str = "hello";
        String salt = "123";
        String Sha256Hash = new Sha256Hash(str, salt).toString();
        System.out.println(Sha256Hash);//7dfe54ea69b2d07a597952e49374a1aebf3c10689444a83f0a084761c8a1c626
        String Sha256Hash_2 = new Sha256Hash(str, salt,2).toString();
        System.out.println(Sha256Hash_2);//b1039379b0c8a52df0369891d1aaf97c7089ac336df0bfbd6d12bac3848fd097
    }

    //sha384
    @Test
    public void testSha384(){
        String str = "hello";
        String salt = "123";
        String Sha384Hash = new Sha384Hash(str, salt).toString();
        //b18fad48be86ede658ae8b850137757d630772726f7ed70c2439cf42d536d9d20c0f377b546c49639586217b72c41077
        //96
        System.out.println(Sha384Hash.length());
        String Sha384Hash_2 = new Sha384Hash(str, salt,2).toString();
        System.out.println(Sha384Hash_2);//0f83d89df8e74e63b8ab6b151a0b3b1eee753ca5553fe3dd9f9060b19fce707c622fcc513a24ab9e6d1e2145f712cccd
    }

    @Test
    public void testSha512() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha512Hash(str, salt).toString();
        System.out.println(sha1);

    }

    @Test
    public void testSimpleHash() {
        String str = "hello";
        String salt = "123";
        //MessageDigest
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
        System.out.println(simpleHash);
    }

    @Test
    public void testHashService(){
        //1、首先创建一个DefaultHashService，默认使用SHA-512算法；
        DefaultHashService hashService = new DefaultHashService();//默认算法SHA-512
        //2、可以通过hashAlgorithmName属性修改算法；
        hashService.setHashAlgorithmName("SHA-512");
        //3、可以通过privateSalt设置一个私盐，其在散列时自动与用户传入的公盐混合产生一个新盐；
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        //4、可以通过generatePublicSalt属性在用户没有传入公盐的情况下是否生成公盐；
        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
        //5、可以设置randomNumberGenerator用于生成公盐；
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
        //6、可以设置hashIterations属性来修改默认加密迭代次数；
        hashService.setHashIterations(1);//生成Hash值的迭代次数
        //7、需要构建一个HashRequest，传入算法、数据、公盐、迭代次数。
        HashRequest hashRequest = new HashRequest.Builder().setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();

        String hex = hashService.computeHash(hashRequest).toHex();
        System.out.println(hex);//fd2b413d4f8c465db16d51ce3e8dc18e
        /*错误：
        String decodeHex = new String(Hex.decode(hex));
        System.out.println(decodeHex);*/
    }

    //SecureRandomNumberGenerator用于生成一个随机数：
    @Test
    public void testSecureRandomNumberGenerator(){
        SecureRandomNumberGenerator randomNumberGenerator =
                new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("123".getBytes());
        String hex = randomNumberGenerator.nextBytes().toHex();
        System.out.println(hex);//23ae809ddacaf96af0fd78ed04b6a265
    }

    //AES算法实现：
    @Test
    public void testAesCipherService(){
        AesCipherService aesCipherService = new AesCipherService();
        //设置key长度
        aesCipherService.setKeySize(128);
        //生成key
        Key key = aesCipherService.generateNewKey();

        String text = "hello";

        //加密
        String encrptText = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        //解密
        String decryptText = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        Assert.assertEquals(text, decryptText);
    }

    @Test
    public void testBlowfishCipherService() {
        BlowfishCipherService blowfishCipherService = new BlowfishCipherService();
        blowfishCipherService.setKeySize(128);

        //生成key
        Key key = blowfishCipherService.generateNewKey();

        String text = "hello";

        //加密
        String encrptText = blowfishCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        //解密
        String text2 = new String(blowfishCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        Assert.assertEquals(text, text2);
    }

    @Test
    public void testDefaultBlockCipherService() throws Exception {


        //对称加密，使用Java的JCA（javax.crypto.Cipher）加密API，常见的如 'AES', 'Blowfish'
        DefaultBlockCipherService cipherService = new DefaultBlockCipherService("AES");
        cipherService.setKeySize(128);

        //生成key
        Key key = cipherService.generateNewKey();

        String text = "hello";

        //加密
        String encrptText = cipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        //解密
        String text2 = new String(cipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        Assert.assertEquals(text, text2);
    }


    //加密/解密相关知识可参考snowolf的博客 http://snowolf.iteye.com/category/68576
}
