package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class AdminUser implements Serializable {
    //
    private Integer id;
    //网站维护管理员登陆账号
    private String username;
    //登陆密码
    private String pwd;
    //对应的角色id
    private Integer roleid;
    //get set 方法
    public void setId (Integer  id){
        this.id=id;
    }
    public  Integer getId(){
        return this.id;
    }
    public void setUsername (String  username){
        this.username=username;
    }
    public  String getUsername(){
        return this.username;
    }
    public void setPwd (String  pwd){
        this.pwd=pwd;
    }
    public  String getPwd(){
        return this.pwd;
    }
    public void setRoleid (Integer  roleid){
        this.roleid=roleid;
    }
    public  Integer getRoleid(){
        return this.roleid;
    }
}
