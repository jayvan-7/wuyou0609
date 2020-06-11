package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class Role implements Serializable {
    //
    private Integer rid;
    //
    private String rolename;
    //
    private String rdesc;
    //get set 方法
    public void setRid (Integer  rid){
        this.rid=rid;
    }
    public  Integer getRid(){
        return this.rid;
    }
    public void setRolename (String  rolename){
        this.rolename=rolename;
    }
    public  String getRolename(){
        return this.rolename;
    }
    public void setRdesc (String  rdesc){
        this.rdesc=rdesc;
    }
    public  String getRdesc(){
        return this.rdesc;
    }
}
