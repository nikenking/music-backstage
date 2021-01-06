package com.zhengchuang.music.mapper;

import com.zhengchuang.music.domain.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论Dao
 */
@Mapper
public interface CommentMapperAnno {
    /**
     *修改
     */
    @Update("<script> update comment set id = #{id}" +
            "<if test='userId!=null'>,userId=#{userId}</if>"+
            "<if test='type!=null'>,type=#{type}</if>"+
            "<if test='songId!=null'>,songId=#{songId}</if>"+
            "<if test='songListId!=null'>,songListId=#{songListId}</if>"+
            "<if test='content!=null'>,content=#{content}</if>"+
            "<if test='up!=null'>,up=#{up}</if>"+
            " where id = #{id}</script>")
    public int update(Comment comment);

    /**
     * 删除
     */
    @Delete("delete from comment where id = #{id}")
    public int delete(Integer id);

    /**
     *增加
     */
    @Insert("<script> insert into `comment`(" +
            "<if test='id!=null'>id=#{id},</if>" +
            "<if test='userId!=null'>userId,</if>" +
            "<if test='type!=null'>type,</if>" +
            "<if test='songId!=null'>songId,</if>" +
            "<if test='songListId!=null'>songListId,</if>" +
            "<if test='content!=null'>content,</if>" +
            "<if test='up!=null'>up,</if>" +
            "<if test='createTime!=null'>createTime</if>" +
            ") values(" +
            "<if test='id!=null'>#{id},</if>" +
            "<if test='userId!=null'>#{userId},</if>" +
            "<if test='type!=null'>#{type},</if>" +
            "<if test='songId!=null'>#{songId},</if>" +
            "<if test='songListId!=null'>#{songListId},</if>" +
            "<if test='content!=null'>#{content},</if>" +
            "<if test='up!=null'>#{up}</if>" +
            "<if test='createTime!=null'>#{createTime}</if>" +
            ")" +
            "</script>")
    public int insert(Comment comment);

    /**
     * 根据主键查询整个对象
     */
    @Select("select * from comment where id = #{id}")
    public Comment selectByPrimaryKey(Integer id);

    /**
     * 查询所有评论
     */
    @Select("select * from comment")
    public List<Comment> allComment();

    /**
     * 查询某个歌曲下的所有评论
     */
    @Select("select * from comment where songId = #{songId}")
    public List<Comment> commentOfSongId(Integer songId);

    /**
     * 查询某个歌单下的所有评论
     */
    @Select("select * from comment where songListId = #{songListId}")
    public List<Comment> commentOfSongListId(Integer songListId);
}
















