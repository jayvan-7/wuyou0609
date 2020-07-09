package com.zb.controller;

import com.zb.entity.EnterpriseUser;
import com.zb.service.EnterpriseUserService;
import com.zb.util.PageUtil;
import com.zb.vo.CompanyDetailForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
@RestController
public class EnterpriseUserController {
    @Autowired
    private EnterpriseUserService enterpriseUserService;

    //分页+组合查询，装修公司查询页面
    @PostMapping(value = "/findCompanyPage")
    public PageUtil<EnterpriseUser> findCompanyPage(String[] extendname,
                                                    String[] servicearea,
                                                    Integer index,
                                                    Integer size,
                                                    String ordertype) {
        return enterpriseUserService.findCompanyPage(extendname, servicearea, index, size, ordertype);
    }

    //按id查询
    @GetMapping(value = "/findCompanyByid")
    public EnterpriseUser findCompanyByid(Integer id) {
        return enterpriseUserService.findCompanyByid(id);
    }

    //将全部装修公司信息存进redis
    @GetMapping(value = "/findCompanyAll")
    public List<EnterpriseUser> findCompanyAll() {
        return enterpriseUserService.findCompanyAll();
    }

    //查看装修公司详情（包含全部页面信息）
    @GetMapping(value = "/findCompannyDetailByid")
    public CompanyDetailForm findCompannyDetailByid(Integer id){
        return enterpriseUserService.findCompannyDetailByid(id);
    }
}
