package com.zb.service.impl;

import com.zb.mapper.AdminUserMapper;
import com.zb.pojo.AdminUser;
import com.zb.service.AdminUserServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @USER : LQY
 * @DATE : 2020/7/7 9:02
 */
@Service
public class AdminUserServieImple implements AdminUserServie {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Override
    public AdminUser loginAdminUser(String name, String pwd) {
        return adminUserMapper.loginAdminUser(name,pwd);
    }
}
