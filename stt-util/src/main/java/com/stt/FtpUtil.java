//package com.stt;
//
//import com.jcraft.jsch.Channel;
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.logging.LogRecord;
//
///**
// * 同时支持FPT和SFTP
// *
// * Created by shitt7 on 2018/6/19.
// */
//public class FtpUtil {
//    /**
//     * ssh ftp上传文件
//     * @param ftpVo : ftp配置信息
//     * @param file : 上传到ftp的文件
//     */
//    public static boolean upload(FtpVo ftpVo, File file) throws ControllerException{
//        if(Constant.NORMAL_FTP_TYPE.equals(ftpVo.getFtpType())){ //当协议类型为普通ftp时
//            return uploadNew(ftpVo, file);
//        }
//        boolean flag = false;
//        ChannelSftp sftp = sftpConnect(ftpVo.getIpAddr(), ftpVo.getPort(), ftpVo.getUserName(), ftpVo.getPwd());
//        try {
//            sftp.cd(ftpVo.getPath());
//            sftp.put(new FileInputStream(file), file.getName());
//            flag = true;
//        } catch (Exception e) {
//            LogRecord.recode(FtpUtil.class, e.getMessage());
//        } finally {
//            try {
//                closeChannel(sftp, sftp.getSession());
//            } catch (JSchException e) {
//                LogRecord.recode(FtpUtil.class, e.getMessage());
//            }
//        }
//        return flag;
//    }
//
//    /**
//     * ftp上传文件
//     * @param file
//     * @throws Exception
//     */
//    public static boolean uploadNew(FtpVo ftpVo, File file) throws ControllerException {
//
//        boolean flag = false;
////        FileInputStream input = null;
//        BufferedInputStream bis = null;
//        FTPClient ftp = new FTPClient();
//        try {
//            ftp.setControlEncoding("UTF-8");
//            ftp.enterLocalPassiveMode();
//
//            if (null == ftpVo.getPort()) {
//                ftp.connect(ftpVo.getIpAddr(),Integer.parseInt(PropertyConfig.getInstance().get(Constant.PORT)));
//            } else {
//                ftp.connect(ftpVo.getIpAddr(),ftpVo.getPort());
//            }
//            if (!ftp.login(ftpVo.getUserName(), ftpVo.getPwd())) {
//                ftp.logout();
//                return flag;
//            }
//            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//            LogRecord.recode(FtpUtil.class, "登录ftp成功，上传文件.");
//            boolean changedir = ftp.changeWorkingDirectory(ftpVo.getPath());
//            if (changedir) {
//                if (file.isDirectory()) {
//                    ftp.makeDirectory(file.getName());
//                    ftp.changeWorkingDirectory(file.getName());
//                    String[] files=file.list();
//                    for(String fstr : files){
//                        File file1=new File(file.getPath()+"/"+fstr);
//                        if (file1.isDirectory()) {
//                            uploadNew(ftpVo, file1);
//                            ftp.changeToParentDirectory();
//                        }else{
//                            File file2=new File(file.getPath()+"/"+fstr);
//                            bis = new BufferedInputStream(new FileInputStream(file2));
//                            flag = ftp.storeFile(new String(file2.getName().getBytes("UTF-8"), "iso-8859-1"),bis);
//                        }
//                    }
//                } else {
////                    File file2=new File(file.getPath());
//                    bis = new BufferedInputStream(new FileInputStream(file));
//                    flag = ftp.storeFile(new String(file.getName().getBytes("UTF-8"), "iso-8859-1"), bis);
//                    bis.close();
//                }
//            } else {
//                //转换目录失败则创建目录再进行转目录操作
//                if (!StringUtils.isEmpty(ftpVo.getPath())) {
//                    String[] paths = ftpVo.getPath().split("/");
//                    StringBuilder pathStr = new StringBuilder();
//                    for (String path : paths) {
//                        if (!StringUtils.isEmpty(path)) {
//                            pathStr.append("/" + path);
//                            boolean mkd = ftp.makeDirectory(pathStr.toString());
//                            if (mkd) {
//                                ftp.changeWorkingDirectory(pathStr.toString());
//                            }
//                        }
//                    }
//                    changedir = ftp.changeWorkingDirectory(ftpVo.getPath());
//                    if (changedir) {
//                        if (file.isDirectory()) {
//                            ftp.makeDirectory(file.getName());
//                            ftp.changeWorkingDirectory(file.getName());
//                            String[] files=file.list();
//                            for(String fstr : files){
//                                File file1=new File(file.getPath()+"/"+fstr);
//                                if (file1.isDirectory()) {
//                                    uploadNew(ftpVo, file1);
//                                    ftp.changeToParentDirectory();
//                                }else{
//                                    File file2=new File(file.getPath()+"/"+fstr);
//                                    bis = new BufferedInputStream(new FileInputStream(file2));
//                                    flag = ftp.storeFile(new String(file2.getName().getBytes("UTF-8"), "iso-8859-1"),bis);
//                                }
//                            }
//                        } else {
//                            //                        File file2=new File(file.getPath());
//                            bis = new BufferedInputStream(new FileInputStream(file));
//                            flag = ftp.storeFile(new String(file.getName().getBytes("UTF-8"), "iso-8859-1"), bis);
//                            bis.close();
//                        }
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            throw new ControllerException("上传文件到ftp失败， 失败原因：" + ex.getMessage(), ex.getCause());
//        } finally {
//            if (null != bis) {
//                try {
//                    bis.close();
//                } catch (IOException e) {
//                    LogRecord.recode(FtpUtil.class, e.getMessage());
//                }
//            }
//            try {
//                ftp.logout();
//            } catch (IOException e) {
//                LogRecord.recode(FtpUtil.class, "退出ftp登录异常：" + e.getMessage());
//                flag = false;
//            }
//            try {
//                ftp.disconnect();
//            } catch (IOException e) {
//                LogRecord.recode(FtpUtil.class, "断开ftp连接异常：" + e.getMessage());
//                flag = false;
//            }
//        }
//        return flag;
//    }
//
//    /**
//     * sftp下载文件
//     * @param ftpVo : ftp配置内容
//     * @param fileName : 文件名
//     * @param localBaseDir ：本地目录
//     * @param remoteBaseDir ： ftp目录
//     */
//    public static boolean download(FtpVo ftpVo, String fileName, String localBaseDir) {
//        if(Constant.NORMAL_FTP_TYPE.equals(ftpVo.getFtpType())){ //当协议类型为普通ftp时
//            return startDownNew(ftpVo, fileName, localBaseDir);
//        }
//        String path = ftpVo.getPath();
//        boolean flag = false;
//        ChannelSftp sftp = sftpConnect(ftpVo.getIpAddr(), ftpVo.getPort(), ftpVo.getUserName(), ftpVo.getPwd());
//        try {
//            sftp.cd(path);
//            File file = new File(localBaseDir);
//            if(!file.exists()){
//                file.mkdirs();
//            }
//            try {
//                sftp.stat(path + "/" + fileName);
//            } catch (Exception e) {
//                LogRecord.recode(FtpUtil.class, path + "/" + fileName + " not found");
//                return flag;
//            }
//            sftp.get(fileName, localBaseDir);
//            flag = true;
//        } catch (Exception e) {
//            LogRecord.recode(FtpUtil.class, e.getMessage());
//        } finally {
//            try {
//                closeChannel(sftp, sftp.getSession());
//            } catch (JSchException e) {
//                LogRecord.recode(FtpUtil.class, e.getMessage());
//            }
//        }
//        return flag;
//    }
//
//
//
//    /**
//     * 下载链接配置
//     * @param f
//     * @param localBaseDir 本地目录
//     * @param remoteBaseDir 远程目录
//     * @throws Exception
//     */
//    public static boolean startDownNew(FtpVo f, String fileName, String localBaseDir) throws ControllerException {
//        boolean flag = false;
//        String path = f.getPath();
//        FTPClient ftp = new FTPClient();
//        try {
//            ftp.setControlEncoding("UTF-8");
//            ftp.enterLocalPassiveMode();
//
//            if (null == f.getPort()) {
//                ftp.connect(f.getIpAddr(),Integer.parseInt(PropertyConfig.getInstance().get(Constant.PORT)));
//            } else {
//                ftp.connect(f.getIpAddr(),f.getPort());
//            }
//            if (!ftp.login(f.getUserName(), f.getPwd())) {
//                ftp.logout();
//                return flag;
//            }
//            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//            LogRecord.recode(FtpUtil.class, "登录ftp成功，下载文件.");
//            FTPFile[] files = null;
//            boolean changedir = ftp.changeWorkingDirectory(path);
//            if (changedir) {
//                ftp.setControlEncoding("GBK");
//                files = ftp.listFiles(path + "/" + fileName);  //获取具体的文件， 注意此处有一个问题如果给的是一个目录时它还会获取当前目录。的信息， 照成死循环
//                for (int i = 0; i < files.length; i++) {
//                    try{
//                        downloadFileNew(ftp, files[i], localBaseDir, path);
//                    }catch(Exception e){
//                        LogRecord.recode(FtpUtil.class, e.getMessage());
//                        LogRecord.recode(FtpUtil.class, "<"+files[i].getName()+">下载失败");
//
//                    }
//                }
//                flag = true;
//            }
//        } catch (Exception ex) {
//            LogRecord.recode(FtpUtil.class, "下载ftp文件异常： " + ex.getMessage());
//        } finally {
//            try {
//                if (null != ftp) {
//                    ftp.logout();
//                }
//            } catch (IOException e) {
//                LogRecord.recode(FtpUtil.class, "退出ftp登录异常：" + e.getMessage());
//                flag = false;
//            }
//            try {
//                if (null != ftp) {
//                    ftp.disconnect();
//                }
//            } catch (IOException e) {
//                LogRecord.recode(FtpUtil.class, "断开ftp连接异常：" + e.getMessage());
//                flag = false;
//            }
//        }
//        return flag;
//    }
//
//
//    /**
//     *
//     * 下载FTP文件
//     * 当你需要下载FTP文件的时候，调用此方法
//     * 根据<b>获取的文件名，本地地址，远程地址</b>进行下载
//     *
//     * @param ftpFile
//     * @param relativeLocalPath
//     * @param relativeRemotePath
//     */
//    private  static void downloadFileNew(FTPClient ftp, FTPFile ftpFile, String relativeLocalPath,String relativeRemotePath) throws ControllerException {
//        try {
//            if (ftpFile.isFile()) {
//                if (ftpFile.getName().indexOf("?") == -1) {
//                    OutputStream outputStream = null;
//                    try {
//                        File locaFile = null;
//                        File locaPath = new File(relativeLocalPath);
//                        if (!locaPath.exists()) {
//                            locaPath.mkdirs();
//                        }
//                        locaFile = new File(relativeLocalPath + ftpFile.getName());
//                        outputStream = new FileOutputStream(locaFile);
//                        ftp.retrieveFile(ftpFile.getName(), outputStream);
//                        outputStream.flush();
//                        outputStream.close();
//                    } catch (Exception e) {
//                        LogRecord.recode(FtpUtil.class, e.getMessage());
//                    } finally {
//                        try {
//                            if (outputStream != null){
//                                outputStream.close();
//                            }
//                        } catch (IOException e) {
//                            throw new ControllerException("输出文件流异常: " + e.getMessage(), e.getCause());
//                        }
//                    }
//                }
//            } else {
//                String newlocalRelatePath = relativeLocalPath + ftpFile.getName();
//                String newRemote = new String(relativeRemotePath+ ftpFile.getName().toString());
//                File fl = new File(newlocalRelatePath);
//                if (!fl.exists()) {
//                    fl.mkdirs();
//                }
//                try {
//                    newlocalRelatePath = newlocalRelatePath + '/';
//                    newRemote = newRemote + "/";
//                    String currentWorkDir = ftpFile.getName().toString();
//                    boolean changedir = ftp.changeWorkingDirectory(currentWorkDir);
//                    if (changedir) {
//                        FTPFile[] files = null;
//                        files = ftp.listFiles();
//                        for (int i = 0; i < files.length; i++) {
//                            downloadFileNew(ftp, files[i], newlocalRelatePath, newRemote);
//                        }
//                    }
//                    if (changedir){
//                        ftp.changeToParentDirectory();
//                    }
//                } catch (Exception e) {
//                    throw new ControllerException("更改ftp文件路径异常：" + e.getMessage(), e.getCause());
//                }
//            }
//        } catch (Exception ex) {
//            throw new ControllerException("下载ftp文件异常， 异常原因：" + ex.getMessage(), ex.getCause());
//        }
//    }
//
//
//
//
//    /**
//     * 连接sftp服务器
//     * @param host 主机
//     * @param port 端口
//     * @param username 用户名
//     * @param password 密码
//     * @return sftp : sftp连接通道
//     */
//    public static ChannelSftp sftpConnect(String host, int port, String username, String password) {
//        ChannelSftp sftp = null;
//        try {
//            JSch jsch = new JSch();
//            jsch.getSession(username, host, port);
//            Session sshSession = jsch.getSession(username, host, port);
//            LogRecord.recode(FtpUtil.class, "Session created.");
//
//            if (password != null) {
//                sshSession.setPassword(password);// 设置密码
//            }
//            Properties sshConfig = new Properties();
//            sshConfig.put("StrictHostKeyChecking", "no");
//            sshSession.setConfig(sshConfig);// 为Session对象设置properties
////            sshSession.setTimeout(timeout); // 设置timeout时间
//            sshSession.connect(30000);// 通过Session建立链接
//            LogRecord.recode(FtpUtil.class, "Session connected.");
//
//            LogRecord.recode(FtpUtil.class, "Opening Channel.");
//            Channel channel = sshSession.openChannel("sftp");
//            channel.connect();
//            sftp = (ChannelSftp) channel;
//            LogRecord.recode(FtpUtil.class, "Connected to " + host + ":" + port);
//        } catch (Exception e) {
//            LogRecord.recode(FtpUtil.class, "sftp连接失败");
//            LogRecord.recode(FtpUtil.class, e.getMessage());
//        }
//        return sftp;
//    }
//    /**
//     * 关闭传输连接通道
//     * @param sftp ： sftp传输连接通道
//     * @throws Exception
//     */
//    public static void closeChannel(ChannelSftp sftp, Session sshSession) {
//        if (sftp != null) {
//            try{
//                sftp.disconnect();
//                LogRecord.recode(FtpUtil.class, "sftp通道正常关闭");
//            } catch(Exception e){
//                LogRecord.recode(FtpUtil.class, "sftp通道关闭异常");
//            }
//        }
//        if (sshSession != null) {
//            try{
//                sshSession.disconnect();
//                LogRecord.recode(FtpUtil.class, "sshSession连接正常关闭");
//            } catch(Exception e){
//                LogRecord.recode(FtpUtil.class, "sshSession连接关闭异常");
//            }
//        }
//    }
//
//
//    /**
//     * 初始化ftp工具参数
//     * @param ftpPath
//     * @return
//     * @throws Exception
//     */
//    public static FtpVo initFtpParams(String ftpPath) throws Exception {
//
//        FtpVo ftpVo = new FtpVo();
//        ftpVo.setIpAddr(PropertyConfig.getInstance().get(Constant.IP_ADDR));
//        ftpVo.setPort(Integer.parseInt(PropertyConfig.getInstance().get(Constant.PORT)));
//        ftpVo.setUserName(PropertyConfig.getInstance().get(Constant.USER_NAME));
//        ftpVo.setPwd(PropertyConfig.getInstance().get(Constant.PWD));
//        ftpVo.setFtpType(PropertyConfig.getInstance().get(Constant.FTP_TYPE));
//        ftpVo.setPath(ftpPath);
//        LogRecord.recode(FtpUtil.class, "初始化ftpVo信息");
//        return ftpVo;
//    }
//
//    /**
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception{
//        FtpVo f=new FtpVo();
//        f.setIpAddr("192.168.1.73");
//        f.setPort(23);
//        f.setUserName("test");
//        f.setPwd("test");
//        f.setPath("/upload");
//        File file = new File("e:/test.txt");
//        FtpUtil.upload(f, file);
//        LogRecord.recode(FtpUtil.class, "上传文件成功");
//        FtpUtil.download(f, "test.txt", "e:/down");
//
////            FtpUtil.connectFtp(f);
////            File file = new File("D:/ftpfile/ab.class");
////            FtpUtil.upload(file);//把文件上传在ftp上
////            FtpUtil.startDown(f, "ab.class", "d:/",  "/ftp");//下载ftp文件测试
////            System.out.println("ok");
////        File file = new File("D:/ftpfile/c/d/d/e");
////        if (file.exists()) {
////            file.delete();
////        } else {
////            file.mkdirs();
////        }
//    }
//}
