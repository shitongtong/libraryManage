//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptUtils {
    private static final Logger logger = LoggerFactory.getLogger(ScriptUtils.class);

    public ScriptUtils() {
    }

    public static <T, E> E eval(String express, Map<String, T> params) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        if(params == null) {
            params = new HashMap();
        }

        Iterator iter = ((Map)params).entrySet().iterator();
        Entry entry = null;

        while(iter.hasNext()) {
            entry = (Entry)iter.next();
            engine.put((String)entry.getKey(), entry.getValue());
        }

        Object result = null;

        try {
            result = engine.eval(express);
        } catch (ScriptException var8) {
            logger.warn("表达式执行异常： " + var8.getMessage());
        }

        return (E) result;
    }

    public static <T> Integer evalInt(String express, Map<String, T> params) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        if(params == null) {
            params = new HashMap();
        }

        Iterator iter = ((Map)params).entrySet().iterator();
        Entry entry = null;

        while(iter.hasNext()) {
            entry = (Entry)iter.next();
            engine.put((String)entry.getKey(), entry.getValue());
        }

        Integer result = null;

        try {
            result = Integer.valueOf((new BigDecimal(String.valueOf(engine.eval(express)))).intValue());
        } catch (ScriptException var8) {
            logger.warn("表达式执行异常： " + var8.getMessage());
        }

        return result;
    }

    public static <T> Boolean evalBoolean(String express, Map<String, T> params) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        if(params == null) {
            params = new HashMap();
        }

        Iterator iter = ((Map)params).entrySet().iterator();
        Entry entry = null;

        while(iter.hasNext()) {
            entry = (Entry)iter.next();
            engine.put((String)entry.getKey(), entry.getValue());
        }

        Boolean result = null;

        try {
            result = (Boolean)engine.eval(express);
        } catch (ScriptException var8) {
            result = Boolean.valueOf(false);
            logger.warn("表达式执行异常： " + var8.getMessage());
        }

        return result;
    }
}
