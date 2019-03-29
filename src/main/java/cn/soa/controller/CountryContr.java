package cn.soa.controller;

import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.soa.entity.Country;
import cn.soa.entity.headResult.ResultJson;
import cn.soa.service.inter.CountryServInter;

@RestController
@RequestMapping("/country")
public class CountryContr {
	private static Logger logger = LoggerFactory.getLogger( CountryContr.class );
	
	@Autowired 
	private CountryServInter countryServ;
	
	@GetMapping("")
	public ResultJson<Country> getCountryById( @RequestParam("id")  String id ){
		Country country = countryServ.getCountryById(id);
		if( StringUtils.isEmpty(country)) {
			return new ResultJson<Country>( 1, "查询信息为空", null );
		}
		return new ResultJson<Country>( 0, "查询信息成功", country );
	}
}
