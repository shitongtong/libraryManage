//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.pool2;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.yi.spider.pool2.HtmlBuilderPooledObjectFactory;
import org.yi.spider.service.IHtmlBuilder;

public class HtmlBuilderObjectPool {
    private static GenericKeyedObjectPool<String, IHtmlBuilder> htmlBuilderPool;

    private HtmlBuilderObjectPool() {
    }

    public static GenericKeyedObjectPool<String, IHtmlBuilder> getPool() {
        if(htmlBuilderPool == null) {
            Class var0 = HtmlBuilderObjectPool.class;
            synchronized(HtmlBuilderObjectPool.class) {
                if(htmlBuilderPool == null) {
                    htmlBuilderPool = new GenericKeyedObjectPool(new HtmlBuilderPooledObjectFactory());
                }
            }
        }

        return htmlBuilderPool;
    }
}
