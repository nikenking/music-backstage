package com.zhengchuang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhengchuang.music.domain.Singer;
import com.zhengchuang.music.service.SingerService;
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
import java.util.List;

@RequestMapping("/singer")
@RestController
public class SingerController {
    @Autowired
    SingerService service;

    /**
     * 添加歌手
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest req) {
        service.resetIndex();//重置自增主键
        JSONObject jsonObject = new JSONObject();
        String name = req.getParameter("name").trim();
        String sex = req.getParameter("sex").trim();
        String pic = req.getParameter("pic").trim();
        String birth = req.getParameter("birth").trim();
        String location = req.getParameter("location").trim();
        String introduction = req.getParameter("introduction").trim();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = format.parse(birth);//将传过来的日期格式转成数据库的格式
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setName(name);
        singer.setPic(pic);
        singer.setSex(new Byte(sex));
        singer.setBirth(birthDate);
        singer.setIntroduction(introduction);
        singer.setLocation(location);
        boolean insert = service.insert(singer);
        if (insert) {
            jsonObject.put(Const.CODE, 1);
            jsonObject.put(Const.MSG, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE, 0);
        jsonObject.put(Const.NAME, "添加失败");
        return jsonObject;
    }

    /**
     * 修改歌手
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String name = req.getParameter("name").trim();
        String sex = req.getParameter("sex").trim();
        //由于图片有专门的的方法，所以update专注于修改信息业务
        String birth = req.getParameter("birth").trim();
        String location = req.getParameter("location").trim();
        String introduction = req.getParameter("introduction").trim();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = format.parse(birth);//将传过来的日期格式转成数据库的格式
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(birthDate);
        singer.setIntroduction(introduction);
        singer.setLocation(location);
        boolean insert = service.update(singer);
        if (insert) {
            jsonObject.put(Const.CODE, 1);
            jsonObject.put(Const.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE, 0);
        jsonObject.put(Const.NAME, "修改失败");
        return jsonObject;
    }

    /**
     * 删除歌手
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        boolean flag = service.delete(Integer.valueOf(id));
        if (flag) {
            jsonObject.put(Const.CODE, 1);
            jsonObject.put(Const.MSG, "修改成功");
        } else {
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.NAME, "修改失败");
        }
        return jsonObject;
    }

    /**
     * 查询歌手
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        return service.selectByPrimaryKey(Integer.valueOf(id));
    }

    /**
     * 查询所有歌手
     */
    @RequestMapping(value = "/allSinger", method = RequestMethod.GET)
    public Object allSinger(HttpServletRequest req) {
        return service.allSinger();
    }

    /**
     * 根据歌手名字查询
     */
    @RequestMapping(value = "/singerOfName", method = RequestMethod.GET)
    public Object singerOfName(HttpServletRequest req) {
        String name = req.getParameter("name");
        return service.singerOfName(name);
    }

    /**
     * 根据性别查询
     */
    @RequestMapping(value = "/singerOfSex", method = RequestMethod.GET)
    public Object singerOfSex(HttpServletRequest req) {
        String sex = req.getParameter("sex");
        return service.singerOfSex(Integer.valueOf(sex));
    }

    /**
     * 更新歌手图片
     */
    @RequestMapping(value = "/updateSingerPic", method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "文件不能为空");
            return jsonObject;
        }
        String fileName = DateUtil.getCurrentTime(false) + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"//file.separator获得系统分隔符
                + System.getProperty("file.separator") + "singerPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            boolean mkdir = file1.mkdir();
        }
        //实际的文件地址(根目录/img/singerPic/时间戳sing.jpg)
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址(img/singerPic/时间戳sing.jpg)
        String storeAvatorPath = "img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean update = service.update(singer);
            if (update) {
                jsonObject.put(Const.CODE, 1);
                jsonObject.put(Const.MSG, "文件上传成功");
                jsonObject.put("pic", storeAvatorPath);//返回该图片的相对路径
                return jsonObject;
            }
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "图片更新失败?");
            System.out.println(singer);
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "文件上传失败" + e.getMessage());
            return jsonObject;
        }
    }
}
