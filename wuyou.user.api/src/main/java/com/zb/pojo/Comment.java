package com.zb.pojo;
import java.io.Serializable;

/***
*   
*/
public class Comment implements Serializable {
	//
	private Integer id;
	//此评论对应的公司id
	private Integer companyid;
	//此评论对应的记录id
	private Integer recordid;
	//评论内容
	private String content;
	//用户id
	private Integer userid;
	//用户评分
	private Integer score;
	//用户是否满意(1:满意  0:不满意)
	private Integer isok;
	//用户评论时间
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
