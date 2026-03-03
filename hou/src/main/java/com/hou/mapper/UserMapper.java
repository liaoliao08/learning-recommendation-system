package com.hou.mapper;

import com.hou.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * @author A
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2024-04-29 22:33:31
 * @Entity com.hou.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
        //根据用户名和密码查询用户对象
        @Select("select * from user where name = #{name} and password = #{password}")
        User select(@Param("name") String name,@Param("password")  String password);

        //根据用户名查询用户对象
        @Select("select * from user where name = #{name}")
        User selectByname(String name);

        //添加用户
        @Insert("INSERT INTO user (name, phone, password) VALUES (#{name}, #{phone}, #{password})")
        Integer add(@Param("name") String name, @Param("phone") String phone, @Param("password") String password);

        //修改密码
        @Update("update user set password = #{password} where name = #{name}")
        Integer updatepasswordByName(@Param("name") String name,@Param("password")  String password);

        @Update("update user set password = #{password} where phone = #{phone}")
        Integer updatepasswordByPhone(@Param("phone") String phone,@Param("password")  String password);

        @Select("select * from user where phone = #{phone} and password = #{password}")
        User selectc(@Param("phone") String phone,@Param("password")  String password);

        @Update("update user set studyshort = #{belong} WHERE name = #{name}")
        Integer save(@Param("name") String name,@Param("belong")  String belong);
}




