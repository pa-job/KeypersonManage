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
public class CountryAreaNote {
	private Integer anid;
	private Integer countryAreaId;
	private Integer parentId;
	private String createTime;
	private Integer noteId;
	private String noteName ;
	private String detail;
	private String remark1;
	private String remark2;
}
