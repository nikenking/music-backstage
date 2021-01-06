package com.zhengchuang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhengchuang.music.domain.ListSong;
import com.zhengchuang.music.service.ListSongService;
import com.zhengchuang.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping( "/listSong")
@RestController
public class ListSongController {
    @Autowired
    ListSongService service;
    /**
     * 给歌单添加歌曲
     * 1.调用getSongId，将歌手和歌名拼接
     * 2.调用songOfSongName()传入复合名从song表中查,返回歌曲id；没有就alert()
     * 3.调用addSong,加入刚查到的songId和当前页的songListId，执行添加操作
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addListSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        //获取前端传来的参数
        String songId = request.getParameter("songId").trim();  //歌曲id
        String songListId = request.getParameter("songListId").trim(); //歌单id
        ListSong target = new ListSong();
        target.setSongId(Integer.parseInt(songId));
        target.setSongListId(Integer.parseInt(songListId));
        boolean flag = service.insert(target);
        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"歌曲添加成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"歌曲添加失败");
        return jsonObject;
    }

    /**
     * 根据歌单id查询歌曲
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object detail(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        return service.listSongOfSongListId(Integer.parseInt(songListId));
    }
    /**
     * 删除歌单里的歌曲
     * 1.点击删除，调用handler将当前row的id赋值给idx
     * 2.点击确定，调用deleteRow=>调用delListSong;传入参数songId和songListId
     * 3.调用接口/listSong/delete(songId,songListID)
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String songId = request.getParameter("songId").trim();                 //歌曲id
        String songListId = request.getParameter("songListId").trim();        //歌单id
        boolean flag = service.deleteBySongIdAndSongListId(Integer.parseInt(songId), Integer.parseInt(songListId));
        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"歌曲删除成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"歌曲删除失败");
        return jsonObject;
    }


}
