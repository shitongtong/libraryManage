package cn.stt.jetty.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/4/13.
 */
public class MainService {
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir")
                + "jetty-demo/src/main";
        System.out.println(System.getProperty("user.dir"));

        try {
            /*Server server = new Server(8888);

            WebAppContext context = new WebAppContext();
            context.setContextPath("/");
            context.setDescriptor(filePath + "/webapp/WEB-INF/web.xml"); // 指定web.xml配置文件
//            context.setDescriptor("D:\\project_git\\libraryManage\\jetty-demo\\src\\main\\webapp\\WEB-INF/web.xml"); // 指定web.xml配置文件
            context.setResourceBase(filePath+"/webapp/");// 指定webapp目录
//            context.setResourceBase("D:\\project_git\\libraryManage\\jetty-demo\\src\\main\\webapp\\webapp\\");// 指定webapp目录
            context.setParentLoaderPriority(true);

            server.setHandler(context);
            server.start();
            server.join();*/



            Server server = new Server(8888);
            WebAppContext context = new WebAppContext();
            context.setContextPath("/");
            context.setDescriptor("./webapp/WEB-INF/web.xml"); // 指定web.xml配置文件
            context.setResourceBase("./webapp/");
            ProtectionDomain protectionDomain = MainService.class.getProtectionDomain();
            URL location = protectionDomain.getCodeSource().getLocation();
            System.out.println(location.toExternalForm());
            context.setWar(location.toExternalForm());
            server.setHandler(context);
            server.start();
            server.join();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
