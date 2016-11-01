//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.cache;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.yidu.novel.constant.YiDuConstants;

public class CacheManager {
    private static final String CACHE_REGION_NAME = "default";
    private static JCS cache = null;

    private CacheManager() {
    }

    public static void initCacheManager() throws CacheException {
        cache = JCS.getInstance("default");
    }

    public static <T> T getObject(String key) {
        return (T) cache.get(key);
    }

    public static void putObject(String key, Object value) {
        try {
            if(value != null && YiDuConstants.yiduConf.getBoolean("cacheEffective", true)) {
                cache.put(key, value);
            }
        } catch (CacheException var3) {
            var3.printStackTrace();
        }

    }

    public static void dispose() {
        cache.dispose();
    }
}
