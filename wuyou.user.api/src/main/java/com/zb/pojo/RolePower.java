package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class RolePower implements Serializable {
    //
    private Integer rid;
    //
    private Integer pid;
    //get set 方法
    public void setRid (Integer  rid){
        this.rid=rid;
    }
    public  Integer getRid(){
        return this.rid;
    }
    public void setPid (Integer  pid){
        this.pid=pid;
    }
    public  Integer getPid(){
        return this.pid;
    }
}
