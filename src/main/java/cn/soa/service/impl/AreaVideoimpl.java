package cn.soa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.soa.dao.AreaVideoMapper;
import cn.soa.entity.CountryAreaView;
import cn.soa.service.inter.AreaVideoInter;
@Service
public class AreaVideoimpl implements AreaVideoInter {
	@Autowired
	private  AreaVideoMapper AreaVideoDao;
	@Override
	public int insetAreaVideoBatch(List<CountryAreaView> lists) {
		
		return AreaVideoDao.insetAreaVideoBatch(lists);
	}

	@Override
	public int deleteCountryAreaViewByIds(List<Integer> avids) {
		// TODO Auto-generated method stub
		return AreaVideoDao.deleteCountryAreaViewByIds(avids);
	}

}
