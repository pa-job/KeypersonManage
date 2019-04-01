package cn.soa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.soa.entity.CountryAreaNote;
@Mapper
public interface CountryAreaNoteMapper {
	public int addCountryAreaNote(@Param("note") CountryAreaNote note);
	public List<CountryAreaNote> queryCountryAreaNoteByconditions(@Param("note") CountryAreaNote note,@Param("page") Integer page,@Param("limit") Integer limit);
	public int deletCountryAreaNote(@Param("lists") List<CountryAreaNote> lists);
}
