package cn.soa.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.soa.entity.Area;
import cn.soa.entity.View;

@Mapper
public interface ViewMapper {
	public View findByCAId( int id );
}
