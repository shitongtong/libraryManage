package com.stt;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 同时支持FPT和SFTP
 */
public class FtpClientUtils {

    /**
     * Description: 从FTP服务器下载文件(ftp)
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param localPath  下载后保存到本地的路径
     * @return
     */
    public static boolean downloadFileFromFtp(String url, int port, String username, String password, String remotePath, String localPath) {
        boolean success = false;
        FTPClient ftp = new FTPClient();

        try {
            int reply;
//            ftp.connect(url, port);
            ftp.connect(url);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                File localFile = new File(localPath + "/" + ff.getName());
                OutputStream is = new FileOutputStream(localFile);
                ftp.retrieveFile(ff.getName(), is);
                is.close();
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    public static boolean downloadFileFromSFtp(String url, int port, String username, String password, String remotePath, String fileName, String localPath) {
        String path = remotePath;
        String localBaseDir = localPath;
        boolean flag = false;
        ChannelSftp sftp = sftpConnect(url, port, username, password);
        try {
            sftp.cd(path);
            File file = new File(localBaseDir);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                sftp.stat(path + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return flag;
            }
            sftp.get(fileName, localBaseDir);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeChannel(sftp, sftp.getSession());
            } catch (JSchException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 连接sftp服务器
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return sftp : sftp连接通道
     */
    public static ChannelSftp sftpConnect(String host, int port, String username, String password) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);

            if (password != null) {
                sshSession.setPassword(password);// 设置密码
            }
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);// 为Session对象设置properties
//            sshSession.setTimeout(timeout); // 设置timeout时间
            sshSession.connect(30000);// 通过Session建立链接

            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sftp;
    }

    public static void closeChannel(ChannelSftp sftp, Session sshSession) {
        if (sftp != null) {
            try {
                sftp.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (sshSession != null) {
            try {
                sshSession.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String ftpUrl = "172.25.21.11";
        int port = 22;
        String username = "aiuap";
        String password = "NMDB4@VK";
        String remotePath = "/home/linux";
        String fileName = "autoConfigLinuxNetwork.sh";
        String localPath = "D:/ftpDownloadDir";
//        downloadFileFromFtp(ftpUrl, port, username, password, remotePath, localPath);
        downloadFileFromSFtp(ftpUrl, port, username, password, remotePath, fileName, localPath);
    }
}
