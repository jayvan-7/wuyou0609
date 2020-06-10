package com.zb.vo;

import lombok.Data;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
@Data
public class CompanyPageForm {
    private String[] extendname;     //装修公司特色
    private String[] servicearea;        //服务区域
    private Integer start;          //当前页码
    private Integer size;           //每页显示几条数据
    private String ordertype;       //排序的方式
}
