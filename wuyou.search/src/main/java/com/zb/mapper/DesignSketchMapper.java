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
    
}
