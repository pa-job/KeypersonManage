package cn.soa.service.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.soa.entity.KeyPerson;

public interface KeyPersonInter {
	 List<KeyPerson> selectByConditions(KeyPerson record,Integer page,Integer pageSize);
	 
	  int counts(KeyPerson record);
	  
	  int deleteByPrimaryKeys(List<Integer> keyIds);
	  
	  int insert(KeyPerson record);
	  
	  int updateByPrimaryKey(KeyPerson record);
}
