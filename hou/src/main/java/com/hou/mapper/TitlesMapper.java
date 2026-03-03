package com.hou.mapper;

import com.hou.entity.Titles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import java.util.List;


@Mapper
public interface TitlesMapper extends BaseMapper<Titles> {

    @Select("select * from titles where belong=#{belong}")
    List<Titles> findByBelong(@Param("belong") String belong);

}




