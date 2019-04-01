package cn.soa.service.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.soa.entity.CountryAreaNote;

public interface CountryAreaNoteInter {
	public int addCountryAreaNote( CountryAreaNote note);
	public List<CountryAreaNote> queryCountryAreaNoteByconditions(CountryAreaNote note,Integer page,Integer limit);
}
