package com.zhengchuang.music;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ConsumerTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("id",
                "username",
                "password",
                "sex",
                "phoneNum",
                "email",
                "birth",
                "introduction",
                "location",
                "avator",
                "createTime",
                "updateTime"
        );
        for (String s : list) {
            System.out.println(""+s+": row."+s+",");
        }
    }
}
