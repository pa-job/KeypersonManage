package cn.soa.service.inter;

import java.util.List;

import cn.soa.entity.CountryAreaView;

public interface AreaVideoInter {
	public  int insetAreaVideoBatch( List<CountryAreaView> lists) ;
	public int deleteCountryAreaViewByIds( List<Integer> avids);
}
