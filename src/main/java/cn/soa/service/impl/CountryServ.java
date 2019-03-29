package cn.soa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.soa.dao.CountryMapper;
import cn.soa.entity.Country;
import cn.soa.service.inter.CountryServInter;

@Service
public class CountryServ implements CountryServInter{
	@Autowired
	public CountryMapper countryMapper;
	
	@Override
	public Country getCountryById( String id ) {
		try {
			Country country = countryMapper.findById( Integer.parseInt(id) );
			return country;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
