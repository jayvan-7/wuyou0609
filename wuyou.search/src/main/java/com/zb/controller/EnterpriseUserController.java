package com.zb.controller;

import com.zb.entity.EnterpriseUser;
import com.zb.service.EnterpriseUserService;
import com.zb.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/findCompanyPage")
    public PageUtil<EnterpriseUser> findCompanyPage(String[] extendname,
                                                    String[] servicearea,
                                                    Integer index,
                                                    Integer size,
                                                    String ordertype) {
        return enterpriseUserService.findCompanyPage(extendname, servicearea, index, size, ordertype);
    }
}
