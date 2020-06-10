package com.zb.controller;

import com.zb.entity.DesignSketch;
import com.zb.service.DesignSketchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
@RestController
public class DesignSketchController {
    @Autowired
    private DesignSketchService designSketchService;

    //效果图搜索引擎
    @PostMapping(value = "/searchDesignES")
    public List<DesignSketch> searchDesign(@RequestParam("keyword") String keyword,
                                             @RequestParam("type") String type,
                                             @RequestParam("style") String style,
                                             @RequestParam("area") Double area,
                                             @RequestParam("index") Integer index, @RequestParam("size") Integer size)throws Exception{
        return designSketchService.SearchDesignES(keyword, type, style, area, index, size);
    }
}
