package com.zb.pojo;
import java.io.Serializable;
import java.util.Date;
/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
public class TempStore implements Serializable {
    //
    private Long id;
    //抢购成功的用户id
    private Integer userid;
    //抢购成功时的时间
    private String recordtime;
    //剩余库存
    private Integer store;
    //公司id
    private Integer companyid;
    //支付状态（0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评）
    private Integer status;
    //get set 方法


    public TempStore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public void setCompanyid (Integer  companyid){
        this.companyid=companyid;
    }
    public  Integer getCompanyid(){
        return this.companyid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
