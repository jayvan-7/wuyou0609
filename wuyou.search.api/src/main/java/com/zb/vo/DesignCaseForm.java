package com.zb.vo;

import com.zb.entity.DesignCase;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/18
 * @Version V1.0
 */
//前端页面需要的数据
public class DesignCaseForm {
    private List<DesignCase> designCases;   //设计案例详情
    private Integer count;      //一共有多少个设计案例

    public List<DesignCase> getDesignCases() {
        return designCases;
    }

    public void setDesignCases(List<DesignCase> designCases) {
        this.designCases = designCases;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
