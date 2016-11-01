//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.filter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.yidu.novel.filter.GZIPResponseStream;

class GZIPResponseWrapper extends HttpServletResponseWrapper {
    protected HttpServletResponse origResponse = null;
    protected ServletOutputStream stream = null;
    protected PrintWriter writer = null;

    public GZIPResponseWrapper(HttpServletResponse response) {
        super(response);
        this.origResponse = response;
    }

    public ServletOutputStream createOutputStream() throws IOException {
        return new GZIPResponseStream(this.origResponse);
    }

    public void finishResponse() {
        try {
            if(this.writer != null) {
                this.writer.close();
            } else if(this.stream != null) {
                this.stream.close();
            }
        } catch (IOException var2) {
            ;
        }

    }

    public void flushBuffer() throws IOException {
        this.stream.flush();
    }

    public ServletOutputStream getOutputStream() throws IOException {
        if(this.writer != null) {
            throw new IllegalStateException("getWriter() has already been called!");
        } else {
            if(this.stream == null) {
                this.stream = this.createOutputStream();
            }

            return this.stream;
        }
    }

    public PrintWriter getWriter() throws IOException {
        if(this.writer != null) {
            return this.writer;
        } else if(this.stream != null) {
            throw new IllegalStateException("getOutputStream() has already been called!");
        } else {
            this.stream = this.createOutputStream();
            this.writer = new PrintWriter(new OutputStreamWriter(this.stream, "UTF-8"));
            return this.writer;
        }
    }

    public void setContentLength(int length) {
    }
}
