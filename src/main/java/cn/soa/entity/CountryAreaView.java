package cn.soa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings( "serial" )
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
public class CountryAreaView {
	private  Integer avid;
	private   Integer countryAreaId;
	private String viewName;
	private String  areaUrl;
	private String  remark1;
	private String  remark2;
	public CountryAreaView(Integer countryAreaId, String viewName, String areaUrl) {
		super();
		this.countryAreaId = countryAreaId;
		this.viewName = viewName;
		this.areaUrl = areaUrl;
	}
	
}
