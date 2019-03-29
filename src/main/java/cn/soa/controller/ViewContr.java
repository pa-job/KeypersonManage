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
import cn.soa.entity.View;
import cn.soa.entity.headResult.ResultJson;
import cn.soa.service.inter.AreaServInter;
import cn.soa.service.inter.ViewServInter;

@RestController
@RequestMapping("/view")
public class ViewContr {
	private static Logger logger = LoggerFactory.getLogger( ViewContr.class );
	
	@Autowired 
	private ViewServInter viewServ;
	
	@GetMapping("")
	public ResultJson<View> getCountryById( @RequestParam("id")  String id ){
		View view = viewServ.getViewById(id);
		if( StringUtils.isEmpty(view)) {
			return new ResultJson<View>( 1, "查询信息为空", null );
		}
		return new ResultJson<View>( 0, "查询信息成功", view );
	}
}
