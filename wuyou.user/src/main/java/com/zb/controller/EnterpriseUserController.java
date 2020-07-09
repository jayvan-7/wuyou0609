package com.zb.controller;

import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.pojo.EnterpriseUser;
import com.zb.service.TokenService;
import com.zb.service.impl.EnterpriseUserImple;
import com.zb.userVo.UserTokenVo;
import com.zb.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @USER : LQY
 * @DATE : 2020/6/12 17:35
 */
@CrossOrigin
@RestController
public class EnterpriseUserController {

    @Autowired
    private EnterpriseUserImple enterpriseUserImple;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/loginIndividuals")
    public Dto loginIndividual(@RequestParam("name") String name, @RequestParam("pwd") String pwd, HttpServletRequest request) {
        EnterpriseUser enterpriseUser = enterpriseUserImple.loginEnterpriseUser(name, pwd);

        return Invoke(enterpriseUser, request);
    }


    @GetMapping(value = "/individualInfos")
    public Dto userInfo(@RequestParam("token") String token) {
        EnterpriseUser enterpriseUser = tokenService.getUserInfoEnterpriseUser(token);


        return DtoUtil.returnSuccess("sucess", enterpriseUser);
    }

    @GetMapping("/phoneLogins")
    public Dto phoneLogin(@RequestParam("userPhone") String userPhone, HttpServletRequest request, @RequestParam("code") String code) {
        EnterpriseUser enterpriseUser = enterpriseUserImple.phoneEnterpriseUserLogin(userPhone);
        if (enterpriseUser == null || enterpriseUser.getCompanyStatus() != 1) {
            Dto dto = new Dto();
            dto.setMsg("登录失败，手机号未注册");
            dto.setErrorCode("4001");
            return dto;
        } else {
            return Invoke(enterpriseUser, request);
        }


    }


    public Dto Invoke(EnterpriseUser enterpriseUser, HttpServletRequest request) {
        if (enterpriseUser != null) {
            //获取设备的useragent
            String userAgent = request.getHeader("user-agent");
            String token = tokenService.generateToken(userAgent, enterpriseUser);
            //封装返回给浏览器的数据模型
            UserTokenVo userTokenVo = new UserTokenVo();
            userTokenVo.setToken(token);
            userTokenVo.setGenTime(System.currentTimeMillis());
            userTokenVo.setExpTime(System.currentTimeMillis() * 2 * 60 * 60 * 1000);
            //存储到redis
            tokenService.saveRedis(token, enterpriseUser);
            return DtoUtil.returnSuccess("ok", userTokenVo);

        } else {
            return DtoUtil.returnFail("登录失败!", "1001");
        }
    }

    @GetMapping("/deleteEnterprise")
    public Integer deleteEnterprise(Integer id) {
        return enterpriseUserImple.deleteEnterpriseUserById(id);
    }

    @GetMapping("/pageUserList")
    public PageUtil<EnterpriseUser> pageUserList(String name, Integer satrat, Integer size) {
        return enterpriseUserImple.getEnterpriseUserList(name, satrat, size);


    }


    @PostMapping("/enterpriseUser")
    public Integer updateAdminenter(EnterpriseUser enterpriseUser) {
        return enterpriseUserImple.updateAdminenter(enterpriseUser);
    }

    @GetMapping("/adminSerchId")
    public EnterpriseUser adminSerchId(Integer id) {
        return enterpriseUserImple.adminSerch(id);
    }
}
