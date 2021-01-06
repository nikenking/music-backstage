package com.zhengchuang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.xs.XSTerm;
import com.zhengchuang.music.domain.Song;
import com.zhengchuang.music.domain.SongList;
import com.zhengchuang.music.service.SongListService;
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

@RequestMapping( "/songList")
@RestController
public class SongListController {
    @Autowired
    SongListService service;

    /**
     * 添加歌曲*/
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest req){
        service.resetIndex();//重置自增主键
        JSONObject jsonObject = new JSONObject();
        String title = req.getParameter("title").trim();//歌单名
        String style = req.getParameter("style").trim();//风格
        String introduction = req.getParameter("introduction").trim();//简介
        String pic = req.getParameter("pic");
        SongList target = new SongList();
        target.setIntroduction(introduction);
        target.setTitle(title);
        target.setStyle(style);
        target.setPic(pic);
        if (service.insert(target)){
            jsonObject.put(Const.CODE, true);
            jsonObject.put(Const.MSG,"创建成功");
        }else{
            jsonObject.put(Const.CODE, false);
            jsonObject.put(Const.MSG,"创建失败");
        }
        return jsonObject;
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
     * 修改歌单信息*/
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSong(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        SongList target = new SongList();
        target.setId(Integer.valueOf(id));
        target.setIntroduction(introduction);
        target.setTitle(title);
        if (service.update(target)){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"修改成功");
        }else{
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.NAME,"修改失败");
        }
        return jsonObject;
    }

    /**
     * 更新歌单图片*/
    @RequestMapping(value = "/updateSongListPic",method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"文件不能为空");
            return jsonObject;
        }
        String fileName = DateUtil.getCurrentTime(false) + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "songListPic";
        File file1 = new File(filePath);
        if (!file1.exists()){
            boolean mkdir = file1.mkdir();
        }
        //实际的文件地址(根目录/img/xxxPic/时间戳xxx.jpg)
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址(img/xxxPic/时间戳xxx.jpg)
        String storeAvatorPath = "img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            SongList target = new SongList();
            target.setId(id);
            target.setPic(storeAvatorPath);
            boolean flag = service.update(target);
            if (flag){
                jsonObject.put(Const.CODE,1);
                jsonObject.put(Const.MSG,"图片更新成功");
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
     * 查询所有歌单*/
    @RequestMapping(value = "/allSongList",method = RequestMethod.GET)
    public Object allSongList(HttpServletRequest req){
        return service.allSongList();
    }
    /**
     * 根据标题精确查询歌单列表
     */
    @RequestMapping(value = "/songListOfTitle",method = RequestMethod.GET)
    public Object songListOfName(HttpServletRequest request){
        String title = request.getParameter("title").trim();          //歌单标题
        return service.songListOfTitle(title);
    }

    /**
     * 根据标题模糊查询歌单列表
     */
    @RequestMapping(value = "/likeTitle",method = RequestMethod.GET)
    public Object likeTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();          //歌单标题
        return service.likeTitle(title);
    }

    /**
     * 根据风格模糊查询歌单列表
     */
    @RequestMapping(value = "/likeStyle",method = RequestMethod.GET)
    public Object likeStyle(HttpServletRequest request){
        String style = request.getParameter("style").trim();          //歌单风格
        return service.likeTitle(style);
    }


}
