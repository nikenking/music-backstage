package com.zhengchuang.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhengchuang.music.service.AdminService;
import com.zhengchuang.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/admin/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        boolean b = adminService.verifyPassword(name, password);
        if (b){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"登录成功");
            jsonObject.put(Const.NAME,name);
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"用户名或密码错误");
        return jsonObject;
    }
}
