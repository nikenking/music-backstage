package com.zhengchuang.music.mapper;

import com.zhengchuang.music.domain.Singer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SingerMapperAnno {
    /**
     * 通过id查歌手*/
    @Select("select * from singer where id = #{id}")
    public Singer selectByPrimaryKey(Integer id);
    /**
     * 插入歌手*/
    @Insert("insert into singer(id,name,sex,pic,birth,location,introduction) values(#{s.id},#{s.name},#{s.sex},#{s.pic},#{s.birth},#{s.location},#{s.introduction})")
    public int insert(@Param("s") Singer singer);
    /**
     * 删除歌手*/
    @Delete("delete from singer where id = #{id}")
    public int delete(Integer id);
    /**
     * 修改歌手*/
    @Update("<script> update singer set id = #{s.id}" +
            "<if test='s.name!=null'>,name=#{s.name}</if>"+
            "<if test='s.sex!=null'>,sex=#{s.sex}</if>"+
            "<if test='s.introduction!=null'>,introduction=#{s.introduction}</if>"+
            "<if test='s.location!=null'>,location=#{s.location}</if>"+
            "<if test='s.birth!=null'>,birth=#{s.birth}</if>"+
            "<if test='s.pic!=null'>,pic=#{s.pic}</if>"+
            " where id = #{s.id}</script>")
    public int update(@Param("s") Singer singer);
    /**
     * 全部查询*/
    @Select("select * from singer")
    public List<Singer> allSinger();
    /**
     * 通过名字模糊查询*/
    @Select("select * from singer where name like CONCAT('%',#{name},'%')")
    public List<Singer> singerOfName(String name);

    @Select("select * from singer where sex = #{sex}")
    public List<Singer> singerOfSex(Integer sex);

    @Select("alter table singer AUTO_INCREMENT = 1")
    public void resetIndex();
}
