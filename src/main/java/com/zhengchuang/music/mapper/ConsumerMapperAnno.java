package com.zhengchuang.music.mapper;

import com.zhengchuang.music.domain.Consumer;
import com.zhengchuang.music.domain.Singer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConsumerMapperAnno {
    /**
     * 通过id查用户
     */
    @Select("select * from consumer where id = #{id}")
    public Consumer selectByPrimaryKey(Integer id);

    /**
     * 插入用户
     */
    @Insert("insert into consumer(id,username,password,sex,phoneNum,email,birth," +
            "introduction,location,avator,createTime,updateTime)" +
            " values(#{s.id},#{s.username},#{s.password},#{s.sex}," +
            "#{s.phoneNum},#{s.email},#{s.birth},#{s.introduction}" +
            ",#{s.location},#{s.avator},#{s.createTime},#{s.updateTime})")
    public int insert(@Param("s") Consumer consumer);

    /**
     * 删除用户
     */
    @Delete("delete from consumer where id = #{id}")
    public int delete(Integer id);

    /**
     * 修改用户
     */
    @Update("<script> update consumer set id = #{s.id}" +
            "<if test='s.username!=null'>,username=#{s.username}</if>" +
            "<if test='s.password!=null'>,password=#{s.password}</if>" +
            "<if test='s.sex!=null'>,sex=#{s.sex}</if>" +
            "<if test='s.phoneNum!=null'>,phoneNum=#{s.phoneNum}</if>" +
            "<if test='s.email!=null'>,email=#{s.email}</if>" +
            "<if test='s.birth!=null'>,birth=#{s.birth}</if>" +
            "<if test='s.introduction!=null'>,introduction=#{s.introduction}</if>" +
            "<if test='s.location!=null'>,location=#{s.location}</if>" +
            "<if test='s.avator!=null'>,avator=#{s.avator}</if>" +
            "<if test='s.createTime!=null'>,createTime=#{s.createTime}</if>" +
            "<if test='s.updateTime!=null'>,updateTime=#{s.updateTime}</if>" +
            " where id = #{s.id}</script>")
    public int update(@Param("s") Consumer consumer);

    /**
     * 全部查询
     */
    @Select("select * from consumer")
    public List<Consumer> allConsumer();

    /**
     * 通过名字模糊查询
     */
    @Select("select * from consumer where username like CONCAT('%',#{name},'%')")
    public List<Consumer> consumerOfName(String name);

    /**
     * 通过性别查询*/
    @Select("select * from consumer where sex = #{sex}")
    List<Consumer> consumerOfSex(Integer sex);

    /**
     * 判断密码是否正确*/
    @Select("select * from consumer where username = #{username} and password = #{password}")
    public int verifyPassword(String username,String password);

    @Select("alter table consumer AUTO_INCREMENT = 1")
    public void resetIndex();
}
