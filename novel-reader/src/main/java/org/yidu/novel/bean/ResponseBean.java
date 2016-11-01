//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ResponseBean<T> {
    private Log logger = LogFactory.getLog(this.getClass());
    private int status;
    private T dataObj;

    public ResponseBean() {
        logger.debug("entry constructor method: ResponseBean()");
    }

    public ResponseBean(int status, T dataObj) {
        logger.debug("entry constructor method: ResponseBean(int status, T dataObj) || status::"+ status + " || dataObj::"+dataObj);
        this.status = status;
        this.dataObj = dataObj;
    }

    public int getStatus() {
        logger.debug("entry method: getStatus() || this.status::"+this.status);
        return this.status;
    }

    public void setStatus(int status) {
        logger.debug("entry method: setStatus(int status) || status::"+status);
        this.status = status;
    }

    public T getDataObj() {
        logger.debug("entry method: getDataObj() || this.dataObj::"+this.dataObj);
        return this.dataObj;
    }

    public void setDataObj(T articleList) {
        logger.debug("entry method: setDataObj(T articleList) || articleList::"+articleList);
        this.dataObj = articleList;
    }
}
