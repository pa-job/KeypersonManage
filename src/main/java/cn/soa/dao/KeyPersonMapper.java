package cn.soa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.soa.entity.KeyPerson;
@Mapper
public interface KeyPersonMapper {
    int deleteByPrimaryKeys(@Param("lists")List<Integer> keyIds);

    int insert(KeyPerson record);

    List<KeyPerson> selectByConditions(@Param("record")KeyPerson record,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
    
    int counts(@Param("record")KeyPerson record);
    
    int updateByPrimaryKey(KeyPerson record);
    
    List<KeyPerson> selectByMark();
}