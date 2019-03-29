package cn.soa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings( "serial" )
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
@Validated
public class Area  implements Serializable {
	/**   
	 * @Fields serialVersionUID : 序列化  
	 */  
	private static final long serialVersionUID = 111L;
	
	@NotNull(message="id不能为空")
	private Integer caid;
	
	private String countryId ; 
	
	private String areaName ; 
	
	private String areaDetail ; 
	
	private String remark1 ; 
	
	private String remark2 ; 
}
