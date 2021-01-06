package com.zhengchuang.music.service.impl;

import com.zhengchuang.music.domain.SongList;
import com.zhengchuang.music.mapper.SongListMapperAnno;
import com.zhengchuang.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    private SongListMapperAnno mapper;
    /**
     * 增加
     *
     * @param songList
     */
    @Override
    public boolean insert(SongList songList) {
        return mapper.insert(songList)>0;
    }

    /**
     * 修改
     *
     * @param songList
     */
    @Override
    public boolean update(SongList songList) {
        return mapper.update(songList)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return mapper.delete(id)>0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单
     */
    @Override
    public List<SongList> allSongList() {
        return mapper.allSongList();
    }

    /**
     * 根据标题精确查询歌单列表
     *
     * @param title
     */
    @Override
    public List<SongList> songListOfTitle(String title) {
        return mapper.songListOfTitle(title);
    }

    /**
     * 根据标题模糊查询歌单列表
     *
     * @param title
     */
    @Override
    public List<SongList> likeTitle(String title) {
        return mapper.likeTitle(title);
    }

    /**
     * 根据风格模糊查询歌单列表
     *
     * @param style
     */
    @Override
    public List<SongList> likeStyle(String style) {
        return mapper.likeStyle(style);
    }
    /**
     * 重置自增主键位置
     */
    @Override
    public void resetIndex() {
        mapper.resetIndex();
    }
}
