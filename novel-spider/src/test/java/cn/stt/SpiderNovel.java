package cn.stt;

import org.yi.spider.loader.InitCfgLoader;

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

}
