package com.zb.mapper;

import com.zb.entity.EnterpriseUser;
import com.zb.vo.CompanyPageForm;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
public interface EnterpriseUserMapper {

    //查询装修公司（组合查询+分页显示）
    public List<EnterpriseUser> findCompanyPage(CompanyPageForm companyPageForm);

    //装修公司的记录数
    public int findCount(CompanyPageForm companyPageForm);


    //根据id查对应装修公司的详情
    public EnterpriseUser findCompanyByid(Integer id);

    //查询全部装修公司，用于存进redis
    public List<EnterpriseUser>findCompanyAll();
}
