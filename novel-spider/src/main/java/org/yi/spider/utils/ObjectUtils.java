//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.utils;

public class ObjectUtils {
    public ObjectUtils() {
    }

    public static Integer obj2Int(Object obj) {
        int i = 0;
        if(obj != null) {
            i = Integer.parseInt(String.valueOf(obj));
        }

        return Integer.valueOf(i);
    }
}
