package com.zhengchuang.music.mapper;

import com.zhengchuang.music.domain.Rank;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * 评价Dao
 */
@Mapper
public interface RankMapperAnno {
    /**
     *增加
     */
    @Insert("<script> insert into `rank`(" +
            "<if test='id!=null'>id=#{id},</if>" +
            "<if test='songListId!=null'>songListId,</if>" +
            "<if test='consumerId!=null'>consumerId,</if>" +
            "<if test='score!=null'>score</if>" +
            ") values(" +
            "<if test='id!=null'>#{id},</if>" +
            "<if test='songListId!=null'>#{songListId},</if>" +
            "<if test='consumerId!=null'>#{consumerId},</if>" +
            "<if test='score!=null'>#{score}</if>" +
            ")" +
            "</script>")
    public int insert(Rank rank);

    /**
     * 查总分
     */
    @Select("select coalesce(sum(score),0) as score from `rank` where songListId = #{songListId}")
    public int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     */
    @Select("select coalesce(count(id),0) as num from `rank` where songListId = #{songListId}")
    public int selectRankNum(Integer songListId);
}















