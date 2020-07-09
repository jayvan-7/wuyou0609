package com.zb.pojo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
*   
*/
public class IndividualUser implements Serializable {
	//普通用户编号
	private Integer id;
	//用户名（登陆用）
	private String username;
	//用户登录密码
	private String password;
	//用户手机号
	private String userphone;
	//用户昵称
	private String usernickname;
	//用户性别
	private char sex;
	private String useraddress;
	private String userpic;
	private Integer roleid;

	private List<Power>list;

	public List<Power> getList() {
		return list;
	}

	public void setList(List<Power> list) {
		this.list = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUsernickname() {
		return usernickname;
	}

	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public String getUserpic() {
		return userpic;
	}

	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
}