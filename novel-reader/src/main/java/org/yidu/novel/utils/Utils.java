//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;

public class Utils {
    protected static Log logger = LogFactory.getLog(Utils.class);

    public Utils() {
    }

    public static String convert2MD5(String input) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] e = input.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(e);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return (new String(str)).toLowerCase();
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public static String getContext(TChapter chapter, boolean escape) {
        StringBuilder sb = new StringBuilder();
        String path = getTextFilePathByChapterno(chapter.getArticleno().intValue(), chapter.getChapterno());
        File file = new File(path);

        try {
            if(file.isFile() && file.exists()) {
                InputStreamReader e = new InputStreamReader(new FileInputStream(file), YiDuConstants.yiduConf.getString("txtEncoding"));
                BufferedReader bufferedReader = new BufferedReader(e);
                String lineTxt = null;

                while((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt);
                    if(escape) {
                        sb.append("<br/>");
                    } else {
                        sb.append("\n");
                    }
                }

                bufferedReader.close();
                e.close();
                if(escape) {
                    return sb.toString().replaceAll("\\s", "&nbsp;");
                }

                return sb.toString();
            }

            logger.info("can not find chapter. articleno:" + chapter.getArticleno() + " chapterno:" + chapter.getChapterno());
        } catch (Exception var8) {
            logger.error(var8.getMessage(), var8);
        }

        return null;
    }

    public static String getTextFilePathByChapterno(int articleno, int chapterno) {
        String path = YiDuConstants.yiduConf.getString("filePath");
        path = path + "/" + articleno / 1000 + "/" + articleno + "/" + chapterno + ".txt";
        return path;
    }

    public static String getTextDirectoryPathByArticleno(int articleno) {
        String path = YiDuConstants.yiduConf.getString("filePath");
        path = path + "/" + articleno / 1000 + "/" + articleno + "/";
        return path;
    }

    public static String getImgDirectoryPathByArticleno(int articleno) {
        String path = YiDuConstants.yiduConf.getString("relativeIamgePath");
        path = ServletActionContext.getServletContext().getRealPath("/") + "/" + path + "/" + articleno / 1000 + "/" + articleno + "/";
        return path;
    }

    public static void saveContext(int articleno, int chapterno, String content) throws IOException {
        String path = getTextFilePathByChapterno(articleno, chapterno);
        File file = new File(path);
        File parentPath = file.getParentFile();
        if(!parentPath.exists()) {
            parentPath.mkdirs();
        }

        try {
            OutputStreamWriter e = new OutputStreamWriter(new FileOutputStream(file), YiDuConstants.yiduConf.getString("txtEncoding"));
            e.write(content);
            e.flush();
            e.close();
        } catch (IOException var7) {
            logger.error(var7.getMessage(), var7);
        }

    }

    public static String getContentFromUri(String uri) {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpGet e = new HttpGet(uri);
            ResponseHandler responseHandler = new ResponseHandler() {
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if(status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null?EntityUtils.toString(entity):null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            String var5 = (String)httpclient.execute(e, responseHandler);
            return var5;
        } catch (Exception var13) {
            logger.error(var13);
        } finally {
            try {
                httpclient.close();
            } catch (IOException var12) {
                logger.error(var12);
            }

        }

        return null;
    }

    public static boolean deleteDirectory(String sPath) {
        if(!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }

        File dirFile = new File(sPath);
        if(dirFile.exists() && dirFile.isDirectory()) {
            boolean flag = true;
            File[] files = dirFile.listFiles();

            for(int i = 0; i < files.length; ++i) {
                if(files[i].isFile()) {
                    flag = deleteFile(files[i].getAbsolutePath());
                    if(!flag) {
                        break;
                    }
                } else {
                    flag = deleteDirectory(files[i].getAbsolutePath());
                    if(!flag) {
                        break;
                    }
                }
            }

            return !flag?false:dirFile.delete();
        } else {
            return false;
        }
    }

    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if(file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }

        return flag;
    }

    public static void saveArticlespic(int articleno, File file, String fileName) throws Exception {
        String path = YiDuConstants.yiduConf.getString("relativeIamgePath");
        path = ServletActionContext.getServletContext().getRealPath("/") + "/" + path + "/" + articleno / 1000 + "/" + articleno + "/" + articleno + "s." + StringUtils.substringAfterLast(fileName, ".");
        File savefile = new File(path);
        if(!savefile.getParentFile().exists()) {
            savefile.getParentFile().mkdirs();
        }

        FileUtils.copyFile(file, savefile);
    }

    public static String getArticlePicPath(int articleno) {
        String path = YiDuConstants.yiduConf.getString("relativeIamgePath");
        path = ServletActionContext.getServletContext().getRealPath("/") + "/" + path + "/" + articleno / 1000 + "/" + articleno + "/";
        return path;
    }
}
