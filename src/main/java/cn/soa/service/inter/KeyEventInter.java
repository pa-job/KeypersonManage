package cn.soa.service.inter;

import java.util.List;

import cn.soa.entity.KeyEvent;
public interface KeyEventInter {
	 List<KeyEvent> selectByConditions(KeyEvent record,Integer page,Integer pageSize);
	 
	  int counts(KeyEvent record);
	  
	  int deleteByPrimaryKeys(List<Integer> eIds);
	  
	  int insert(KeyEvent record);
	  
	  int updateByPrimaryKey(KeyEvent record);
}
