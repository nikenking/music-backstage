package com.zhengchuang.music.utils;

import java.io.File;

public class FileUtil {
    public static boolean removeFile(String url){
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+url;
        File file1 = new File(filePath);
        return file1.delete();
    }
}
