package com.stt.java.base.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 *
 * Created by Administrator on 2017-02-16.
 *
 * @author shitongtong
 */
public class ClassLocationUtils {

    /**
     * 获取运行时类的加载路径
     * @param clazz
     * @return
     */
    public static String classLocation(final Class clazz) {
        if (clazz == null){
            throw new IllegalArgumentException("null input: clazz");
        }
        URL result = null;

        final String clazzAsResource  = clazz.getName().replace(".", "/").concat(".class");
        System.out.println("===clazzAsResource==="+clazzAsResource+"======");
        final ProtectionDomain protectionDomain = clazz.getProtectionDomain();

        if (protectionDomain != null){
            final CodeSource codeSource = protectionDomain.getCodeSource();
            if (codeSource != null){
                result = codeSource.getLocation();
            }
            if (result != null){
                if ("file".equals(result.getProtocol())){
                    String externalForm = result.toExternalForm();
                    System.out.println("===externalForm==="+externalForm+"======");
                    try {
                        if (externalForm.endsWith(".jar") || externalForm.endsWith(".zip")) {
                            result = new URL("jar:".concat(externalForm).concat("!/").concat(clazzAsResource));
                        } else {
                            if (new File(result.getFile()).isDirectory()) {
                                result = new URL(result, clazzAsResource);
                            }
                        }
                    }catch (MalformedURLException e){
                        e.printStackTrace();
                    }
                }
            }

            if (result == null){
                final ClassLoader classLoader = clazz.getClassLoader();
                result = classLoader!=null?classLoader.getResource(clazzAsResource):ClassLoader.getSystemResource(clazzAsResource);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String classLocation = classLocation(Map.class);
        Map map1 = new Map();
        map1.print();
//        Map map = new HashMap();
//        map.put("","");
        System.out.println("===classLocation==="+classLocation+"======");
    }

}
