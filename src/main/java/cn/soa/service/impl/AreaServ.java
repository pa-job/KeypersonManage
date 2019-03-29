package cn.soa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.soa.dao.AreaMapper;
import cn.soa.entity.Area;
import cn.soa.service.inter.AreaServInter;

@Service
public class AreaServ implements AreaServInter{
	@Autowired
	public AreaMapper areaMapper;
	
	@Override
	public Area getCountryById( String id ) {
		try {
			Area Area = areaMapper.findById( id );
			return Area;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
