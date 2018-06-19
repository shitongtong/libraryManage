package com.stt.java.base;

import java.io.File;

/**
 * Created by shitt7 on 2018/6/7.
 */
public class DeleteSvnDir {
    public static void main(String[] args) {
        String baseDir = "D:\\workDir\\项目代码\\整理后代码\\portal_1";
        deleteSvnDir(baseDir);
    }

    public static void deleteSvnDir(String dirPath) {
        File dirFile = new File(dirPath);
        if (dirFile.exists() && dirFile.isDirectory()) {
            File[] files = dirFile.listFiles();
            for (File file : files) {
                if (".svn".equalsIgnoreCase(file.getName())) {
                    System.out.println(file.getPath());
                    deleteAllFile(file.getPath());
                } else {
                    deleteSvnDir(file.getPath());
                }
            }
        }
    }

    private static void deleteAllFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isFile()) {
                System.out.println(path);
                file.delete();
            } else {
                File[] list = file.listFiles();
                for (File file1 : list) {
                    deleteAllFile(file1.getPath());
                }
            }
        }
    }
}
