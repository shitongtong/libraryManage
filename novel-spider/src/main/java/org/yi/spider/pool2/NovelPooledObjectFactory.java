//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.pool2;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.yi.spider.factory.impl.ServiceFactory;
import org.yi.spider.service.INovelService;

public class NovelPooledObjectFactory extends BaseKeyedPooledObjectFactory<String, INovelService> {
    public NovelPooledObjectFactory() {
    }

    public INovelService create(String key) throws Exception {
        return (new ServiceFactory()).createNovelService(key);
    }

    public PooledObject<INovelService> wrap(INovelService value) {
        return new DefaultPooledObject(value);
    }
}
