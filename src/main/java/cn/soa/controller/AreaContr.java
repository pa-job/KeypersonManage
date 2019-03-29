package cn.soa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.soa.entity.Area;
import cn.soa.entity.headResult.ResultJson;
import cn.soa.service.inter.AreaServInter;

@RestController
@RequestMapping("/area")
public class AreaContr {
	private static Logger logger = LoggerFactory.getLogger( AreaContr.class );
	
	@Autowired 
	private AreaServInter areaServ;
	
	@GetMapping("")
	public ResultJson<Area> getCountryById( @RequestParam("id")  String id ){
		Area area = areaServ.getCountryById(id);
		if( StringUtils.isEmpty(area)) {
			return new ResultJson<Area>( 1, "查询信息为空", null );
		}
		return new ResultJson<Area>( 0, "查询信息成功", area );
	}
}
