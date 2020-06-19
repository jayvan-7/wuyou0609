package com.zb.service;

import com.zb.entity.EnterpriseUser;
import com.zb.util.PageUtil;
import com.zb.vo.CompanyDetailForm;
import com.zb.vo.CompanyPageForm;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
public interface EnterpriseUserService {

   /**
    *对装修公司进行多条件查询，多种排序
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

   //根据id查对应装修公司的详情
   public EnterpriseUser findCompanyByid(Integer id);

   //查询全部装修公司，用于存进redis
   public List<EnterpriseUser> findCompanyAll();


   public CompanyDetailForm findCompannyDetailByid(Integer id);
}
