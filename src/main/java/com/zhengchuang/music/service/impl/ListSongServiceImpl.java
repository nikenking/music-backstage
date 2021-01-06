package com.zhengchuang.music.service.impl;

import com.zhengchuang.music.domain.ListSong;
import com.zhengchuang.music.mapper.ListSongMapperAnno;
import com.zhengchuang.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListSongServiceImpl implements ListSongService {
    @Autowired
    private ListSongMapperAnno mapper;
    /**
     * 增加
     *
     * @param listSong
     */
    @Override
    public boolean insert(ListSong listSong) {
        return mapper.insert(listSong)>0;
    }

    /**
     * 修改
     *
     * @param listSong
     */
    @Override
    public boolean update(ListSong listSong) {
        return mapper.update(listSong)>0;
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
     * 根据歌曲id和歌单id删除
     */
    @Override
    public boolean deleteBySongIdAndSongListId(Integer songId,Integer songListId){
        return mapper.deleteBySongIdAndSongListId(songId,songListId)>0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单里面的歌曲
     */
    @Override
    public List<ListSong> allListSong() {
        return mapper.allListSong();
    }

    /**
     * 根据歌单id查询所有的歌曲
     *
     * @param songListId
     */
    @Override
    public List<ListSong> listSongOfSongListId(Integer songListId) {
        System.out.println("查询的songListId："+songListId);
        return mapper.listSongOfSongListId(songListId);
    }
    /**
     * 重置自增主键位置
     */
    @Override
    public void resetIndex() {
        mapper.resetIndex();
    }

}
