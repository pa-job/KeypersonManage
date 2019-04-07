package cn.soa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.soa.dao.KeyEventMapper;
import cn.soa.entity.KeyEvent;
import cn.soa.service.inter.KeyEventInter;
@Service
public class KeyEventImpl  implements KeyEventInter{
	@Autowired
	private KeyEventMapper KeyEventDao;
	@Override
	public List<KeyEvent> selectByConditions(KeyEvent record, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return KeyEventDao.selectByConditions(record, page, pageSize);
	}

	@Override
	public int counts(KeyEvent record) {
		// TODO Auto-generated method stub
		return KeyEventDao.counts(record);
	}

	@Override
	public int deleteByPrimaryKeys(List<Integer> eIds) {
		// TODO Auto-generated method stub
		return KeyEventDao.deleteByPrimaryKeys(eIds);
	}

	@Override
	public int insert(KeyEvent record) {
		// TODO Auto-generated method stub
		return KeyEventDao.insert(record);
	}

	@Override
	public int updateByPrimaryKey(KeyEvent record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
