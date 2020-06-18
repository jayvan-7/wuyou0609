package com.zb.service.impl;

import com.zb.entity.DesignCase;
import com.zb.mapper.DesignCaseMapper;
import com.zb.service.DesignCaseService;
import com.zb.vo.DesignCaseForm;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/17
 * @Version V1.0
 */
@Service
public class DesignCaseServiceImpl implements DesignCaseService  {
    @Autowired(required = false)
    private DesignCaseMapper designCaseMapper;

    //查某家企业所有的设计案例，将其存到redis中，进行分页展示
    public List<DesignCase>findDesignPage(Integer companyid, Integer index,Integer size){
        List<DesignCase>designCases=designCaseMapper.findDesignAll(companyid);
        return  designCases;
    }

    //仅用于第一个页面展示6条数据用
    public DesignCaseForm ShowDesign(Integer companyid){
        List<DesignCase>designCases= designCaseMapper.findDesignShowSix(companyid);
        int count=designCaseMapper.findDesignCount(companyid);
        //把数据封装进前台页面需要的对象中，避免服务器与客户端产生过多的交互
        DesignCaseForm designCaseForm=new DesignCaseForm();
        designCaseForm.setDesignCases(designCases);
        designCaseForm.setCount(count);
        return designCaseForm;
    }



}
