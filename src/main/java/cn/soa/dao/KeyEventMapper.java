package cn.soa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.soa.entity.KeyEvent;

@Mapper
public interface KeyEventMapper {
    int deleteByPrimaryKeys(@Param("lists")List<Integer> eIds);

    int insert(@Param("record")KeyEvent record);

    List<KeyEvent> selectByConditions(@Param("record")KeyEvent record,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
    
    int counts(@Param("record")KeyEvent record);
    
    int updateByPrimaryKey(@Param("record")KeyEvent record);
}