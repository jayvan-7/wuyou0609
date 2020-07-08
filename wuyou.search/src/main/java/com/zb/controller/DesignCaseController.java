package com.zb.controller;

import com.zb.service.impl.DesignCaseServiceImpl;
import com.zb.vo.DesignCaseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.dc.pr.PRError;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/18
 * @Version V1.0
 */
@RestController
public class DesignCaseController {
    @Autowired
    private DesignCaseServiceImpl designCaseService;

    //仅用于第一个页面展示6条数据用
    @GetMapping(value = "/ShowDesign")
    public DesignCaseForm ShowDesign(Integer companyid){
        return designCaseService.ShowDesign(companyid);
    }

}
