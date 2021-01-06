package com.zhengchuang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhengchuang.music.domain.Singer;
import com.zhengchuang.music.domain.Song;
import com.zhengchuang.music.service.SingerService;
import com.zhengchuang.music.service.SongService;
import com.zhengchuang.music.utils.Const;
import com.zhengchuang.music.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping( "/song")
@RestController
public class SongController {
    @Autowired
    SongService service;

    /**
     * 添加歌曲*/
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest req,@RequestParam("file") MultipartFile mpFile){
        service.resetIndex();//重置自增主键
        JSONObject jsonObject = new JSONObject();
        String singerId = req.getParameter("singerId").trim();//所属歌手id
        String name = req.getParameter("name").trim();//歌名
        String introduction = req.getParameter("introduction").trim();//简介
        String pic = "img/songPic/default.jpg";
        String lyric = req.getParameter("lyric").trim(); //歌词
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date creatTime = new Date();
        Date updateTime = new Date();
        try {
            creatTime = format.parse(format.format(new Date()));
            updateTime = creatTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //上传的mp3文件
        if (mpFile.isEmpty()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"歌曲不能为空");//文件为空
            return jsonObject;
        }
        //文件名=时间戳+原来的文件名
        String fileName = DateUtil.getCurrentTime(false) + mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";//file.separator获得系统分隔符
        File file1 = new File(filePath);
        if (!file1.exists()){
            boolean mkdir = file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的mp3相对文件地址
        String storeUrlPath = "/song/" + fileName;
        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.valueOf(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            song.setCreateTime(creatTime);
            song.setUpdateTime(updateTime);
            boolean insert = service.insert(song);
            if (insert){
                jsonObject.put(Const.CODE,1);
                jsonObject.put(Const.MSG,"歌曲上传成功");
                jsonObject.put("avator",storeUrlPath);
                return jsonObject;
            }
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"歌曲添加失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"文件上传失败"+e.getMessage());
            return jsonObject;
        }
    }

    /**
     * 查询所有歌单*/
    @RequestMapping(value = "/allSong",method = RequestMethod.GET)
    public Object allSongList(HttpServletRequest req){
        return service.allSong();
    }

    /**
     * 删除歌曲*/
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSong(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        boolean result = service.delete(Integer.valueOf(id));
        if (result){
            jsonObject.put(Const.CODE, true);
            jsonObject.put(Const.MSG,"删除成功");
        }else{
            jsonObject.put(Const.CODE, false);
            jsonObject.put(Const.MSG,"删除失败");
        }
        return jsonObject;
    }

    /**
     * 歌单界面因为只有对应关系，没有具体数据，所以要再查一次*/
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object songOfId(HttpServletRequest req){
        String songId = req.getParameter("songId");
        return service.selectByPrimaryKey(Integer.valueOf(songId));
    }

    /**
     * 歌单添加没法直接输入id，所以让服务器查询SingerName-songName形式的歌曲，然后添加他的id到歌单关系中*/
    @RequestMapping(value = "/songOfSongName",method = RequestMethod.GET)
    public Object songOfName(HttpServletRequest req){
        String songName = req.getParameter("songName");
        return service.songOfName(songName);
    }

    /**
     * 根据歌手id查询歌曲*/
    @RequestMapping(value = "/singer/detail",method = RequestMethod.GET)
    public Object songOfSingerId(HttpServletRequest req){
        String singerId = req.getParameter("singerId");
        return service.songOfSingerId(Integer.parseInt(singerId));
    }

    /**
     * 修改歌曲信息*/
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSong(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String lyric = req.getParameter("lyric").trim();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date updateTime = new Date();
        try {
            updateTime = format.parse(format.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Song target = new Song();
        target.setId(Integer.valueOf(id));
        target.setName(name);
        target.setIntroduction(introduction);
        target.setLyric(lyric);
        target.setUpdateTime(updateTime);
        boolean flag = service.update(target);
        if (flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.NAME,"修改失败");
        return jsonObject;
    }

    /**
     * 更新歌曲图片*/
    @RequestMapping(value = "/updateSongPic",method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"文件不能为空");
            return jsonObject;
        }
        String fileName = DateUtil.getCurrentTime(false) + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "songPic";
        File file1 = new File(filePath);
        if (!file1.exists()){
            boolean mkdir = file1.mkdir();
        }
        //实际的文件地址(根目录/img/xxxPic/时间戳xxx.jpg)
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址(img/xxxPic/时间戳xxx.jpg)
        String storeAvatorPath = "img/songPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Song target = new Song();
            target.setId(id);
            target.setPic(storeAvatorPath);
            boolean flag = service.update(target);
            if (flag){
                jsonObject.put(Const.CODE,1);
                jsonObject.put(Const.MSG,"图片更新成功");
                jsonObject.put("pic",storeAvatorPath);//返回该图片的相对路径
                return jsonObject;
            }
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"图片更新失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"图片更新失败"+e.getMessage());
            return jsonObject;
        }
    }

    /**
     * 更新歌曲*/
    @RequestMapping(value = "/updateSongUrl",method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file")MultipartFile mp3File,@RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if (mp3File.isEmpty()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"文件不能为空");
            return jsonObject;
        }
        String fileName = DateUtil.getCurrentTime(false) + mp3File.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()){
            boolean mkdir = file1.mkdir();
        }
        //实际的文件地址(根目录/img/xxxPic/时间戳xxx.jpg)
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址(img/xxxPic/时间戳xxx.jpg)
        String storeSongPath = "song/" + fileName;
        try {
            mp3File.transferTo(dest);
            Song target = service.selectByPrimaryKey(id);//查出以前的所有数据
            boolean delete = service.delete(id);//删掉原有数据
            target.setUrl(storeSongPath);//配置新的mp3Url
            boolean flag = service.insert(target);//重新插入
            if (flag){
                jsonObject.put(Const.CODE,1);
                jsonObject.put(Const.MSG,"音乐更新成功");
                return jsonObject;
            }
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"音乐更新失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"音乐更新失败,error message:"+e.getMessage());
            return jsonObject;
        }
    }


}
