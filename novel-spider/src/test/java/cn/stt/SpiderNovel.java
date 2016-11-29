package cn.stt;

import org.junit.Test;
import org.yi.spider.constants.GlobalConfig;
import org.yi.spider.loader.InitCfgLoader;
import org.yi.spider.model.Category;
import org.yi.spider.utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Administrator on 2016-11-10.
 */
public class SpiderNovel {

    public static void main(String[] args) {
        try {
            InitCfgLoader.load();
            if(args == null || args.length == 0) {
                args = new String[]{"-ca"};
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadFile() {
        BufferedReader reader = null;

        try {
//            File file = FileUtils.locateAbsolutePathFromClasspath("category.ini");
//            File file = FileUtils.locateFromClasspath("category.ini").getFile();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileUtils.locateFromClasspath("category.ini").getFile()), "UTF-8"));
            String e = null;

            while ((e = reader.readLine()) != null) {
                System.out.println(e);
            }

            reader.close();
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException var11) {
                    var11.printStackTrace();
                }
            }
        }
    }
}
