package cn.soa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.soa.entity.CountryAreaView;

@Mapper
public interface AreaVideoMapper {
	public  int insetAreaVideoBatch(@Param("list") List<CountryAreaView> lists) ;
	public int deleteCountryAreaViewByIds(@Param("avids") List<Integer> avids);
	
}
