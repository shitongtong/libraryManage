package cn.stt.jetty.demo.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/4/18.
 */
public class JettyServer {
    public static void main(String[] args) {
        Server server = new Server(8080);
        WebAppContext context = new WebAppContext();
        context.setContextPath("/ext");
        String ProPath = System.getProperty("user.dir")+"/jetty-demo";
        System.out.println("ProPath=="+ProPath);
        context.setDescriptor(ProPath +"/src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase(ProPath +"/src/main/webapp");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
