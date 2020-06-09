package com.zb.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil<T> {
	//当前页码
	private Integer pageindex;
	//每页容量
	private Integer pagesize;
	//数据总行数
	private Integer totalNewscount;
	//总页数
	private Integer totalpagecount;
	//分页数据
	private List<T> data=new ArrayList<T>();
	
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getPageindex() {
		return pageindex;
	}

	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getTotalNewscount() {
		return totalNewscount;
	}

	public void setTotalNewscount(Integer totalNewscount) {
		this.totalNewscount = totalNewscount;
	}
	//根据总记录数和每页容量可 算得总页数
	public Integer getTotalpagecount() {
		totalpagecount=this.totalNewscount%this.pagesize==0?this.totalNewscount/this.pagesize:this.totalNewscount/this.pagesize+1;
		return totalpagecount;
	}
	
	public void setTotalpagecount(Integer totalpagecount) {
		this.totalpagecount = totalpagecount;
	}

	public PageUtil() {
		super();
	}

}
