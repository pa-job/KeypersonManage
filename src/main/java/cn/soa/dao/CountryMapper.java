package cn.soa.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.soa.entity.Country;

@Mapper
public interface CountryMapper {
	
	public Country findById( int id );
}
