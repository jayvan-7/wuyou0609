package com.zb.entity;


import java.util.List;

public class EnterpriseUser {

  private Integer id;
  private String companyName;
  private String linkname;
  private String linkphone;
  private String companyUsername;
  private String password;
  private String companyAddress;
  private String companyPic;
  private String companyFeature;
  private String companyQualification;
  private String companyIntroduce;
  private Integer companyStatus;
  private Integer roleid;
  private Integer isdiscount;
  private Integer discountnum;
  private Double score;   //评分
  private Double recommend;   //好评率
  private Integer commentnum;   //评论人数
  private List<Area> areas;   //装修公司的服务区域范围
  private List<ExtendProperty> extendProperties;  //装修公司的特色

  public List<Area> getAreas() {
    return areas;
  }

  public void setAreas(List<Area> areas) {
    this.areas = areas;
  }

  public List<ExtendProperty> getExtendProperties() {
    return extendProperties;
  }

  public void setExtendProperties(List<ExtendProperty> extendProperties) {
    this.extendProperties = extendProperties;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public Double getRecommend() {
    return recommend;
  }

  public void setRecommend(Double recommend) {
    this.recommend = recommend;
  }

  public Integer getCommentnum() {
    return commentnum;
  }

  public void setCommentnum(Integer commentnum) {
    this.commentnum = commentnum;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }


  public String getLinkname() {
    return linkname;
  }

  public void setLinkname(String linkname) {
    this.linkname = linkname;
  }


  public String getLinkphone() {
    return linkphone;
  }

  public void setLinkphone(String linkphone) {
    this.linkphone = linkphone;
  }


  public String getCompanyUsername() {
    return companyUsername;
  }

  public void setCompanyUsername(String companyUsername) {
    this.companyUsername = companyUsername;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getCompanyAddress() {
    return companyAddress;
  }

  public void setCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
  }


  public String getCompanyPic() {
    return companyPic;
  }

  public void setCompanyPic(String companyPic) {
    this.companyPic = companyPic;
  }


  public String getCompanyFeature() {
    return companyFeature;
  }

  public void setCompanyFeature(String companyFeature) {
    this.companyFeature = companyFeature;
  }


  public String getCompanyQualification() {
    return companyQualification;
  }

  public void setCompanyQualification(String companyQualification) {
    this.companyQualification = companyQualification;
  }


  public String getCompanyIntroduce() {
    return companyIntroduce;
  }

  public void setCompanyIntroduce(String companyIntroduce) {
    this.companyIntroduce = companyIntroduce;
  }


  public Integer getCompanyStatus() {
    return companyStatus;
  }

  public void setCompanyStatus(Integer companyStatus) {
    this.companyStatus = companyStatus;
  }


  public Integer getRoleid() {
    return roleid;
  }

  public void setRoleid(Integer roleid) {
    this.roleid = roleid;
  }


  public Integer getIsdiscount() {
    return isdiscount;
  }

  public void setIsdiscount(Integer isdiscount) {
    this.isdiscount = isdiscount;
  }


  public Integer getDiscountnum() {
    return discountnum;
  }

  public void setDiscountnum(Integer discountnum) {
    this.discountnum = discountnum;
  }

}
