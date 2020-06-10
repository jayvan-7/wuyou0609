package com.zb.entity;


public class Pic {

  private Integer id;
  private Integer targetid;
  private String imgUrl;
  private Integer pictype;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getTargetid() {
    return targetid;
  }

  public void setTargetid(Integer targetid) {
    this.targetid = targetid;
  }


  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }


  public Integer getPictype() {
    return pictype;
  }

  public void setPictype(Integer pictype) {
    this.pictype = pictype;
  }

}
