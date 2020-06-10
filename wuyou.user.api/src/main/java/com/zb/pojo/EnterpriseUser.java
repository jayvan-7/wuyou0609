package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class EnterpriseUser implements Serializable {
    //企业用户编号
    private Integer id;
    //企业名称
    private String companyName;
    //联系人姓名
    private String linkname;
    //联系电话
    private String linkphone;
    //账户名（登陆用）
    private String companyUsername;
    //登陆密码
    private String password;
    //企业所在地
    private String companyAddress;
    //企业头像
    private String companyPic;
    //公司特色服务(保存附文本)
    private String companyFeature;
    //公司资质(保存附文本)
    private String companyQualification;
    //公司简介
    private String companyIntroduce;
    //公司认证状态(0：未认证  1：已认证)
    private Integer companyStatus;
    //对应角色id
    private Integer roleid;
    //get set 方法
    public void setId (Integer  id){
        this.id=id;
    }
    public  Integer getId(){
        return this.id;
    }
    public void setCompanyName (String  companyName){
        this.companyName=companyName;
    }
    public  String getCompanyName(){
        return this.companyName;
    }
    public void setLinkname (String  linkname){
        this.linkname=linkname;
    }
    public  String getLinkname(){
        return this.linkname;
    }
    public void setLinkphone (String  linkphone){
        this.linkphone=linkphone;
    }
    public  String getLinkphone(){
        return this.linkphone;
    }
    public void setCompanyUsername (String  companyUsername){
        this.companyUsername=companyUsername;
    }
    public  String getCompanyUsername(){
        return this.companyUsername;
    }
    public void setPassword (String  password){
        this.password=password;
    }
    public  String getPassword(){
        return this.password;
    }
    public void setCompanyAddress (String  companyAddress){
        this.companyAddress=companyAddress;
    }
    public  String getCompanyAddress(){
        return this.companyAddress;
    }
    public void setCompanyPic (String  companyPic){
        this.companyPic=companyPic;
    }
    public  String getCompanyPic(){
        return this.companyPic;
    }
    public void setCompanyFeature (String  companyFeature){
        this.companyFeature=companyFeature;
    }
    public  String getCompanyFeature(){
        return this.companyFeature;
    }
    public void setCompanyQualification (String  companyQualification) {
        this.companyQualification = companyQualification;
    }
    //hhhh
    public  String getCompanyQualification(){
        return this.companyQualification;
    }
    public void setCompanyIntroduce (String  companyIntroduce){
        this.companyIntroduce=companyIntroduce;
    }
    public  String getCompanyIntroduce(){
        return this.companyIntroduce;
    }
    public void setCompanyStatus (Integer  companyStatus){
        this.companyStatus=companyStatus;
    }
    public  Integer getCompanyStatus(){
        return this.companyStatus;
    }
    public void setRoleid (Integer  roleid){
        this.roleid=roleid;
    }
    public  Integer getRoleid(){
        return this.roleid;
    }
}
