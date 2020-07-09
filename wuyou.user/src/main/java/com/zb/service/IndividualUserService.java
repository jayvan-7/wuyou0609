package com.zb.service;

import com.zb.pojo.EnterpriseUser;
import com.zb.pojo.IndividualUser;
import com.zb.util.PageUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @USER : LQY
 * @DATE : 2020/6/11 15:25
 */
public interface IndividualUserService {
    //账号登录
    IndividualUser loginIndividualUser(String name, String pwd);

    //手机登录
    IndividualUser phoneLogin(String userphone);


   //注册

    Integer insertIndividualUser(IndividualUser individualUser);

   //头像更换密码修改
   Integer updateIndividualUser(IndividualUser individualUser);
   //根据ID删除用户
   Integer deleteIndividualUserById(Integer id);

 //根据账号查询或者全部查询
 PageUtil<IndividualUser> getIndividualList(String name, Integer satrat, Integer size);
 //管理员修改
 Integer updateAdmindual(IndividualUser individualUser);
 //根据编号查询
 IndividualUser adminSerch(Integer id);

}
