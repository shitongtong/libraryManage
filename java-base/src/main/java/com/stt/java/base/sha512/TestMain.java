package com.stt.java.base.sha512;

import com.stt.java.base.util.SecurityUtil;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

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
        String phone = "18353258038";
        phone = "18900000000";
        phone = "13916593205";
        String password1 = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
        System.out.println(password1);
        //a4bda9cd5a0d4de101022096f564774dd4912240d7afeb947817da7afbfb63308e4b2cb0477876392f0ab778282202bf931ca5694ca9aab95909f3f4967f3f5e
        //13a2eae154e9fb77e5a2d6c7492b346aed553f88a7888255b94830cbce6db3457b51ddf49879a23eef4911bdeee8afa7c8c5eaa27fb957afb73eb1bf47d5bdcd
        //47108679cdf08cc75a483db1019adada5c127e4518e94445b02cc8be2f55d6da3d3091f65e9272484c7c3aabc9cdfb3bead8b07283196796ae207390fed9241d
    }

    @Test
    public void test2() {
        String sha512Hex = SecurityUtil.hashSha512Hex("123456");
        System.out.println(sha512Hex);//ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413

        //*6BB4837EB74329105EE4568DDA7DC67ED2CA2AD9
    }

    @Test
    public void test3() {
        String phone = "13916593205";
//        phone = "18353258038";  //教师
        phone = "18840000010";  //教师
        phone = "18830000000";
        String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
        System.out.println(password);
        //47108679cdf08cc75a483db1019adada5c127e4518e94445b02cc8be2f55d6da3d3091f65e9272484c7c3aabc9cdfb3bead8b07283196796ae207390fed9241d
        //a4bda9cd5a0d4de101022096f564774dd4912240d7afeb947817da7afbfb63308e4b2cb0477876392f0ab778282202bf931ca5694ca9aab95909f3f4967f3f5e
        //dd09265420991e9a7f786c3cfae5fa579c9a5cc8187a24e4bc38a9be53eb2488ff2f9d84c716bbc4565d726f993eac008e332f276b50150624aff5b7c6c3e22d
        //1bf5417d18190caa5b7917c5444c23d09d8619e4f2d3cd855ebb95b659b6952385e2c99e24c58869806896fe17910ee2525b577cd4db2f2f27c46c44096ea5b0
        long timestamp = 5454654564L;
        String realPwd = SecurityUtil.hashSha512Hex(password + timestamp);
        System.out.println(realPwd);
        //4074406d96c086055a86b9cd3975d8d28e81d1258412c1972e1fbfb0b8e19d79fa855d13056141cda601e8542860f095b4574163ea5b0ef2b290479e33c801bb
        //5fcf80d36238d5d22ab56ffcfeb2aeafb5064d94aa699a958e25f8bfa6a7c3f15de6bf876834456a9a216fde19ff43afb5cb9e1dc2924768b9e3dc7b26ed6488
        //7cfa2d57d13fd1a0f55810c4e3930b462b9315fd277517ba1d5808a7366b8e2b245a5ca70a4ff99869c1d5397495ca0d388f03577b901e686901c9c8a73c5abe
        //2f02bba697dfca334684f720df611b9a321f0076cede3912b236f20b524745fb2a6802692e77aaba6ef39b0f3ff9245da2c4d1e59ec7b622badbc005e0aee5c7
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

    @Test
    public void test4() {
        String phone = "18940000010";
        phone = "18800000000";
        phone = "18221656604";
        phone = "18850216033";
        phone = "13916593205";
        String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
        System.out.println(password);
        //4f4422c4ee71d91c247bfa4d7d73a508f5115ec48e4f6357c05e8aee05202158c9914930cf053561144401954f9aaf6e82aec1169fb5ba4252946e243943d11c
        //7e95300fc527cf8b6021405379e30d8197745aabe094e8f536ee04c1d52ba29fe0725af550120b0de150bc829480913327b7640a572f56463ad2dc0c285f7477
        //e9ae32856aac6b332c07c142ccbfc125b597c36943a87d9c461804222c818cdd40e732856da5b83ae72b2b12afc1633fd4f466b53aa21fe9ca911eae782f4bcd
        //23bba85e77e937bb30da0aed47e1676ccbe455aecb600eda09c3be1d0576dab1b7b0c58eb128797bf58b2726e73d028da9773882e7e04ac5fadd7247a9e240d4
        //47108679cdf08cc75a483db1019adada5c127e4518e94445b02cc8be2f55d6da3d3091f65e9272484c7c3aabc9cdfb3bead8b07283196796ae207390fed9241d
        long timestamp = 5454654564L;
        String realPwd = SecurityUtil.hashSha512Hex(password + timestamp);
        System.out.println(realPwd);
        //ff86ead6bbecb1abf591c0418a35aa0ba948bc578edea8b58e07d9ba9a16ad3e670e9bad2f2a8681bb4331e26d47187baecd43b709e6bb750a73667d2328fa10
        //37a6f140c32e875705aed9840c926911aa15c488f68cc9f8ab069d569f14d0438107209f56e00fb80d08a91f208e3af323c3ba31250ba201811273f93305daa4
        //7d0bbfdd1e06447fc4d915dfbf149afe06b54933f6046ec0cf4a778278f832ca2d5fed054c862f24fb90b7b61f36799a5aede2d15b01158b2ad2ef735ad9b484
        //149171f465b2238b9c9ca7aed8751de845714e7bcfa322718e90739f896ef2925109ebaa76a4944c1402abe6d003c4c48a0f1dcb421c0316484e60d7a42a1929
        //4074406d96c086055a86b9cd3975d8d28e81d1258412c1972e1fbfb0b8e19d79fa855d13056141cda601e8542860f095b4574163ea5b0ef2b290479e33c801bb
    }

    @Test
    public void test5() {
        //teacher
        String phone = "18353258038";
        phone = "13916593205";
        String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
        System.out.println(password);//a4bda9cd5a0d4de101022096f564774dd4912240d7afeb947817da7afbfb63308e4b2cb0477876392f0ab778282202bf931ca5694ca9aab95909f3f4967f3f5e
    }

    @Test
    public void test6() {
        String password = SecurityUtil.hashSha512Hex("123456");
        System.out.println(password);
    }

    @Test
    public void test7() {
        String phone = "18221593584";
        phone = "15538132882";
        phone = "18175136606";
        phone = "18200153536";
        phone = "13776625715";
        String password = SecurityUtil.hashSha512Hex(phone + "&" + "123456" + ":onlyhi");
        System.out.println(password);
//        String password = "aeccbeac21dc6ed6bb6e8a18b649526dfc2d5d0ff35cbea54ab31338eeca5e19095fb783ee5213e62e6ce1c265c23744927be00866502e0952e5d4d10af0a983";
        long timestamp = 1510811031;
        String realPwd = SecurityUtil.hashSha512Hex(password + timestamp);
        System.out.println(realPwd);
    }

    @Test
    public void test8() {
        String loginName = "admin";
        String password = "!qaz2wsx3edc";
        loginName = "cctest0";
//        loginName = "renyue";
        password = "1234567";
        String password1 = SecurityUtil.hashSha512Hex(loginName + "&" + password + ":onlyhi");
        System.out.println(password1);
        //04f814ab0c87f863f727168b906791448de7a81a26ba5fa27ea408320b7c05525cd0c88318867cc64096566252c43de9fc0bc98d8954a8c32403e147f8f8cda8
        //5ee8795030e3dcb37929621bad0daf14504a4a0a8362a632922983162193cb7b1cdbe767b7782b3cc8b70692f3a8d4f4a02b30945674ba900dabfadf2af62b1e
        //9dd3ef1cb8e0e7fe3cb9231822aabc48d4766b82578658c9dd0daab3b473ebc623f210f74eefe69e1c9d032aea1a4842f5b17870a49c5e28d2350ca65d8d5949
        //924cee02caf01b9d1913924d54b4dffe77029eebde27eec3c467f47594cef8d5491b36158e628b1967770a1a5ac5cbad15e7055f6a639142e41d4b35030a508f
        long timestamp = 5454654564L;
        String password2 = SecurityUtil.hashSha512Hex(password1 + timestamp);
        System.out.println(password2);
        //45eb94a3ac780f9efd63a7f79ed826804961869a8eae517062e8b3e97bd28eb209c242ce32ee908556f915693ae6bbd657cdb5ad6e713fb571ed19082635e6d0
        //12379cb453567a1b5bfb9175a1b62a701e274cfb5f91ea3c15d5ff425ffb5b71b97ea132a96da7daf1bcc1d5bd3512b243a5954b4d69cd5e93266e7430ba14b4
        //fe31e796713500eea0c2e4d3a605f9c22f07f5add3adfd95b4012fdb0d34650d997d9a91ab4179eed6b1fc65f58c1358cca2da1dbda2abe17525f180b5c579c7
        //eda46cc3009625ef32d9d80b749c06c970ccec253c003223750698e6a8ae2b8459da9b900d54ed5224ba98237adc6428e3c67866001c74e480d1d2e623010deb
    }

    @Test
    public void test9() {
        String s = SecurityUtil.hashMD5Hex("79ab6c141bd84eb08621b98063fe23af" + "a8e0dbb830d44c9f8e7e5a4e32c62c53" + "1023862448" + "1516067297");
        System.out.println(s);
    }

    @Test
    public void test10() {
        String pwd = "868fe944c973adcb6904d75c6eab5003c003e57366c2bd90fc21a7543ebc9acf9053c0de97917fe518e71cdf3f57f44613558465aed03aae85070c031b4c115f";
        long timestamp = 1516347916L;
        String password = SecurityUtil.hashSha512Hex(pwd + timestamp);
        System.out.println(password);
    }

    @Test
    public void test11() throws Exception {
        long startTime = System.currentTimeMillis();
//        String imageUrl = "http://clienttest.haiketang.net/uploadPath/courseware/846c6983514e440ab1a666e17ed8be9e/image/1516859807752_-1483080243/0.png";
//        String imageUrlBase = "http://static.onlyhi.cn/uploadPath/courseware/4496AA9F781A4BB994753790CAF15D93/20180310125816299/";
        String imageUrlBase = "http://client.onlyhi.cn/uploadPath/courseware/4496AA9F781A4BB994753790CAF15D93/20180310125816299/";
//        String imageUrlBase = "http://47.100.14.33:20027/uploadPath/courseware/4496AA9F781A4BB994753790CAF15D93/20180310125816299/";
//        String imageUrlBase = "http://clienttest.haiketang.net/uploadPath/courseware/86074E814DFB40D7B75E30F6BFFE61FD/20180310152532737/";
//        String imageUrlBase = "http://static.onlyhi.cn/uploadPath/courseware/test20020/20180212104932300/";

        for (int i = 0; i < 116; i++) {
            long l = System.currentTimeMillis();
            String imageUrl = imageUrlBase + i + ".png";
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            String hex = SecurityUtil.hashMD5Hex(is);
            System.out.println(hex);
            System.out.println(i + ": " + (System.currentTimeMillis() - l) / 1000 + "s");
        }
        System.out.println("总时间: " + (System.currentTimeMillis() - startTime) / 1000 + "s"); //31s

    }

    @Test
    public void test11_1() throws Exception {
        String imageUrl = "http://clienttest.haiketang.net/uploadPath/courseware/21f9d18ed1234139a8a2e590e050bfd8/20180312134945938/0.png";
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        String hex = SecurityUtil.hashMD5Hex(is);
        System.out.println(hex);
    }

    @Test
    public void test12() {
        String s = ",,,";
        String[] split = s.split(",");
        System.out.println(split.length);
    }

    @Test
    public void test13() {
        String phone = "18888888880";
        String password = "csy123456";
        String password1 = SecurityUtil.hashSha512Hex(phone + "&" + password + ":onlyhi");
        System.out.println(password1);
        long timestamp = 1518071798000L;
        String password2 = SecurityUtil.hashSha512Hex(password1 + timestamp);
        System.out.println(password2);
        String pwd = "ef105c2b38c8c0549dddfd0e3330c73de297750eb937495a49376049f1014612262951dbb107ddc5ebdbc8e8e3e280cfb54b35980cc8159b76f805d0394ae36c";
    }

    @Test
    public void test14() {
        String name = "admin";
        String password = "admin123";
        String md5Hex = SecurityUtil.hashMD5Hex(password);
        System.out.println(md5Hex);
    }

}
