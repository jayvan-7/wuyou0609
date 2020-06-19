package com.zb.vo;

import com.zb.entity.*;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/19
 * @Version V1.0
 */
//返回给前台页面的封装对象，存储了装修公司详情页需要的所有数据
public class CompanyDetailForm {

    private EnterpriseUser enterpriseUser;  //装修公司
    private Integer designcaseCount;    //设计案例的数量
    private List<DesignCase> designCases; //用于首页展示的6个设计案例
    private Integer workteamCount;  //设计团队总人数
    private List<WorkteamBuilder>workteamBuilders;  //用于首页展示的5个设计师信息
    private Integer commentCount;  //评论总条数
    private List<Comment>comments;    //首页展示4条最近的评论

    public EnterpriseUser getEnterpriseUser() {
        return enterpriseUser;
    }

    public void setEnterpriseUser(EnterpriseUser enterpriseUser) {
        this.enterpriseUser = enterpriseUser;
    }

    public Integer getDesigncaseCount() {
        return designcaseCount;
    }

    public void setDesigncaseCount(Integer designcaseCount) {
        this.designcaseCount = designcaseCount;
    }

    public List<DesignCase> getDesignCases() {
        return designCases;
    }

    public void setDesignCases(List<DesignCase> designCases) {
        this.designCases = designCases;
    }

    public Integer getWorkteamCount() {
        return workteamCount;
    }

    public void setWorkteamCount(Integer workteamCount) {
        this.workteamCount = workteamCount;
    }

    public List<WorkteamBuilder> getWorkteamBuilders() {
        return workteamBuilders;
    }

    public void setWorkteamBuilders(List<WorkteamBuilder> workteamBuilders) {
        this.workteamBuilders = workteamBuilders;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
