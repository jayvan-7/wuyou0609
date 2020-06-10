package com.zb.mapper;

import com.zb.entity.DesignSketch;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/9
 * @Version V1.0
 */
public interface DesignSketchMapper {

    //查询全部效果图，用于存到es中
    public List<DesignSketch>findDesignAll();

    //根据效果图id查看这条记录的详情，即查看全部的效果图
    public DesignSketch findDesignByid(Integer id);
    
}
