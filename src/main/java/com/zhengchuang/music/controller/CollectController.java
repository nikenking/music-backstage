package com.zhengchuang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhengchuang.music.domain.Collect;
import com.zhengchuang.music.service.CollectService;
import com.zhengchuang.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 收藏控制类
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService CollectService;

    /**
     * 添加收藏
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addCollect(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");           //用户id
        String type = request.getParameter("type");               //收藏类型（0歌曲1歌单）
        String songId = request.getParameter("songId");           //歌曲id
        if(songId==null||songId.equals("")){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"收藏歌曲为空");
            return jsonObject;
        }
        if(CollectService.existSongId(Integer.parseInt(userId),Integer.parseInt(songId))){
            jsonObject.put(Const.CODE,2);
            jsonObject.put(Const.MSG,"已收藏");
            return jsonObject;
        }
        //保存到收藏的对象中
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(new Byte(type));
        collect.setSongId(Integer.parseInt(songId));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date createTime = new Date();
        try {
            createTime = format.parse(format.format(createTime));//将传过来的日期格式转成数据库的格式
        } catch (ParseException e) {
            e.printStackTrace();
        }
        collect.setCreateTime(createTime);
        System.out.println(collect);
        boolean flag = CollectService.insert(collect);
        if(flag){   //保存成功
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"收藏失败");
        return jsonObject;
    }

    /**
     * 删除收藏
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteCollect(HttpServletRequest request){
        String userId = request.getParameter("userId");           //用户id
        String songId = request.getParameter("songId");           //歌曲id
        boolean flag = CollectService.deleteByUserIdSongId(Integer.parseInt(userId),Integer.parseInt(songId));
        return flag;
    }

    /**
     * 查询所有收藏
     */
    @RequestMapping(value = "/allCollect",method = RequestMethod.GET)
    public Object allCollect(HttpServletRequest request){
        return CollectService.allCollect();
    }

    /**
     * 查询某个用户的收藏列表
     */
    @RequestMapping(value = "/collectOfUserId",method = RequestMethod.GET)
    public Object collectOfUserId(HttpServletRequest request){
        String userId = request.getParameter("userId");          //用户id
        return CollectService.collectOfUserId(Integer.parseInt(userId));
    }

}






















