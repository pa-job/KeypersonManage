package cn.soa.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.soa.entity.MicroblogDynamic;
@Mapper
public interface MicroblogDynamicMapper {
    int deleteByPrimaryKey(Integer dId);

    int insert(MicroblogDynamic record);

    int insertSelective(MicroblogDynamic record);

    MicroblogDynamic selectByPrimaryKey(Integer dId);

    int updateByPrimaryKeySelective(MicroblogDynamic record);

    int updateByPrimaryKey(MicroblogDynamic record);
    
    ArrayList<MicroblogDynamic> selectAll();
    
    ArrayList<MicroblogDynamic> selectByNum( String num );
    
    
}