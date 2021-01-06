package com.zhengchuang.music.service.impl;

import com.zhengchuang.music.dao.AdminMapper;
import com.zhengchuang.music.mapper.AdminMapperAnno;
import com.zhengchuang.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapperAnno mapper;

    /**
     * 验证密码是否正确
     *
     * @param username
     * @param password
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return mapper.verifyPassword(username,password) > 0;
    }
}
