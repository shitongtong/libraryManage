package org.tuckey.web.filters.urlrewrite;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.Status;
import org.tuckey.web.filters.urlrewrite.UrlRewriteWrappedResponse;
import org.tuckey.web.filters.urlrewrite.UrlRewriter;
import org.tuckey.web.filters.urlrewrite.utils.Log;
import org.tuckey.web.filters.urlrewrite.utils.ModRewriteConfLoader;
import org.tuckey.web.filters.urlrewrite.utils.NumberUtils;
import org.tuckey.web.filters.urlrewrite.utils.ServerNameMatcher;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;
import org.yidu.novel.constant.YiDuConstants;

/**
 * Created by Administrator on 2016-10-31.
 */
public class UrlRewriteFilter implements Filter {
        private static Log log = Log.getLog(UrlRewriteFilter.class);
        /** @deprecated */
        public static final String VERSION = "4.0.3";
        public static final String DEFAULT_WEB_CONF_PATH = "/WEB-INF/urlrewrite.xml";
        private UrlRewriter urlRewriter = null;
        private boolean confReloadCheckEnabled = false;
        private int confReloadCheckInterval = 0;
        private boolean allowConfSwapViaHttp = false;
        private long confLastLoad = 0L;
        private Conf confLastLoaded = null;
        private long confReloadLastCheck = 30L;
        private boolean confLoadedFromFile = true;
        private String confPath;
        private boolean confReloadInProgress = false;
        private boolean statusEnabled = true;
        private String statusPath = "/rewrite-status";
        private boolean modRewriteStyleConf = false;
        public static final String DEFAULT_MOD_REWRITE_STYLE_CONF_PATH = "/WEB-INF/.htaccess";
        private ServerNameMatcher statusServerNameMatcher;
        private static final String DEFAULT_STATUS_ENABLED_ON_HOSTS = "localhost, local, 127.0.0.1";
        private ServletContext context = null;
        private static long INITIALISED_TIME = System.currentTimeMillis();

        public UrlRewriteFilter() {
        }

        public void init(FilterConfig filterConfig) throws ServletException {
            log.debug("filter init called");
            if(filterConfig == null) {
                log.error("unable to init filter as filter config is null");
            } else {
                log.debug("init: calling destroy just in case we are being re-inited uncleanly");
                this.destroyActual();
                this.context = filterConfig.getServletContext();
                if(this.context == null) {
                    log.error("unable to init as servlet context is null");
                } else {
                    Log.setConfiguration(filterConfig);
                    String confReloadCheckIntervalStr = filterConfig.getInitParameter("confReloadCheckInterval");
                    String confPathStr = filterConfig.getInitParameter("confPath");
                    String statusPathConf = filterConfig.getInitParameter("statusPath");
                    String statusEnabledConf = filterConfig.getInitParameter("statusEnabled");
                    String statusEnabledOnHosts = filterConfig.getInitParameter("statusEnabledOnHosts");
                    String allowConfSwapViaHttpStr = filterConfig.getInitParameter("allowConfSwapViaHttp");
                    if(!StringUtils.isBlank(allowConfSwapViaHttpStr)) {
                        this.allowConfSwapViaHttp = "true".equalsIgnoreCase(allowConfSwapViaHttpStr);
                    }

                    if(!StringUtils.isBlank(confReloadCheckIntervalStr)) {
                        this.confReloadCheckInterval = 1000 * NumberUtils.stringToInt(confReloadCheckIntervalStr);
                        if(this.confReloadCheckInterval < 0) {
                            this.confReloadCheckEnabled = false;
                            log.info("conf reload check disabled");
                        } else if(this.confReloadCheckInterval == 0) {
                            this.confReloadCheckEnabled = true;
                            log.info("conf reload check performed each request");
                        } else {
                            this.confReloadCheckEnabled = true;
                            log.info("conf reload check set to " + this.confReloadCheckInterval / 1000 + "s");
                        }
                    } else {
                        this.confReloadCheckEnabled = false;
                    }

                    String modRewriteConf = filterConfig.getInitParameter("modRewriteConf");
                    if(!StringUtils.isBlank(modRewriteConf)) {
                        this.modRewriteStyleConf = "true".equals(StringUtils.trim(modRewriteConf).toLowerCase());
                    }

                    if(!StringUtils.isBlank(confPathStr)) {
                        this.confPath = StringUtils.trim(confPathStr);
                    } else {
                        this.confPath = this.modRewriteStyleConf?"/WEB-INF/.htaccess":"/WEB-INF/urlrewrite.xml";
                    }

                    log.debug("confPath set to " + this.confPath);
                    if(statusEnabledConf != null && !"".equals(statusEnabledConf)) {
                        log.debug("statusEnabledConf set to " + statusEnabledConf);
                        this.statusEnabled = "true".equals(statusEnabledConf.toLowerCase());
                    }

                    if(this.statusEnabled) {
                        if(statusPathConf != null && !"".equals(statusPathConf)) {
                            this.statusPath = statusPathConf.trim();
                            log.info("status display enabled, path set to " + this.statusPath);
                        }
                    } else {
                        log.info("status display disabled");
                    }

                    if(StringUtils.isBlank(statusEnabledOnHosts)) {
                        statusEnabledOnHosts = "localhost, local, 127.0.0.1";
                    } else {
                        log.debug("statusEnabledOnHosts set to " + statusEnabledOnHosts);
                    }

                    this.statusServerNameMatcher = new ServerNameMatcher(statusEnabledOnHosts);
                    String modRewriteConfText = filterConfig.getInitParameter("modRewriteConfText");
                    if(!StringUtils.isBlank(modRewriteConfText)) {
                        ModRewriteConfLoader loader = new ModRewriteConfLoader();
                        Conf conf = new Conf();
                        loader.process(modRewriteConfText, conf);
                        conf.initialise();
                        this.checkConf(conf);
                        this.confLoadedFromFile = false;
                    } else {
                        this.loadUrlRewriter(filterConfig);
                    }

                }
            }
        }

        protected void loadUrlRewriter(FilterConfig filterConfig) throws ServletException {
            try {
                this.loadUrlRewriterLocal();
            } catch (Throwable var3) {
                log.error(var3);
                throw new ServletException(var3);
            }
        }

        private void loadUrlRewriterLocal() {
            InputStream inputStream = this.context.getResourceAsStream(this.confPath);
            if(inputStream == null) {
                inputStream = ClassLoader.getSystemResourceAsStream(this.confPath);
            }

            URL confUrl = null;

            try {
                confUrl = this.context.getResource(this.confPath);
            } catch (MalformedURLException var5) {
                log.debug(var5);
            }

            String confUrlStr = null;
            if(confUrl != null) {
                confUrlStr = confUrl.toString();
            }

            if(inputStream == null) {
                log.error("unable to find urlrewrite conf file at " + this.confPath);
                if(this.urlRewriter != null) {
                    log.error("unloading existing conf");
                    this.urlRewriter = null;
                }
            } else {
                Conf conf = new Conf(this.context, inputStream, this.confPath, confUrlStr, this.modRewriteStyleConf);
                this.checkConf(conf);
            }

        }

        protected void checkConf(Conf conf) {
            this.checkConfLocal(conf);
        }

        private void checkConfLocal(Conf conf) {
            if(log.isDebugEnabled()) {
                if(conf.getRules() != null) {
                    log.debug("inited with " + conf.getRules().size() + " rules");
                }

                log.debug("conf is " + (conf.isOk()?"ok":"NOT ok"));
            }

            this.confLastLoaded = conf;
            if(conf.isOk() && conf.isEngineEnabled()) {
                this.urlRewriter = new UrlRewriter(conf);
                log.info("loaded (conf ok)");
            } else {
                if(!conf.isOk()) {
                    log.error("Conf failed to load");
                }

                if(!conf.isEngineEnabled()) {
                    log.error("Engine explicitly disabled in conf");
                }

                if(this.urlRewriter != null) {
                    log.error("unloading existing conf");
                    this.urlRewriter = null;
                }
            }

        }

        public void destroy() {
            log.info("destroy called");
            this.destroyActual();
            Log.resetAll();
        }

        public void destroyActual() {
            this.destroyUrlRewriter();
            this.context = null;
            this.confLastLoad = 0L;
            this.confPath = "/WEB-INF/urlrewrite.xml";
            this.confReloadCheckEnabled = false;
            this.confReloadCheckInterval = 0;
            this.confReloadInProgress = false;
        }

        protected void destroyUrlRewriter() {
            if(this.urlRewriter != null) {
                this.urlRewriter.destroy();
                this.urlRewriter = null;
            }

        }

        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            if(!YiDuConstants.yiduConf.getBoolean("cleanUrl", true)) {
                chain.doFilter(request, response);
            } else {
                UrlRewriter urlRewriter = this.getUrlRewriter(request, response, chain);
                HttpServletRequest hsRequest = (HttpServletRequest)request;
                HttpServletResponse hsResponse = (HttpServletResponse)response;
                UrlRewriteWrappedResponse urlRewriteWrappedResponse = new UrlRewriteWrappedResponse(hsResponse, hsRequest, urlRewriter);
                if(this.statusEnabled && this.statusServerNameMatcher.isMatch(request.getServerName())) {
                    String requestRewritten = hsRequest.getRequestURI();
                    if(log.isDebugEnabled()) {
                        log.debug("checking for status path on " + requestRewritten);
                    }

                    String contextPath = hsRequest.getContextPath();
                    if(requestRewritten != null && requestRewritten.startsWith(contextPath + this.statusPath)) {
                        this.showStatus(hsRequest, urlRewriteWrappedResponse);
                        return;
                    }
                }

                boolean requestRewritten1 = false;
                if(urlRewriter != null) {
                    requestRewritten1 = urlRewriter.processRequest(hsRequest, urlRewriteWrappedResponse, chain);
                } else if(log.isDebugEnabled()) {
                    log.debug("urlRewriter engine not loaded ignoring request (could be a conf file problem)");
                }

                if(!requestRewritten1) {
                    chain.doFilter(hsRequest, urlRewriteWrappedResponse);
                }

            }
        }

        protected UrlRewriter getUrlRewriter(ServletRequest request, ServletResponse response, FilterChain chain) {
            if(this.isTimeToReloadConf()) {
                this.reloadConf();
            }

            return this.urlRewriter;
        }

        public boolean isTimeToReloadConf() {
            if(!this.confLoadedFromFile) {
                return false;
            } else {
                long now = System.currentTimeMillis();
                return this.confReloadCheckEnabled && !this.confReloadInProgress && now - (long)this.confReloadCheckInterval > this.confReloadLastCheck;
            }
        }

        public void reloadConf() {
            long now = System.currentTimeMillis();
            this.confReloadInProgress = true;
            this.confReloadLastCheck = now;
            log.debug("starting conf reload check");
            long confFileCurrentTime = this.getConfFileLastModified();
            if(this.confLastLoad < confFileCurrentTime) {
                this.confLastLoad = System.currentTimeMillis();
                log.info("conf file modified since last load, reloading");

                try {
                    this.loadUrlRewriterLocal();
                } catch (Exception var6) {
                    log.error("Error in reloading the conf file. No rules to be applied for subsequent requests.", var6);
                }
            } else {
                log.debug("conf is not modified");
            }

            this.confReloadInProgress = false;
        }

        private long getConfFileLastModified() {
            if(this.context != null) {
                String realPath = this.context.getRealPath(this.confPath);
                if(realPath != null) {
                    File confFile = new File(this.context.getRealPath(this.confPath));
                    return confFile.lastModified();
                }
            }

            return INITIALISED_TIME;
        }

        private void showStatus(HttpServletRequest request, ServletResponse response) throws IOException {
            log.debug("showing status");
            if(this.allowConfSwapViaHttp) {
                String status = request.getParameter("conf");
                if(!StringUtils.isBlank(status)) {
                    this.confPath = status;
                    this.loadUrlRewriterLocal();
                }
            }

            Status status1 = new Status(this.confLastLoaded, this);
            status1.displayStatusInContainer(request);
            response.setContentType("text/html; charset=UTF-8");
            response.setContentLength(status1.getBuffer().length());
            PrintWriter out = response.getWriter();
            out.write(status1.getBuffer().toString());
            out.close();
        }

        public boolean isConfReloadCheckEnabled() {
            return this.confReloadCheckEnabled;
        }

        public int getConfReloadCheckInterval() {
            return this.confReloadCheckInterval / 1000;
        }

        public Date getConfReloadLastCheck() {
            return new Date(this.confReloadLastCheck);
        }

        public boolean isStatusEnabled() {
            return this.statusEnabled;
        }

        public String getStatusPath() {
            return this.statusPath;
        }

        public boolean isLoaded() {
            return this.urlRewriter != null;
        }

        public static String getFullVersionString() {
            Properties props = new Properties();
            String buildNumberStr = "";

            try {
                InputStream e = UrlRewriteFilter.class.getResourceAsStream("build.number.properties");
                if(e != null) {
                    props.load(e);
                    String buildNumber = (String)props.get("build.number");
                    if(!StringUtils.isBlank(buildNumber)) {
                        buildNumberStr = props.get("project.version") + " build " + props.get("build.number");
                    }
                }
            } catch (IOException var4) {
                log.error(var4);
            }

            return buildNumberStr;
        }
}
