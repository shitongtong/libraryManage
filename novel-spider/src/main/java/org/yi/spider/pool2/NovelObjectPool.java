//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.pool2;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.yi.spider.pool2.NovelPooledObjectFactory;
import org.yi.spider.service.INovelService;

public class NovelObjectPool {
    private static GenericKeyedObjectPool<String, INovelService> novelPool;

    private NovelObjectPool() {
    }

    public static GenericKeyedObjectPool<String, INovelService> getPool() {
        if(novelPool == null) {
            Class var0 = NovelObjectPool.class;
            synchronized(NovelObjectPool.class) {
                if(novelPool == null) {
                    novelPool = new GenericKeyedObjectPool(new NovelPooledObjectFactory());
                }
            }
        }

        return novelPool;
    }
}
