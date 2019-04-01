package cn.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.soa.entity.CountryAreaNote;
import cn.soa.entity.headResult.ResultJson;
import cn.soa.service.inter.CountryAreaNoteInter;

@RestController
@RequestMapping("/areaNote")
public class CountryAreaNoteController {
	@Autowired
	private CountryAreaNoteInter CountryAreaNote;
	@RequestMapping("/addCountryAreaNote")
	public ResultJson<Integer> addCountryAreaNote(CountryAreaNote note){
		return new ResultJson(CountryAreaNote.addCountryAreaNote(note));
		
	}
	@RequestMapping("/queryCountryAreaNoteByconditions")
	public ResultJson<List<CountryAreaNote>> queryCountryAreaNoteByconditions(CountryAreaNote note,@RequestParam(value="page",required=false) Integer page,@RequestParam(value="limit",required=false)Integer limit){
		if(note.getParentId()==null) {
			note.setParentId(0);
		};
		if(page==null||limit==null) {
			page=1;
			limit=5;
					
		}
		return new ResultJson(CountryAreaNote.queryCountryAreaNoteByconditions(note,(page-1)*limit, limit));
		
	}
}
