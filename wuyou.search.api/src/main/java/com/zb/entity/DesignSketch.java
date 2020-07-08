package com.zb.entity;


import java.util.List;

public class DesignSketch {

  private Integer id;
  private String designname;
  private String type;
  private String style;
  private Double area;
  private Integer piccount;
  private Integer userid;
  private String firstimg;
  private List<Pic>imgUrl;

  public List<Pic> getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(List<Pic> imgUrl) {
    this.imgUrl = imgUrl;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getDesignname() {
    return designname;
  }

  public void setDesignname(String designname) {
    this.designname = designname;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }


  public Double getArea() {
    return area;
  }

  public void setArea(Double area) {
    this.area = area;
  }


  public Integer getPiccount() {
    return piccount;
  }

  public void setPiccount(Integer piccount) {
    this.piccount = piccount;
  }


  public Integer getUserid() {
    return userid;
  }

  public void setUserid(Integer userid) {
    this.userid = userid;
  }


  public String getFirstimg() {
    return firstimg;
  }

  public void setFirstimg(String firstimg) {
    this.firstimg = firstimg;
  }

}
