package com.zb.pojo;
import java.io.Serializable;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
public class Record implements Serializable {
    //
    private Integer id;
    //用户id
    private Integer userid;
    //订单编号，由订单生成器自动生成
    private String orderno;
    //交易编号，由支付宝支付成功后产生
    private String tradwno;
    //公司id
    private Integer companyid;
    //公司名称
    private String companyname;
    //支付状态（0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评）
    private Integer orderstatus;
    //联系手机号
    private String noticePhone;
    //支付完成时间
    private String modifyDate;
    //修改时间
    private String updateTime;
    //乐观锁版本号
    private Integer version;
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
    public void setOrderno (String  orderno){
        this.orderno=orderno;
    }
    public  String getOrderno(){
        return this.orderno;
    }
    public void setTradwno (String  tradwno){
        this.tradwno=tradwno;
    }
    public  String getTradwno(){
        return this.tradwno;
    }
    public void setCompanyid (Integer  companyid){
        this.companyid=companyid;
    }
    public  Integer getCompanyid(){
        return this.companyid;
    }
    public void setCompanyname (String  companyname){
        this.companyname=companyname;
    }
    public  String getCompanyname(){
        return this.companyname;
    }
    public void setOrderstatus (Integer  orderstatus){
        this.orderstatus=orderstatus;
    }
    public  Integer getOrderstatus(){
        return this.orderstatus;
    }
    public void setNoticePhone (String  noticePhone){
        this.noticePhone=noticePhone;
    }
    public  String getNoticePhone(){
        return this.noticePhone;
    }
    public void setModifyDate (String  modifyDate){
        this.modifyDate=modifyDate;
    }
    public  String getModifyDate(){
        return this.modifyDate;
    }
    public void setUpdateTime (String  updateTime){
        this.updateTime=updateTime;
    }
    public  String getUpdateTime(){
        return this.updateTime;
    }
    public void setVersion (Integer  version){
        this.version=version;
    }
    public  Integer getVersion(){
        return this.version;
    }
}
