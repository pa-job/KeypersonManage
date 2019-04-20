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
public class KeyPerson {
    private Integer keyId;

    private String name;

    private Integer userNum;

    private String mircoblogNum;

    private String policeName;

    private Integer relation;

    private String rId;

    private Integer mark;

   
}