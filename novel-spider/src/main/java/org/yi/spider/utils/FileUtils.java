//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yi.spider.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yi.spider.utils.StringUtils;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static final String PROTOCOL_FILE = "file";
    private static final String FILE_SCHEME = "file:";
    private static final int HEX = 16;
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    public FileUtils() {
    }

    public static File fileFromURL(URL url) {
        if(url != null && url.getProtocol().equals("file")) {
            String filename = url.getFile().replace('/', File.separatorChar);
            int pos = 0;

            while((pos = filename.indexOf(37, pos)) >= 0) {
                if(pos + 2 < filename.length()) {
                    String hexStr = filename.substring(pos + 1, pos + 3);
                    char ch = (char)Integer.parseInt(hexStr, 16);
                    filename = filename.substring(0, pos) + ch + filename.substring(pos + 3);
                }
            }

            return new File(filename);
        } else {
            return null;
        }
    }

    public static String getBasePath(URL url) {
        if(url == null) {
            return null;
        } else {
            String s = url.toString();
            if(s.startsWith("file:") && !s.startsWith("file://")) {
                s = "file://" + s.substring("file:".length());
            }

            return !s.endsWith("/") && !StringUtils.isEmpty(url.getPath())?s.substring(0, s.lastIndexOf("/") + 1):s;
        }
    }

    public static String getBasePath(String path) {
        try {
            URL url = getURL((String)null, path);
            return getBasePath(url);
        } catch (Exception var3) {
            return null;
        }
    }

    public static String getFileName(URL url) {
        if(url == null) {
            return null;
        } else {
            String path = url.getPath();
            return !path.endsWith("/") && !StringUtils.isEmpty(path)?path.substring(path.lastIndexOf("/") + 1):null;
        }
    }

    public static String getFileName(String path) {
        try {
            URL url = getURL((String)null, path);
            return getFileName(url);
        } catch (Exception var3) {
            return null;
        }
    }

    public static URL toURL(File file) throws MalformedURLException {
        return file.toURI().toURL();
    }

    public static URL locate(String base, String name) {
        if(logger.isDebugEnabled()) {
            logger.debug("定位配置文件: 基础路径  [{}],文件名[{}]", new Object[]{base, name});
        }

        if(name == null) {
            return null;
        } else {
            URL url = locateFromURL(base, name);
            File e;
            if(url == null) {
                e = new File(name);
                if(e.isAbsolute() && e.exists()) {
                    try {
                        url = toURL(e);
                        logger.debug("通过绝对路径加载配置： " + name);
                    } catch (MalformedURLException var7) {
                        logger.warn("无法获取文件路径", var7);
                    }
                }
            }

            if(url == null) {
                try {
                    e = constructFile(base, name);
                    if(e != null && e.exists()) {
                        url = toURL(e);
                    }

                    if(url != null) {
                        logger.debug("从{}加载配置 ", e);
                    }
                } catch (MalformedURLException var6) {
                    logger.warn("无法通过文件获取URL", var6);
                }
            }

            if(url == null) {
                try {
                    e = constructFile(System.getProperty("user.home"), name);
                    if(e != null && e.exists()) {
                        url = toURL(e);
                    }

                    if(url != null) {
                        logger.debug("Loading configuration from the home path " + e);
                    }
                } catch (MalformedURLException var5) {
                    logger.warn("Could not obtain URL from file", var5);
                }
            }

            if(url == null) {
                url = locateFromClasspath(name);
            }

            return url;
        }
    }

    public static URL locateFromClasspath(String resourceName) {
        URL url = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if(loader != null) {
            url = loader.getResource(resourceName);
            if(url != null) {
                logger.debug("Locate file from the context classpath (" + resourceName + ")");
            }
        }

        if(url == null) {
            url = ClassLoader.getSystemResource(resourceName);
            if(url != null) {
                logger.debug("Locate file from the system classpath (" + resourceName + ")");
            }
        }

        return url;
    }

    public static File locateAbsolutePathFromClasspath(String resourceName) {
        URL url = locateFromClasspath(resourceName);
        return fileFromURL(url);
    }

    public static File constructFile(String basePath, String fileName) {
        File absolute = null;
        if(fileName != null) {
            absolute = new File(fileName);
        }

        File file;
        if(StringUtils.isEmpty(basePath) || absolute != null && absolute.isAbsolute()) {
            file = new File(fileName);
        } else {
            StringBuilder fName = new StringBuilder();
            fName.append(basePath);
            if(!basePath.endsWith(File.separator)) {
                fName.append(File.separator);
            }

            if(fileName.startsWith("." + File.separator)) {
                fName.append(fileName.substring(2));
            } else {
                fName.append(fileName);
            }

            file = new File(fName.toString());
        }

        return file;
    }

    public static void createPath(File file) {
        if(file != null && !file.exists()) {
            File parent = file.getParentFile();
            if(parent != null && !parent.exists()) {
                parent.mkdirs();
            }
        }

    }

    public static URL getURL(String basePath, String fileName) throws MalformedURLException {
        File f = new File(fileName);
        if(f.isAbsolute()) {
            return toURL(f);
        } else {
            try {
                if(basePath == null) {
                    return new URL(fileName);
                } else {
                    URL uex = new URL(basePath);
                    return new URL(uex, fileName);
                }
            } catch (MalformedURLException var4) {
                return toURL(constructFile(basePath, fileName));
            }
        }
    }

    public static URL locateFromURL(String basePath, String fileName) {
        try {
            if(basePath == null) {
                return new URL(fileName);
            } else {
                URL baseURL = new URL(basePath);
                URL e = new URL(baseURL, fileName);
                InputStream in = null;

                try {
                    in = e.openStream();
                } finally {
                    if(in != null) {
                        in.close();
                    }

                }

                return e;
            }
        } catch (IOException var9) {
            if(logger.isDebugEnabled()) {
                logger.debug("Could not locate file " + fileName + " at " + basePath + ": " + var9.getMessage());
            }

            return null;
        }
    }

    public static String readFile(String fileName) {
        return readFile(fileName, "UTF-8");
    }

    public static String readFile(String fileName, String charset) {
        StringBuffer sb = new StringBuffer();
        File file = new File(fileName);

        try {
            InputStreamReader e = new InputStreamReader(new FileInputStream(file), charset);
            BufferedReader bufferedReader = new BufferedReader(e);
            String lineTxt = null;

            while((lineTxt = bufferedReader.readLine()) != null) {
                sb.append(lineTxt + System.getProperty("line.separator"));
            }

            e.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return sb.toString();
    }

    public static List<String> readFile2List(String fileName, String charset) {
        ArrayList list = new ArrayList();
        File file = locateAbsolutePathFromClasspath(fileName);

        try {
            InputStreamReader e = new InputStreamReader(new FileInputStream(file), charset);
            BufferedReader bufferedReader = new BufferedReader(e);
            String lineTxt = null;

            while((lineTxt = bufferedReader.readLine()) != null) {
                if(StringUtils.isNotBlank(lineTxt)) {
                    list.add(lineTxt.trim());
                }
            }

            e.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return list;
    }

    public static void writeFile(File destFile, String content, boolean append) throws IOException {
        writeFile(destFile, content, "utf-8", append);
    }

    public static void writeFile(File destFile, String content, String chatset) throws IOException {
        writeFile(destFile, content, chatset, false);
    }

    public static void writeFile(File destFile, String content) throws IOException {
        writeFile(destFile, content, "utf-8");
    }

    public static void writeFile(File destFile, String content, String chatset, boolean append) throws IOException {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFile, append), chatset));
            writer.write(content);
            writer.close();
        } catch (IOException var6) {
            throw new IOException(var6);
        }
    }

    public static boolean deleteFile(String path) {
        return deleteFile(new File(path));
    }

    public static boolean deleteFile(File file) {
        boolean result = false;
        if(file.exists()) {
            result = file.delete();
        }

        return result;
    }

    public static void download(String remotePath, String localPath) {
        URL url = null;

        try {
            url = new URL(remotePath);
            URLConnection e = url.openConnection();
            InputStream inStream = e.getInputStream();
            FileOutputStream fos = new FileOutputStream(localPath);
            byte[] buffer = new byte[1024];
            boolean byteread = false;

            int byteread1;
            while((byteread1 = inStream.read(buffer)) != -1) {
                fos.write(buffer, 0, byteread1);
            }

            fos.close();
        } catch (FileNotFoundException var8) {
            var8.printStackTrace();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

    }

    public static void copy(String fromFile, String toFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(fromFile);
        FileChannel fromChannel = inputStream.getChannel();
        FileOutputStream outputStream = new FileOutputStream(toFile);
        FileChannel toChannel = outputStream.getChannel();
        toChannel.transferFrom(fromChannel, 0L, fromChannel.size());
        toChannel.force(true);
        inputStream.close();
        fromChannel.close();
        outputStream.close();
        toChannel.close();
    }

    public static void copyByIO(String fromFile, String toFile) throws IOException {
        File inputFile = new File(fromFile);
        File outputFile = new File(toFile);
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        byte[] bytes = new byte[1024];

        int c;
        while((c = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, c);
        }

        inputStream.close();
        outputStream.close();
    }
}
