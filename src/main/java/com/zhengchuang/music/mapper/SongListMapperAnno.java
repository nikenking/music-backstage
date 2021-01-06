package com.zhengchuang.music.mapper;

import com.zhengchuang.music.domain.Song;
import com.zhengchuang.music.domain.SongList;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SongListMapperAnno {
    /**
     * 通过id查歌单*/
    @Select("select * from songList where id = #{id}")
    public SongList selectByPrimaryKey(Integer id);
    /**
     * 插入歌单*/
    @Insert("insert into songList(id,title,pic,introduction,style) values(#{s.id},#{s.title},#{s.pic},#{s.introduction},#{s.style})")
    public int insert(@Param("s") SongList songList);
    /**
     * 删*/
    @Delete("delete from songList where id = #{id}")
    public int delete(Integer id);
    /**
     *修改
     */
    @Update("<script> update songList set id = #{s.id}" +
            "<if test='s.title!=null'>,title=#{s.title}</if>"+
            "<if test='s.pic!=null'>,pic=#{s.pic}</if>"+
            "<if test='s.introduction!=null'>,introduction=#{s.introduction}</if>"+
            "<if test='s.style!=null'>,style=#{s.style}</if>"+
            " where id = #{s.id}</script>")
    public int update(@Param("s") SongList songList);
    /**
     * 全部查询*/
    @Select("select * from songList")
    public List<SongList> allSongList();
    /**
     * 根据标题精确查询歌单列表*/
    @Select("select * from songList where title  = #{title}")
    public List<SongList> songListOfTitle(String title);
    /**
     * 根据标题模糊查询歌单列表*/
    @Select("select * from songList where title like CONCAT('%',#{title},'%')")
    public List<SongList> likeTitle(String title);
    /**
     * 根据风格模糊查询歌单列表*/
    @Select("select * from songList where style like CONCAT('%',#{style},'%')")
    public List<SongList> likeStyle(String style);
    /**
     * 调整自增*/
    @Select("alter table songList AUTO_INCREMENT = 1")
    public void resetIndex();
}
