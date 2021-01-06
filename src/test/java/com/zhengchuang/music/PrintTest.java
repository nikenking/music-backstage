package com.zhengchuang.music;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PrintTest {
    @Test
    public void test1(){
        /**
         * */
        List<String> list = Arrays.asList("id",
                "userId",
                "type",
                "songId",
                "songListId",
                "createTime"
        );
        for (String s : list) {
            System.out.println("\"<if test='"+s+"!=null'>#{"+s+"},</if>\" +");
        }
    }
}
