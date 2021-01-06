package com.zhengchuang.music;

import com.zhengchuang.music.controller.SongController;
import com.zhengchuang.music.domain.Song;
import com.zhengchuang.music.mapper.SongMapperAnno;
import com.zhengchuang.music.service.SongService;
import com.zhengchuang.music.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class SongTest {
    @Autowired
    SongService service;
    @Autowired
    SongMapperAnno mapper;

    @Test
    public void addSong() {
        Song song = new Song();
        song.setSingerId(2);
//        song.setName("不一样的烟火");
        song.setPic("img/songPic/default.jpg");
        song.setLyric("看着飞舞的尘埃 掉下来\n" +
                "没人发现它存在\n" +
                "多自由自在\n" +
                "可世界都爱热热闹闹\n" +
                "容不下 我百无聊赖\n" +
                "不应该 一个人 发呆\n" +
                "只有我 守着安静的沙漠\n" +
                "等待着花开\n" +
                "只有我 看着别人的快乐\n" +
                "竟然会感慨\n" +
                "就让我 听着天大的道理\n" +
                "不愿意明白\n" +
                "有什么 是应该 不应该\n" +
                "我的心里住着一个 苍老的小孩\n" +
                "如果世界听不明白 对影子表白\n" +
                "是不是只有我 还在问\n" +
                "为什么 明天更精彩\n" +
                "烟火里 找不到 童真的残骸\n" +
                "只有我 守着安静的沙漠\n" +
                "等待着花开\n" +
                "只有我 看着别人的快乐\n" +
                "竟然会感慨\n" +
                "就让我 听着天大的道理\n" +
                "不愿意明白\n" +
                "只有我 就是我 好奇怪\n" +
                "还在感慨\n" +
                "风阵阵吹过来 为何不回来\n" +
                "风一去不回来 悲不悲哀\n" +
                "麻木得那么快 应不应该\n" +
                "能不能慢下来\n" +
                "笑得开怀 哭得坦率\n" +
                "为何表情 要让这世界安排\n" +
                "我就是我 我只是我\n" +
                "只是一场烟火散落的尘埃\n" +
                "风阵阵吹过来\n" +
                "风一去不回来\n" +
                "能不能慢下来");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            song.setCreateTime(format.parse("2015-04-13"));
            song.setUpdateTime(format.parse(format.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        song.setIntroduction("《烟火里的尘埃》是华晨宇演唱的一首歌曲，由林夕作词，西楼谱曲，西楼、郑楠编曲，收录在华晨宇2014年发行专辑《卡西莫多的礼物》中。");
        song.setUrl("song/华晨宇《烟火里的尘埃》.mp3");
        service.insert(song);
    }

    @Test
    public void delSong() {

        service.delete(16);
    }

    @Test
    public void selectPrimaryKey() {
        Song song = service.selectByPrimaryKey(1);
        System.out.println(song);
    }

    @Test
    public void update() {
        Song song = new Song();
        song.setId(4);
        service.update(song);
    }

    @Test
    public void dynamicalSql() {
        Song song = new Song();
        song.setId(4);
        song.setName("烟花");
        System.out.println(mapper.updateTest(song));
    }

    @Test
    public void getCurrentTime(){
        String currentTime = DateUtil.getCurrentTime(true);
        System.out.println(currentTime);
        currentTime = DateUtil.getCurrentTime(false);
        System.out.println(currentTime);
    }

}
