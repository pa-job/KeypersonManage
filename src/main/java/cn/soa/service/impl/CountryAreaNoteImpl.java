package cn.soa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.soa.dao.CountryAreaNoteMapper;
import cn.soa.entity.CountryAreaNote;
import cn.soa.service.inter.CountryAreaNoteInter;
@Service
public class CountryAreaNoteImpl implements CountryAreaNoteInter {
	@Autowired
	private CountryAreaNoteMapper CountryAreaNoteDao;
	@Override
	public int addCountryAreaNote(CountryAreaNote note) {
		// TODO Auto-generated method stub
		return CountryAreaNoteDao.addCountryAreaNote(note);
	}
	@Override
	public List<CountryAreaNote> queryCountryAreaNoteByconditions(CountryAreaNote note,Integer page,Integer limit) {
		// TODO Auto-generated method stub
		return  CountryAreaNoteDao.queryCountryAreaNoteByconditions(note,page,limit);
	}

}
