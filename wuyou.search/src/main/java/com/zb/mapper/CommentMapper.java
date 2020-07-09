package com.zb.mapper;

import com.zb.entity.Comment;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/19
 * @Version V1.0
 */
public interface CommentMapper {

    //某家公司有多少条评论
    public int findCommentCount(Integer companyid);

    //首页仅展示最新四条评论
    public List<Comment>findCommentFour(Integer companyid);

}
