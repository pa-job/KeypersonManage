package cn.soa.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.soa.entity.Area;

@Mapper
public interface AreaMapper {
	public Area findById( String id );
}
