//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.utils;

import java.nio.charset.Charset;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesUtils {
    public PropertiesUtils() {
    }

    public static PropertiesConfiguration load(String fileName, String charset) throws ConfigurationException {
        PropertiesConfiguration conf = new PropertiesConfiguration();
        conf.setEncoding(charset);
        conf.setFileName(fileName);
        conf.load();
        return conf;
    }

    public static PropertiesConfiguration load(String fileName, Charset charset) throws ConfigurationException {
        return load(fileName, charset.name());
    }

    public static PropertiesConfiguration load(String fileName) throws ConfigurationException {
        return new PropertiesConfiguration(fileName);
    }

    public static HierarchicalINIConfiguration loadIni(String fileName, String charset) throws ConfigurationException {
        HierarchicalINIConfiguration conf = new HierarchicalINIConfiguration();
        conf.setEncoding(charset);
        conf.setFileName(fileName);
        conf.load();
        return conf;
    }

    public static HierarchicalINIConfiguration loadIni(String fileName, Charset charset) throws ConfigurationException {
        return loadIni(fileName, charset.displayName());
    }

    public static HierarchicalINIConfiguration loadIni(String fileName) throws ConfigurationException {
        return new HierarchicalINIConfiguration(fileName);
    }
}
