package com.zhengchuang.music.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapperAnno {
    @Select("select count(*) from admin where name =#{username} and password=#{password}")
    public Integer verifyPassword(String username,String password);
}
