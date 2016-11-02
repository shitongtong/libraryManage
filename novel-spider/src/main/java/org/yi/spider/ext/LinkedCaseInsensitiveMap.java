//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.ext;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class LinkedCaseInsensitiveMap<V> extends LinkedHashMap<String, V> {
    private static final long serialVersionUID = -1605894759708367851L;
    private final Map<String, String> caseInsensitiveKeys;
    private final Locale locale;

    public LinkedCaseInsensitiveMap() {
        this((Locale)null);
    }

    public LinkedCaseInsensitiveMap(Locale locale) {
        this.caseInsensitiveKeys = new HashMap();
        this.locale = locale != null?locale:Locale.getDefault();
    }

    public LinkedCaseInsensitiveMap(int initialCapacity) {
        this(initialCapacity, (Locale)null);
    }

    public LinkedCaseInsensitiveMap(int initialCapacity, Locale locale) {
        super(initialCapacity);
        this.caseInsensitiveKeys = new HashMap(initialCapacity);
        this.locale = locale != null?locale:Locale.getDefault();
    }

    public V put(String key, V value) {
        this.caseInsensitiveKeys.put(this.convertKey(key), key);
        return super.put(key, value);
    }

    public void putAll(Map<? extends String, ? extends V> map) {
        if(!map.isEmpty()) {
            Iterator var3 = map.entrySet().iterator();

            while(var3.hasNext()) {
                Entry entry = (Entry)var3.next();
                this.put((String)entry.getKey(), (V) entry.getValue());
            }

        }
    }

    public boolean containsKey(Object key) {
        return key instanceof String && this.caseInsensitiveKeys.containsKey(this.convertKey((String)key));
    }

    public V get(Object key) {
        return key instanceof String?super.get(this.caseInsensitiveKeys.get(this.convertKey((String)key))):null;
    }

    public V remove(Object key) {
        return key instanceof String?super.remove(this.caseInsensitiveKeys.remove(this.convertKey((String)key))):null;
    }

    public void clear() {
        this.caseInsensitiveKeys.clear();
        super.clear();
    }

    protected String convertKey(String key) {
        return key.toLowerCase(this.locale);
    }
}
