package com.zb.service;

import com.zb.entity.EnterpriseUser;
import com.zb.util.PageUtil;
import com.zb.vo.CompanyPageForm;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
public interface EnterpriseUserService {

   /**
    *
    *
    * @param extendname
    * @param servicearea
    * @param index
    * @param size
    * @param ordertype
    * @return
    */
   public PageUtil<EnterpriseUser> findCompanyPage(String[] extendname,
                                                   String[] servicearea,
                                                   Integer index,
                                                   Integer size,
                                                   String ordertype);

}
