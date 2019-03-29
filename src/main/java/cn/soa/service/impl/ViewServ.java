package cn.soa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.soa.dao.AreaMapper;
import cn.soa.dao.ViewMapper;
import cn.soa.entity.Area;
import cn.soa.entity.View;
import cn.soa.service.inter.AreaServInter;
import cn.soa.service.inter.ViewServInter;

@Service
public class ViewServ implements ViewServInter{
	@Autowired
	public ViewMapper viewMapper;
	
	@Override
	public View getViewById( String id ) {
		try {
			View view = viewMapper.findByCAId(Integer.parseInt(id));
			return view;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
