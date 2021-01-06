package com.zhengchuang.music.service;

import com.zhengchuang.music.domain.Singer;
import com.zhengchuang.music.domain.Song;

import java.util.List;

public interface SongService {
    /**
     * 增*/
    public boolean insert(Song song);
    /**
     * 删*/
    public boolean delete(Integer id);
    /**
     * 查*/
    public Song selectByPrimaryKey(Integer id);
    /**
     * 改
     * 由于涉及赋值可能为空所以需要校验*/
    public boolean update(Song song);
    /**
     * 查询所有*/
    public List<Song> allSong();
    /**
     * 根据名字模糊查询*/
    public List<Song> songOfName(String name);
    /**
     * 根据歌手id查询*/
    public List<Song> songOfSingerId(Integer singerId);
    /**
     * 重置自增主键位置*/
    public void resetIndex();
}
