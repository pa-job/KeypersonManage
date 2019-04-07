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
public class KeyEvent {
    private Integer eid;

    private String eventType;

    private String eventIntro;

    private String eventDetail;

    private String eventTime;

    private String eventPerson;

    private String leaderName;

    private String remark1;

    private String remark2;

}