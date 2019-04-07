package cn.soa.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@SuppressWarnings( "serial" )
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors( chain=true )
public class MicroblogDynamic {
    private Integer dId;

    private String mircoblogNum;

    private String mircoblogContent;

    private Date creatTime;

    private String remark1;

   
}