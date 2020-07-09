package com.zb.controller;

import com.zb.pojo.AdminUser;
import com.zb.service.AdminUserServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER : LQY
 * @DATE : 2020/7/7 9:06
 */
@RestController
public class AdminUserController {
    @Autowired
    private AdminUserServie adminUserServie;
    @GetMapping("/loginAdminUser")
    public AdminUser loginAdminUser(@RequestParam("name") String name, @RequestParam("pwd")String pwd){
        return adminUserServie.loginAdminUser(name,pwd);
    }

}
