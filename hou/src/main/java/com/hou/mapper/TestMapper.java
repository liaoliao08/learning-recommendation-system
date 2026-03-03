package com.hou.mapper;

import com.hou.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author A
* @description 针对表【test】的数据库操作Mapper
* @createDate 2024-05-07 16:44:44
* @Entity com.hou.entity.Test1
*/
@Mapper
public interface TestMapper extends BaseMapper<Test> {

}




