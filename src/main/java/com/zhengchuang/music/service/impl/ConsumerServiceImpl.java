package com.zhengchuang.music.service.impl;

import com.zhengchuang.music.domain.Consumer;
import com.zhengchuang.music.mapper.ConsumerMapperAnno;
import com.zhengchuang.music.mapper.ConsumerMapperAnno;
import com.zhengchuang.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    ConsumerMapperAnno mapper;
    /**
     * 增
     *
     * @param consumer
     */
    @Override
    public boolean insert(Consumer consumer) {
        return mapper.insert(consumer)>0;
    }

    /**
     * 删
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return mapper.delete(id)>0;
    }

    /**
     * 查
     *
     * @param id
     */
    @Override
    public Consumer selectByPrimaryKey(Integer id)
    {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 改
     *由于涉及赋值可能为空所以需要校验
     * @param consumer
     */
    @Override
    public boolean update(Consumer consumer) {
        return mapper.update(consumer)>0;
    }

    /**
     * 查询所有
     */
    @Override
    public List<Consumer> allConsumer() {
        return mapper.allConsumer();
    }

    /**
     * 根据名字模糊查询
     *
     * @param name
     */
    @Override
    public List<Consumer> consumerOfName(String name) {
        return mapper.consumerOfName(name);
    }

    /**
     * 根据性别查询
     *
     * @param sex
     */
    @Override
    public List<Consumer> consumerOfSex(Integer sex) {
        return mapper.consumerOfSex(sex);
    }

    /**
     * 查看密码是否正确
     *
     * @param username
     * @param password
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return mapper.verifyPassword(username,password)>0;
    }

    /**
     * 重置自增主键位置
     */
    @Override
    public void resetIndex() {
        mapper.resetIndex();
    }


}
