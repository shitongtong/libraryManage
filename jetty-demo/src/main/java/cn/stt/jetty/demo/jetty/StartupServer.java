package cn.stt.jetty.demo.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;

import java.util.concurrent.Executors;

/**
 * 启动jetty测试服务
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/4/15.
 */
public class StartupServer {
    public static void main(String[] args)throws Exception {




        ResourceHandler resourceHandler = new ResourceHandler();

        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("/resources");
        resourceHandler.setStylesheet("");

        ContextHandler staticContextHandler = new ContextHandler();
        staticContextHandler.setContextPath("/resources");
        //staticContextHandler.setContextPath("/files");
        staticContextHandler.setHandler(resourceHandler);




        //添加servlet支持
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        servletContextHandler.setContextPath("/");

        /**可在下面依次添加多个servlet**/
//        servletContextHandler.addServlet(new ServletHolder(new MainController()), "/show");
        //servletContextHandler.addServlet(new ServletHolder(new SpuSimilarityServlet()), "/spu");
        //servletContextHandler.addServlet(new ServletHolder(new SpuResultServlet()), "/search");
        //	servletContextHandler.addServlet(new ServletHolder(new AdminServlet()), "/db");
        HandlerList handlers = new HandlerList();
        handlers.addHandler(servletContextHandler);
        handlers.addHandler(staticContextHandler);

        int hosts=8888;//端口号

        Server server = new Server(hosts);
        // 设置线程池
        ThreadPool threadPool = new ExecutorThreadPool(Executors.newFixedThreadPool(5));
        server.setThreadPool(threadPool);
        // 设置连接参数
        Connector connector = new SelectChannelConnector();
        // 设置监听端口
        connector.setPort(hosts);
        // 为服务设置连接器
        server.setConnectors(new Connector[] { connector });
        server.setHandler(handlers);

        server.start();
        server.join();


    }
}
