package com.stt;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketException;

/**
 * @ClassName TelnetUtil
 * @Description TODO
 * @Author shitt7
 * @Date 2018/11/29 16:17
 * @Version 1.0
 */
public class TelnetUtil {
    /**
     * 写入命令方法
     *
     * @param cmd
     * @param os
     */
    public static void writeUtil(String cmd, OutputStream os) {
        try {
            cmd = cmd + "\n";
            os.write(cmd.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读到指定位置,不在向下读
     *
     * @param endFlag
     * @param in
     * @return
     */
    public static String readUntil(String endFlag, InputStream in) {

        InputStreamReader isr = new InputStreamReader(in);

        char[] charBytes = new char[1024];
        int n = 0;
        boolean flag = false;
        String str = "";
        try {
            while ((n = isr.read(charBytes)) != -1) {
                for (int i = 0; i < n; i++) {
                    char c = (char) charBytes[i];
                    str += c;
                    //当拼接的字符串以指定的字符串结尾时,不在继续读
                    if (str.endsWith(endFlag)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    public static void main(String[] args) {

        try {
            TelnetClient tc = new TelnetClient();
            tc.connect("47.52.228.140", 23);
            InputStream in = tc.getInputStream();
            OutputStream os = tc.getOutputStream();

            System.out.print(readUntil(":", in));

            writeUtil("root", os);
            System.out.print(readUntil(":", in));

            writeUtil("freewificom", os);
            System.out.print(readUntil("root@WiAC:~#", in));


            writeUtil("ls -l / ", os);
            System.out.print(readUntil("root@WiAC:~#", in));

            writeUtil("ip addr ", os);
            System.out.print(readUntil("root@WiAC:~#", in));

            writeUtil("ip route ", os);
            System.out.print(readUntil("root@WiAC:~#", in));

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
