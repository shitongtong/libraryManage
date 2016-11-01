//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.filter.GZIPResponseWrapper;

public class GzipFilter implements Filter {
    private FilterConfig filterConfig = null;

    public GzipFilter() {
    }

    protected final FilterConfig getFilterConfig() {
        return this.filterConfig;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(!YiDuConstants.yiduConf.getBoolean("gzipEffective", false)) {
            chain.doFilter(req, res);
        } else {
            if(req instanceof HttpServletRequest && !((HttpServletRequest)req).getRequestURI().startsWith("/download")) {
                HttpServletRequest request = (HttpServletRequest)req;
                HttpServletResponse response = (HttpServletResponse)res;
                String ae = request.getHeader("accept-encoding");
                if(ae != null && ae.indexOf("gzip") != -1) {
                    GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
                    chain.doFilter(req, wrappedResponse);
                    wrappedResponse.finishResponse();
                    return;
                }
            }

            chain.doFilter(req, res);
        }

    }
}
