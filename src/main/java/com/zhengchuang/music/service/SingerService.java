package com.zhengchuang.music.service;

import com.zhengchuang.music.domain.Singer;
import org.springframework.stereotype.Service;

import java.util.List;
public interface SingerService {
    /**
     * 增*/
    public boolean insert(Singer singer);
    /**
     * 删*/
    public boolean delete(Integer id);
    /**
     * 查*/
    public Singer selectByPrimaryKey(Integer id);
    /**
     * 改
     * 由于涉及赋值可能为空所以需要校验*/
    public boolean update(Singer singer);
    /**
     * 查询所有*/
    public List<Singer> allSinger();
    /**
     * 根据名字模糊查询*/
    public List<Singer> singerOfName(String name);
    /**
     * 根据性别查询*/
    public List<Singer> singerOfSex(Integer sex);

    /**
     * 重置自增主键位置*/
    public void resetIndex();
}
