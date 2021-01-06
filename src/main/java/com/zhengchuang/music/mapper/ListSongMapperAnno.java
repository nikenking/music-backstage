package com.zhengchuang.music.mapper;

import com.zhengchuang.music.domain.ListSong;
import com.zhengchuang.music.domain.Song;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ListSongMapperAnno {
    /**
     * 插*/
    @Insert("insert into listSong(id,songId,songListId)values(#{l.id},#{l.songId},#{l.songListId})")
    int insert(@Param("l") ListSong listSong);

    /**
     * 改*/
    @Update("<script> update listSong set id = #{l.id}" +
            "<if test='l.songId!=null'>,title=#{l.songId}</if>"+
            "<if test='l.songListId!=null'>,songListId=#{l.songListId}</if>"+
            " where id = #{l.id}</script>")
    int update(@Param("l") ListSong listSong);
    /**
     * 删*/
    @Delete("delete from listSong where id = #{id}")
    int delete(Integer id);

    /**
     * 通过歌单id和歌曲id来删除歌单记录*/
    @Delete("delete from listSong where songId=#{songId} and songListId=#{songListId}")
    int deleteBySongIdAndSongListId(Integer songId, Integer songListId);

    /**
     * 查*/
    @Select("select * from listSong where id = #{id}")
    ListSong selectByPrimaryKey(Integer id);

    /**
     * 所有的歌单对应关系，，，量有点大*/
    @Select("select * from listSong")
    List<ListSong> allListSong();

    /**
     * 查询某个歌单的所有对应关系*/
    @Select("select * from listSong where songListId = #{songListId}")
    List<ListSong> listSongOfSongListId(Integer songListId);

    /**
     * 调整自增*/
    @Select("alter table listSong AUTO_INCREMENT = 1")
    public void resetIndex();
}
