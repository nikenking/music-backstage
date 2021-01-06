package com.zhengchuang.music.dao;

import com.zhengchuang.music.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 歌手dao*/
@Repository
public interface SingerMapper {
/**
 * 增*/
public int insert(Singer singer);
/**
 * 删*/
public int delete(Integer id);
/**
 * 查*/
public Singer selectByPrimaryKey(Integer id);
/**
 * 改*/
public int update(Singer singer);
/**
 * 查询所有*/
public List<Singer> allSinger();
/**
 * 根据名字模糊查询*/
public List<Singer> singerOfName(String name);
/**
 * 根据性别查询*/
public List<Singer> singerOfSex(Integer sex);

}
