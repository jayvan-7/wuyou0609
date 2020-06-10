package com.zb.service;

import com.zb.entity.DesignSketch;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/9
 * @Version V1.0
 */
public interface DesignSketchService {

    /**
     *
     * @param keyword
     * @param type
     * @param style
     * @param area
     * @param index
     * @param size
     * @return
     * @throws Exception
     */
    public List<DesignSketch>SearchDesignES(String keyword,
                                            String type,
                                            String style,
                                            Double area,
                                            Integer index,
                                            Integer size
    ) throws Exception;

}
