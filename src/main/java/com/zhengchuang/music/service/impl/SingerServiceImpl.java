package com.zhengchuang.music.service.impl;

import com.zhengchuang.music.domain.Singer;
import com.zhengchuang.music.mapper.SingerMapperAnno;
import com.zhengchuang.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    SingerMapperAnno mapper;
    /**
     * 增
     *
     * @param singer
     */
    @Override
    public boolean insert(Singer singer) {
        return mapper.insert(singer)>0;
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
    public Singer selectByPrimaryKey(Integer id)
    {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 改
     *由于涉及赋值可能为空所以需要校验
     * @param singer
     */
    @Override
    public boolean update(Singer singer) {
        return mapper.update(singer)>0;
    }

    /**
     * 查询所有
     */
    @Override
    public List<Singer> allSinger() {
        return mapper.allSinger();
    }

    /**
     * 根据名字模糊查询
     *
     * @param name
     */
    @Override
    public List<Singer> singerOfName(String name) {
        return mapper.singerOfName(name);
    }

    /**
     * 根据性别查询
     *
     * @param sex
     */
    @Override
    public List<Singer> singerOfSex(Integer sex) {
        return mapper.singerOfSex(sex);
    }

    /**
     * 重置自增主键位置
     */
    @Override
    public void resetIndex() {
        mapper.resetIndex();
    }
}
