package com.zhengchuang.music.service.impl;

import com.zhengchuang.music.domain.Singer;
import com.zhengchuang.music.domain.Song;
import com.zhengchuang.music.mapper.SingerMapperAnno;
import com.zhengchuang.music.mapper.SongMapperAnno;
import com.zhengchuang.music.service.SingerService;
import com.zhengchuang.music.service.SongService;
import com.zhengchuang.music.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
/**
 * 歌曲实现类*/
@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongMapperAnno mapper;
    /**
     * 增
     *
     * @param song
     */
    @Override
    public boolean insert(Song song) {
        return mapper.insert(song) >0;
    }

    /**
     * 删
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        /**
         * 删除前先删除掉对应的歌曲*/
        String url = selectByPrimaryKey(id).getUrl();
        System.out.println("删除文件:\""+url+"\""+(FileUtil.removeFile(url)?"成功":"失败"));;
        return mapper.delete(id)>0;
    }

    /**
     * 查
     *
     * @param id
     */
    @Override
    public Song selectByPrimaryKey(Integer id)
    {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 改
     *由于涉及赋值可能为空所以需要校验
     * @param song
     */
    @Override
    public boolean update(Song song) {
        return mapper.update(song)>0;
    }

    /**
     * 查询所有
     */
    @Override
    public List<Song> allSong() {
        return mapper.allSong();
    }

    /**
     * 根据名字模糊查询
     *
     * @param name
     */
    @Override
    public List<Song> songOfName(String name) {
        return mapper.songOfName(name);
    }

    /**
     * 根据歌手id查询
     *
     * @param singerId
     */
    @Override
    public List<Song> songOfSingerId(Integer singerId) {
        return mapper.songOfSingerId(singerId);
    }

    /**
     * 重置自增主键位置
     */
    @Override
    public void resetIndex() {
        mapper.resetIndex();
    }
}
