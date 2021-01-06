package com.zhengchuang.music.mapper;

import com.zhengchuang.music.domain.Singer;
import com.zhengchuang.music.domain.Song;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SongMapperAnno {
    /**
     * 通过id查歌曲*/
    @Select("select * from song where id = #{id}")
    public Song selectByPrimaryKey(Integer id);
    /**
     * 插入歌手*/
    @Insert("insert into song(id,singerId,name,introduction,createTime,updateTime,pic,lyric,url) values(#{s.id},#{s.singerId},#{s.name},#{s.introduction},#{s.createTime},#{s.updateTime},#{s.pic},#{s.lyric},#{s.url})")
    public int insert(@Param("s") Song song);
    /**
     * 删除歌曲*/
    @Delete("delete from song where id = #{id}")
    public int delete(Integer id);
    /**
     * 修改歌曲*/
    @Update("<script> update song set id = #{s.id}" +
            "<if test='s.singerId!=null'>,singerId=#{s.singerId}</if>"+
            "<if test='s.name!=null'>,name=#{s.name}</if>"+
            "<if test='s.introduction!=null'>,introduction=#{s.introduction}</if>"+
            "<if test='s.createTime!=null'>,createTime=#{s.createTime}</if>"+
            "<if test='s.updateTime!=null'>,updateTime=#{s.updateTime}</if>"+
            "<if test='s.pic!=null'>,pic=#{s.pic}</if>"+
            "<if test='s.lyric!=null'>,lyric=#{s.lyric}</if>"+
            "<if test='s.url!=null'>,url=#{s.url}</if>"+
            " where id = #{s.id}</script>")
    public int update(@Param("s") Song song);
    /**
     * 全部查询*/
    @Select("select * from song")
    public List<Song> allSong();
    /**
     * 通过歌名模糊查询*/
    @Select("select * from song where name like CONCAT('%',#{name},'%')")
    public List<Song> songOfName(String name);
    /**
     * 根据歌手id查询*/
    @Select("select * from song where singerId = #{singerId}")
    public List<Song> songOfSingerId(Integer singerId);
    /**
     * 根据歌手名查询*/
    @Select({"<script>update song set id = #{s.id}"
            +"<if test='s.name!=null'>,name=#{s.name}</if>"
            +" where id = #{s.id}</script>"})
    public Object updateTest(@Param("s") Song song);
    /**
     * 调整自增*/
    @Select("alter table song AUTO_INCREMENT = 1")
    public void resetIndex();
}
