package com.zhengchuang.music.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getCurrentTime(boolean random){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        StringBuffer time = new StringBuffer(format.format(System.currentTimeMillis()));
        if (random){
            String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            time.append("-");
            for (int i = 0; i < 10; i++) {
                int index = (int) (Math.random() * str.length());
                time.append(str.charAt(index));
            }
        }
        time.append("-");
        return String.valueOf(time);
    }

}
