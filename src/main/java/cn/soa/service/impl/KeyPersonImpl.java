package cn.soa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.soa.dao.KeyPersonMapper;
import cn.soa.entity.KeyPerson;
import cn.soa.service.inter.KeyPersonInter;
@Service
public class KeyPersonImpl implements  KeyPersonInter{
	@Autowired
	private KeyPersonMapper KeyPersonDao;
	@Override
	public List<KeyPerson> selectByConditions(KeyPerson record, Integer page, Integer pageSize) {
		
		return KeyPersonDao.selectByConditions( record,  page,  pageSize);
	}
	@Override
	public int counts(KeyPerson record) {
		// TODO Auto-generated method stub
		return KeyPersonDao.counts(record);
	}
	@Override
	public int deleteByPrimaryKeys(List<Integer> keyIds) {
		// TODO Auto-generated method stub
		return KeyPersonDao.deleteByPrimaryKeys(keyIds);
	}
	@Override
	public int insert(KeyPerson record) {
		// TODO Auto-generated method stub
		return KeyPersonDao.insert(record);
	}
	@Override
	public int updateByPrimaryKey(KeyPerson record) {
		// TODO Auto-generated method stub
		return KeyPersonDao.updateByPrimaryKey(record);
	}

}
