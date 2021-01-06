package com.zhengchuang.music;

import com.zhengchuang.music.controller.SingerController;
import com.zhengchuang.music.domain.Singer;
import com.zhengchuang.music.mapper.SingerMapperAnno;
import com.zhengchuang.music.service.SingerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MusicApplicationTests {
    @Autowired
    SingerMapperAnno singerMapperAnno;
    @Autowired
    SingerService service;
    @Test
    void selectByPrimaryKey() {
        Singer singer = service.selectByPrimaryKey(1);
        System.out.println(singer);
    }
    @Test
    void insert(){
        Singer singer = new Singer(null, "test", new Byte("1"), "xxx.jpg", new Date(),"China", "sayhello");
        int insert = singerMapperAnno.insert(singer);
        System.out.println(insert);
    }
    @Test
    void update(){
//        Singer singer = new Singer(null, "change", new Byte("1"), "xxx.jpg",new Date(),"China", "sayhello");
        Singer singer = new Singer();
        singer.setId(1);
        singer.setName("花花");
        int update = singerMapperAnno.update(singer);
        System.out.println(update);
    }
    @Test
    void selectAll(){
        List<Singer> singers = singerMapperAnno.allSinger();
        for (Singer singer : singers) {
            System.out.println(singer);
        }
    }
    @Test
    void delete(){
        int delete = singerMapperAnno.delete(2);
        System.out.println(delete);
    }
    @Test
    void selectByName(){
        List<Singer> singers = singerMapperAnno.singerOfName("d");
        for (Singer singer : singers) {
            System.out.println(singer);
        }
    }
    @Test
    void selectBySex(){
        List<Singer> singers = singerMapperAnno.singerOfSex(1);
        for (Singer singer : singers) {
            System.out.println(singer);
        }
    }

    @Test
    void userList(){
        List<String> list = Arrays.asList("魏子超",
                "许向南",
                "王怀",
                "刘欣程",
                "张敏",
                "唐伟",
                "钦程",
                "张羽铖",
                "谭钊全",
                "白宏禹",
                "林祥",
                "郎金刚",
                "尚含溪",
                "郭永明",
                "郭波",
                "陈林",
                "高斌",
                "李远芳",
                "郑创",
                "张永涛",
                "姚永晴",
                "潘雯",
                "彭然",
                "于敏亮",
                "吴凤岐",
                "李长茂",
                "陈亚",
                "官睿",
                "叶陈锋",
                "彭涛",
                "买春生",
                "张培杰",
                "雷职菱",
                "胡博",
                "秦超"
        );
        System.out.println("update student set weight = 0;");
        for (String s : list) {
            System.out.println("update student set weight = 1 where name = '"+s+"';");
        }
    }

}
