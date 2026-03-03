package com.hou.mapper;

import com.hou.entity.Sources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import java.util.List;


@Mapper
public interface SourcesMapper extends BaseMapper<Sources> {

    @Select("SELECT * FROM sources where name LIKE CONCAT('%', #{name}, '%') LIMIT #{offset}, #{limit}")
    List<Sources> findSourcesByName(@Param("name") String name, @Param("offset") int offset, @Param("limit") int limit);

    // 可能还需要一个方法来获取总记录数
    @Select("select count(*) FROM sources where name LIKE CONCAT('%', #{name}, '%')")
    long countSourcesByName(@Param("name") String name);

        // 添加分页查询方法
        @Select("SELECT * FROM sources where belong=#{belong} LIMIT #{offset}, #{limit}")
        List<Sources> findSourcesByBelong(@Param("belong") String belong, @Param("offset") int offset, @Param("limit") int limit);

        // 可能还需要一个方法来获取总记录数
        @Select("select count(*) FROM sources where belong=#{belong}")
        long countSourcesByBelong(@Param("belong") String belong);


}




