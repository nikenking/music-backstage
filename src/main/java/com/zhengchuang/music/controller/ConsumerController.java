package com.zhengchuang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhengchuang.music.domain.Consumer;
import com.zhengchuang.music.domain.Singer;
import com.zhengchuang.music.service.ConsumerService;
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

@RequestMapping("/consumer")
@RestController
public class ConsumerController {
    @Autowired
    ConsumerService service;

    /**
     * 添加用户
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addConsumer(HttpServletRequest req) {
        service.resetIndex();//重置自增主键
        JSONObject jsonObject = new JSONObject();
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String sex = req.getParameter("sex").trim();
        String phoneNum = req.getParameter("phoneNum").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birth").trim();
        String introduction = req.getParameter("introduction").trim();
        String location = req.getParameter("location").trim();
        String avator = req.getParameter("avator").trim();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        Date createDate = new Date();
        Date updateDate = new Date();
        try {
            birthDate = format.parse(birth);//将传过来的日期格式转成数据库的格式
            createDate = format.parse(format.format(new Date()));
            updateDate = format.parse(format.format(new Date()));
        } catch (ParseException e) {

            e.printStackTrace();
        }
        Consumer target = new Consumer();
        target.setUsername(username);
        target.setPassword(password);
        target.setSex(Byte.valueOf(sex));
        target.setPhoneNum(phoneNum);
        target.setEmail(email);
        target.setBirth(birthDate);
        target.setIntroduction(introduction);
        target.setLocation(location);
        target.setAvator(avator);
        target.setCreateTime(createDate);
        target.setUpdateTime(updateDate);

        boolean flag = service.insert(target);
        if (flag) {
            jsonObject.put(Const.CODE, 1);
            jsonObject.put(Const.MSG, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE, 0);
        jsonObject.put(Const.NAME, "添加失败");
        return jsonObject;
    }

    /**
     * 修改用户
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateConsumer(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String username = req.getParameter("username").trim();
        String sex = req.getParameter("sex").trim();
        String phoneNum = req.getParameter("phoneNum").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birth").trim();
        String introduction = req.getParameter("introduction").trim();
        String location = req.getParameter("location").trim();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        Date updateDate = new Date();
        try {
            birthDate = format.parse(birth);//将传过来的日期格式转成数据库的格式
            updateDate = format.parse(format.format(updateDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Consumer target = new Consumer();
        target.setId(Integer.valueOf(id));
        target.setUsername(username);
        target.setSex(Byte.valueOf(sex));
        target.setPhoneNum(phoneNum);
        target.setEmail(email);
        target.setBirth(birthDate);
        target.setIntroduction(introduction);
        target.setLocation(location);
        target.setUpdateTime(updateDate);
        boolean flag = service.update(target);
        if (flag) {
            jsonObject.put(Const.CODE, 1);
            jsonObject.put(Const.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE, 0);
        jsonObject.put(Const.NAME, "修改失败");
        return jsonObject;
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteConsumer(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        boolean flag = service.delete(Integer.valueOf(id));
        if (flag) {
            jsonObject.put(Const.CODE, 1);
            jsonObject.put(Const.MSG, "删除成功");
        } else {
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.NAME, "删除失败");
        }
        return jsonObject;
    }

    /**
     * 查询用户
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        return service.selectByPrimaryKey(Integer.valueOf(id));
    }

    /**
     * 查询所有用户
     */
    @RequestMapping(value = "/allConsumer", method = RequestMethod.GET)
    public Object allConsumer() {
        return service.allConsumer();
    }

    /**
     * 根据用户名字查询
     */
    @RequestMapping(value = "/consumerOfName", method = RequestMethod.GET)
    public Object consumerOfName(HttpServletRequest req) {
        String name = req.getParameter("name");
        return service.consumerOfName(name);
    }

    /**
     * 更新歌手图片
     */
    @RequestMapping(value = "/updateConsumerPic", method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "文件不能为空");
            return jsonObject;
        }
        String fileName = DateUtil.getCurrentTime(false) + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "avatorImages";//file.separator获得系统分隔符
        File file1 = new File(filePath);
        if (!file1.exists()) {
            boolean mkdir = file1.mkdir();
        }
        //实际的文件地址(根目录/img/singerPic/时间戳sing.jpg)
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址(img/singerPic/时间戳sing.jpg)
        String storeAvatorPath = "avatorImages/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer target = new Consumer();
            target.setId(id);
            target.setAvator(storeAvatorPath);
            boolean update = service.update(target);
            if (update) {
                jsonObject.put(Const.CODE, 1);
                jsonObject.put(Const.MSG, "头像更新成功");
                return jsonObject;
            }
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "头像更新失败?");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "文件上传异常，异常信息:{" + e.getMessage() + "}");
            return jsonObject;
        }
    }

    /**
     * 前端用户登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        if(username.equals("")){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password.equals("")){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"密码不能为空");
            return jsonObject;
        }

        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        boolean flag = service.verifyPassword(username,password);
        if(flag){   //验证成功
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"登录成功");
            jsonObject.put("userMsg",service.consumerOfName(username).get(0));
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"用户名或密码错误");
        return jsonObject;
    }

}
