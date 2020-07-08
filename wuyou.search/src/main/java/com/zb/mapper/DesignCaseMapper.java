package com.zb.mapper;

import com.zb.entity.DesignCase;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/17
 * @Version V1.0
 */
public interface DesignCaseMapper {

    //公司旗下一共有多少个设计案例
    public int findDesignCount(Integer companyid);

    //查询公司旗下的全部设计案例（用来存进redis进行分页显示）
    public List<DesignCase>findDesignAll(Integer companyid);

    //在首页仅展示6个设计案例
    public List<DesignCase>findDesignShowSix(Integer companyid);



}
