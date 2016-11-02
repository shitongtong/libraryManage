//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.pool2;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.yi.spider.pool2.ChapterPooledObjectFactory;
import org.yi.spider.service.IChapterService;

public class ChapterObjectPool {
    private static GenericKeyedObjectPool<String, IChapterService> chapterPool;

    private ChapterObjectPool() {
    }

    public static GenericKeyedObjectPool<String, IChapterService> getPool() {
        if(chapterPool == null) {
            Class var0 = ChapterObjectPool.class;
            synchronized(ChapterObjectPool.class) {
                if(chapterPool == null) {
                    chapterPool = new GenericKeyedObjectPool(new ChapterPooledObjectFactory());
                }
            }
        }

        return chapterPool;
    }
}
