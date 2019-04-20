package cn.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.soa.entity.KeyEvent;
import cn.soa.entity.KeyPerson;
import cn.soa.entity.ResultJson;
import cn.soa.entity.UserTableJson;
import cn.soa.service.inter.KeyEventInter;


@RestController
@RequestMapping("/KeyEvent")
public class KeyEventController {
	@Autowired
	private  KeyEventInter KeyEventService;
	@RequestMapping("/selectByConditions")
	public UserTableJson selectByConditions(KeyEvent record,@RequestParam(value="page",required=false) Integer page,@RequestParam(value="limit",required=false) Integer pageSize) {
		int count=KeyEventService.counts(record);
		List<KeyEvent> list=KeyEventService.selectByConditions(record, (page-1)*pageSize, pageSize);
		return new UserTableJson("",0,"",list,count,true) ;
	}
	@RequestMapping("/deleteByPrimaryKeys")
	public ResultJson<Integer>  deleteByPrimaryKeys(@RequestParam("list[]")List<Integer> eIds){
		return new ResultJson(KeyEventService.deleteByPrimaryKeys(eIds));
	}
	@RequestMapping("/checkEventCount")
	public ResultJson<Integer>  checkEvent(KeyEvent record){
		return new ResultJson(KeyEventService.counts(record));
	}
	@RequestMapping("/addOrUpdateEvent")
	public ResultJson<Integer> addOrUpdatePepole(KeyEvent record){
		int count=-1;
		if(record.getEid()==null) {
			count=KeyEventService.insert(record);
		}else {
			count=KeyEventService.updateByPrimaryKey(record);
		}
		return new ResultJson(count);
		
	}

}
