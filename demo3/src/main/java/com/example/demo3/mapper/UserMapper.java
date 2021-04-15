package com.example.demo3.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {


    @Update("update user set pass=#{age} where id = #{id}")
    void updateAge(@Param("id")String id, @Param("age") String age);


    @Update("update user set name = #{name} where id = #{id}")
    void updateName(@Param("id")String id, @Param("name")String name);
}
