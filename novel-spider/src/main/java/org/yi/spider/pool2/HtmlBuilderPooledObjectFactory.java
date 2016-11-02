//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.pool2;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.yi.spider.factory.impl.ServiceFactory;
import org.yi.spider.service.IHtmlBuilder;

public class HtmlBuilderPooledObjectFactory extends BaseKeyedPooledObjectFactory<String, IHtmlBuilder> {
    public HtmlBuilderPooledObjectFactory() {
    }

    public IHtmlBuilder create(String key) throws Exception {
        return (new ServiceFactory()).createHtmlBuilder(key);
    }

    public PooledObject<IHtmlBuilder> wrap(IHtmlBuilder value) {
        return new DefaultPooledObject(value);
    }
}
