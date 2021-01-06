package com.zhengchuang.music.service;

import com.zhengchuang.music.domain.Consumer;

import java.util.List;

public interface ConsumerService {
    /**
     * 增*/
    public boolean insert(Consumer consumer);
    /**
     * 删*/
    public boolean delete(Integer id);
    /**
     * 查*/
    public Consumer selectByPrimaryKey(Integer id);
    /**
     * 改
     * 由于涉及赋值可能为空所以需要校验*/
    public boolean update(Consumer consumer);
    /**
     * 查询所有*/
    public List<Consumer> allConsumer();
    /**
     * 根据名字模糊查询*/
    public List<Consumer> consumerOfName(String name);
    /**
     * 根据性别查询*/
    public List<Consumer> consumerOfSex(Integer sex);
    /**
     * 查看密码是否正确
     */
    public boolean verifyPassword(String username,String password);
    /**
     * 重置自增主键位置*/
    public void resetIndex();
}
