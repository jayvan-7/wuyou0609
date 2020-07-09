package com.zb.service;

import com.zb.pojo.EnterpriseUser;
import com.zb.util.PageUtil;

/**
 * @USER : LQY
 * @DATE : 2020/6/12 17:24
 */
public interface EnterpriseUserService {

    //账号登录
    EnterpriseUser loginEnterpriseUser(String name, String pwd);

    //手机登录
    EnterpriseUser phoneEnterpriseUserLogin(String userphone);


    //根据ID删除用户
    Integer deleteEnterpriseUserById(Integer id);

    //根据账号查询或者全部查询
    PageUtil<EnterpriseUser> getEnterpriseUserList(String name, Integer satrat, Integer size);
    //管理员修改
    Integer updateAdminenter(EnterpriseUser individualUser);
    //根据编号查询
    EnterpriseUser adminSerch(Integer id);

}
