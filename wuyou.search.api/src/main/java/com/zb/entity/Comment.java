package com.zb.entity;


public class Comment {

  private Integer id;
  private Integer companyid;
  private Integer recordid;
  private String content;
  private Integer userid;
  private Integer score;
  private Integer isok;
  private String commenttime;


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


  public Integer getRecordid() {
    return recordid;
  }

  public void setRecordid(Integer recordid) {
    this.recordid = recordid;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public Integer getUserid() {
    return userid;
  }

  public void setUserid(Integer userid) {
    this.userid = userid;
  }


  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }


  public Integer getIsok() {
    return isok;
  }

  public void setIsok(Integer isok) {
    this.isok = isok;
  }


  public String getCommenttime() {
    return commenttime;
  }

  public void setCommenttime(String commenttime) {
    this.commenttime = commenttime;
  }

}
