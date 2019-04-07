package cn.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.soa.entity.KeyPerson;
import cn.soa.entity.UserTableJson;
import cn.soa.service.inter.KeyPersonInter;

import cn.soa.entity.ResultJson;

@RestController
@RequestMapping("/KeyPerson")
public class KeyPeopleController {
	@Autowired
	private  KeyPersonInter KeyPersonDaoService;
	@RequestMapping("/selectByConditions")
	public UserTableJson selectByConditions(KeyPerson record,@RequestParam(value="page",required=false) Integer page,@RequestParam(value="limit",required=false) Integer pageSize) {
		int count=KeyPersonDaoService.counts(record);
		List<KeyPerson> list=KeyPersonDaoService.selectByConditions(record, (page-1)*pageSize, pageSize);
		return new UserTableJson("",0,"",list,count,true) ;
	}
	@RequestMapping("/deleteByPrimaryKeys")
	public ResultJson<Integer>  deleteByPrimaryKeys(@RequestParam("list[]")List<Integer> keyIds){
		return new ResultJson(KeyPersonDaoService.deleteByPrimaryKeys(keyIds));
	}
	@RequestMapping("/addOrUpdatePepole")
	public ResultJson<Integer> addOrUpdatePepole(KeyPerson record){
		int count=-1;
		if(record.getKeyId()==null) {
			count=KeyPersonDaoService.insert(record);
		}else {
			count=KeyPersonDaoService.updateByPrimaryKey(record);
		}
		return new ResultJson(count);
		
	}
}
