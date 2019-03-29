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
public class View  implements Serializable {
	/**   
	 * @Fields serialVersionUID : 序列化  
	 */  
	private static final long serialVersionUID = 111L;
	

	private Integer avid;
	
	private Integer countryAreaId ; 
	
	private String viewName ; 
	
	private String viewUrl ; 
	
	private String remark1 ; 
	
	private String remark2 ; 
}
