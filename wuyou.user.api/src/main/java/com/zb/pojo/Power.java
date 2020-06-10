package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class Power implements Serializable {
    //
    private Integer pid;
    //
    private String title;
    //
    private Integer parentId;
    //
    private String url;
    //get set 方法
    public void setPid (Integer  pid){
        this.pid=pid;
    }
    public  Integer getPid(){
        return this.pid;
    }
    public void setTitle (String  title){
        this.title=title;
    }
    public  String getTitle(){
        return this.title;
    }
    public void setParentId (Integer  parentId){
        this.parentId=parentId;
    }
    public  Integer getParentId(){
        return this.parentId;
    }
    public void setUrl (String  url){
        this.url=url;
    }
    public  String getUrl(){
        return this.url;
    }
}
