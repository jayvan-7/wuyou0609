package com.zb.service.impl;

import com.zb.mapper.EnterpriseUserMapper;
import com.zb.pojo.EnterpriseUser;
import com.zb.pojo.IndividualUser;
import com.zb.service.EnterpriseUserService;
import com.zb.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @USER : LQY
 * @DATE : 2020/6/12 17:26s
 */
@Service
public class EnterpriseUserImple implements EnterpriseUserService {
    @Autowired
    private EnterpriseUserMapper enterpriseUserMapper;
    @Override
    public EnterpriseUser loginEnterpriseUser(String name, String pwd) {



        Map<String,Object> map=new HashMap<>();
        map.put("company_username",name);
        map.put("password",pwd);
        try {
            EnterpriseUser enterpriseUser = enterpriseUserMapper.search(map);
            return enterpriseUser;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public EnterpriseUser phoneEnterpriseUserLogin(String userphone) {
        try {
            return enterpriseUserMapper.phoneLogin(userphone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer deleteEnterpriseUserById(Integer id) {
        try {
            return enterpriseUserMapper.deleteEnterpriseUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public PageUtil<EnterpriseUser> getEnterpriseUserList(String name, Integer satrat, Integer size) {
        Map<String, Object> map = new HashMap<>();
        map.put("company_username", name);
        map.put("beginPos",(satrat-1)*size);
        map.put("pageSize",size);
        try {
            List<EnterpriseUser> data = enterpriseUserMapper.getEnterpriseUserListByMap(map);
            Integer count = enterpriseUserMapper.getEnterpriseUserCountByMap(map);
            PageUtil<EnterpriseUser> pageUtil=new PageUtil<>();
            pageUtil.setData(data);
            pageUtil.setPagesize(size);
            pageUtil.setPageindex(satrat);
            pageUtil.setTotalpagecount(count);
            return  pageUtil;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer updateAdminenter(EnterpriseUser enterpriseUser) {
        try {
       return      enterpriseUserMapper.updateEnterpriseUser(enterpriseUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public EnterpriseUser adminSerch(Integer id) {
        try {
            return enterpriseUserMapper.getEnterpriseUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
