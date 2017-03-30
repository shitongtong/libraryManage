package com.stt.java.base.sha512;

import com.stt.java.base.util.SecurityUtil;
import org.junit.Test;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/3/28.
 */
public class TestMain {

    @Test
    public void testStudentSha512(){
        String password = SecurityUtil.hashSha512Hex("13796113960" + "&" + "123456" + ":onlyhi");
        //一次加密： f60cdbfa86e7c2a125eec8f202035ecc0bfae9d6bdfc8f36567e71aeecd3587c921ddc29fdc337b26b65d0a0a427f2548ca8a911b3b2ce37c88c81479995c326
        System.out.println("password=="+password);

        long timestamp = 5454654564L;
        String realPwd = SecurityUtil.hashSha512Hex(password + timestamp);
        //二次加密：   c5ae04951b96c642149dfd2a105c6ee144424c2a12fc76a681635517a73d4788628cad0fcc1a7381db49adf6838afd5028c490825ac752d084d8f9a21988ba0e
        System.out.println(realPwd);
    }

    @Test
    public void testTeacherSha512(){
        String password = SecurityUtil.hashSha512Hex("18260051329" + "&" + "123456" + ":onlyhi");
        //一次加密： bccead564888eeac672b8458ba8d888b25142df78fa56a8c396e3da72a14240a601a059853ee7c1cf75d2edf9a7d4a64fb669c2b42c7f0dbfab6f0d6cd203519
        System.out.println("password=="+password);

        long timestamp = 5454654564L;
        String realPwd = SecurityUtil.hashSha512Hex(password + timestamp);
        //二次加密：   70840080c92a8e709410558ee83e9d3db85e1e90f9b0a3239c67184e796346dc33ef61b1f1bfa436d414559462f4608b0878f2f83d73eaaea7be5034adc3a163
        System.out.println(realPwd);
    }


}
