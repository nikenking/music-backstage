package com.zhengchuang.music.service;
/**
* 管理员service*/
public interface AdminService {
    /**
     * 验证密码是否正确*/
    public boolean verifyPassword(String username,String password);

}
