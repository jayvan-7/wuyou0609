package com.zb.entity;


public class WorkteamBuilder {

  private Integer id;
  private Integer belongteamid;
  private String buildername;
  private String builderrank;
  private Integer builderage;
  private String builderimg;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getBelongteamid() {
    return belongteamid;
  }

  public void setBelongteamid(Integer belongteamid) {
    this.belongteamid = belongteamid;
  }


  public String getBuildername() {
    return buildername;
  }

  public void setBuildername(String buildername) {
    this.buildername = buildername;
  }


  public String getBuilderrank() {
    return builderrank;
  }

  public void setBuilderrank(String builderrank) {
    this.builderrank = builderrank;
  }


  public Integer getBuilderage() {
    return builderage;
  }

  public void setBuilderage(Integer builderage) {
    this.builderage = builderage;
  }


  public String getBuilderimg() {
    return builderimg;
  }

  public void setBuilderimg(String builderimg) {
    this.builderimg = builderimg;
  }

}
