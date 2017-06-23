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
    public void testStudentSha512() {
        String password1 = SecurityUtil.hashSha512Hex("13994413502" + "&" + "123456" + ":onlyhi");
        String password2 = SecurityUtil.hashSha512Hex("15936665112" + "&" + "123456" + ":onlyhi");
        String password3 = SecurityUtil.hashSha512Hex("15073438981" + "&" + "123456" + ":onlyhi");
        String password4 = SecurityUtil.hashSha512Hex("18947417999" + "&" + "123456" + ":onlyhi");
        String password5 = SecurityUtil.hashSha512Hex("15001962050" + "&" + "123456" + ":onlyhi");
        String password6 = SecurityUtil.hashSha512Hex("15191392296" + "&" + "123456" + ":onlyhi");
        //一次加密：
        /*
        password1==666c2522323244008e12dcdb1b21e0f1f078a32aaca15c69203209af3442e2cfd59d89c97a6e11279ca03a66b1ab8cbe31702ed6dfe9c828449c01e949a0f830
        password2==1397067a2e351ca6830a46bc3fe9e28e68a30d4fa908bbcd93f1797cfa35751f5ff76c2ab752905cbef83add83354a119d266d4a8eb735a2beccb16332fd8263
        password3==623b87ec2084c477f7d32e3f2869520a8c57b89f77895723d3cf9145bf71272e67b59c493e9d7788b79231032fcbcdf83fcb086a8b0fb65f58f347071ae4a3cc
        password4==23798d6a3ea3faec9af47382fe5f0c56fdb7dc6c61059e7ccd424c504e7958c90e58c66e431cf05c404bacfc09d9335699a239e86078f98ff3085bbfe1872313
        password5==03edc6e955acfb15b47971152e37369aad54b15ec885b20be178e312fe0b3ea18435a2210c674ff799fd39c84b93bc286c27db68a8421f0f7861b89a9f9a7e94
        password6==3fe31aef40f252a752d6d893e40f2a38d68f81e3af80440465f2f7a3b9795bbd7268f9d01b05a1b1d55b55bcad7d1d338bbcb4bf2af3c87de54b4be0a0198e3e
         */
        System.out.println("password1==" + password1);
        System.out.println("password2==" + password2);
        System.out.println("password3==" + password3);
        System.out.println("password4==" + password4);
        System.out.println("password5==" + password5);
        System.out.println("password6==" + password6);

        long timestamp = 5454654564L;
//        timestamp = 1491897385490L;
        String realPwd = SecurityUtil.hashSha512Hex(password6 + timestamp);
        //p2:   17411dd16835293b40a1712108e5fb18fe4eaed72f3dda6983951ce7911de1e601cfb6d243963e31172f799a2b27ccec05df2715772ad743302cfbacad9453ef
        //p6：   95cfe7a72db93bb1943750a07aa7c60e24b9b95e1271da670a98a9ef1485e1d43ccec12b4d7df829133952ad2dcbed49bfce520960f3d46a54692863a9ec84b7
        System.out.println(realPwd);
    }

    @Test
    public void testTeacherSha512() {

        //测试环境：
        //1:    952d710bced1c5b0bfc37384c0ce61a684105656f52e7f84790f5a3603ecea02dd7f2f655865101a872a9a4ef06dbc33972c14dc569799741a61b0cf602519aa
        //2:    b1672d65eb0ed397f55cf28fcd6d85b091c0f1834f8f2471268677f7ed611bfb1bb9dff9522ade2ebebd87f8eae65b5434e07cea78ba60dbf224603bc8617c4e
        String password = SecurityUtil.hashSha512Hex("13985698596" + "&" + "123456" + ":onlyhi");

        //公网测试环境：
//        String password = SecurityUtil.hashSha512Hex("18260051329" + "&" + "123456" + ":onlyhi");
        //一次加密： bccead564888eeac672b8458ba8d888b25142df78fa56a8c396e3da72a14240a601a059853ee7c1cf75d2edf9a7d4a64fb669c2b42c7f0dbfab6f0d6cd203519
        System.out.println("password==" + password);

        long timestamp = 5454654564L;
        String realPwd = SecurityUtil.hashSha512Hex(password + timestamp);
        //二次加密：   70840080c92a8e709410558ee83e9d3db85e1e90f9b0a3239c67184e796346dc33ef61b1f1bfa436d414559462f4608b0878f2f83d73eaaea7be5034adc3a163
        System.out.println(realPwd);
    }

    @Test
    public void test1() {
        String password1 = SecurityUtil.hashSha512Hex("15052851735" + "&" + "123456" + ":onlyhi");
        System.out.println(password1);
    }

    @Test
    public void test2() {
        String sha512Hex = SecurityUtil.hashSha512Hex("123456");
        System.out.println(sha512Hex);//ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413

        //*6BB4837EB74329105EE4568DDA7DC67ED2CA2AD9
    }

    @Test
    public void test3() {
        String password = SecurityUtil.hashSha512Hex("18900000040" + "&" + "123456" + ":onlyhi");
        long timestamp = 5454654564L;
        String realPwd = SecurityUtil.hashSha512Hex(password + timestamp);
        System.out.println(realPwd);
    }

    @Test
    public void addPatriarch() {
        String[] strs = {"18920000001", "18920000002", "18920000003", "18920000004", "18920000005", "18920000006", "18920000007", "18920000008",
                "18920000009", "18920000010", "18920000011", "18920000012"};
        for (int i = 0; i < strs.length; i++) {
            String phone = strs[i];
            String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
            System.out.println(password);
        }
    }


}
