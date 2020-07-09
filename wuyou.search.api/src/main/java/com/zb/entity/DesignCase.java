package com.zb.entity;


public class DesignCase {

  private Integer id;
  private Integer companyid;
  private String title;
  private Double cost;
  private String briefinfo;
  private String designImg;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getCompanyid() {
    return companyid;
  }

  public void setCompanyid(Integer companyid) {
    this.companyid = companyid;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }


  public String getBriefinfo() {
    return briefinfo;
  }

  public void setBriefinfo(String briefinfo) {
    this.briefinfo = briefinfo;
  }


  public String getDesignImg() {
    return designImg;
  }

  public void setDesignImg(String designImg) {
    this.designImg = designImg;
  }

}
