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
public class SystemConfig {
	private String sid;
	private String sname;
	private String scontent;
	private String stime;
	private String remark1;
	private String remark2;
}
