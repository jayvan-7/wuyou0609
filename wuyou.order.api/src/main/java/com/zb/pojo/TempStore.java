package com.zb.pojo;
import java.io.Serializable;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
public class TempStore implements Serializable {
    //
    private Integer id;
    //抢购成功的用户id
    private Integer userid;
    //抢购成功时的时间
    private String recordtime;
    //剩余库存
    private Integer store;
    //get set 方法
    public void setId (Integer  id){
        this.id=id;
    }
    public  Integer getId(){
        return this.id;
    }
    public void setUserid (Integer  userid){
        this.userid=userid;
    }
    public  Integer getUserid(){
        return this.userid;
    }
    public void setRecordtime (String  recordtime){
        this.recordtime=recordtime;
    }
    public  String getRecordtime(){
        return this.recordtime;
    }
    public void setStore (Integer  store){
        this.store=store;
    }
    public  Integer getStore(){
        return this.store;
    }
}
