package com.zb.controller;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.pojo.IndividualUser;
import com.zb.service.IndividualUserService;
import com.zb.service.TokenService;
import com.zb.service.UploadService;
import com.zb.userVo.UserTokenVo;
import com.zb.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @USER : LQY
 * @DATE : 2020/6/11 15:44
 */
@CrossOrigin
@RestController
public class IndividualUserController {
    @Autowired
    private IndividualUserService individualUserService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UploadService uploadService;

    @GetMapping("/loginIndividual")
    public Dto loginIndividual(@RequestParam("name") String name, @RequestParam("pwd") String pwd, HttpServletRequest request) {
        IndividualUser individualUser = individualUserService.loginIndividualUser(name, pwd);

        return Invoke(individualUser, request);
    }


    @GetMapping(value = "/individualInfo")
    public Dto userInfo(@RequestParam("token") String token) {
        IndividualUser individualUser = tokenService.getUserInfo(token);


        return DtoUtil.returnSuccess("sucess", individualUser);
    }

    @GetMapping("/phoneLogin")
    public Dto phoneLogin(String userPhone, HttpServletRequest request, String code) {


        IndividualUser individualUser = individualUserService.phoneLogin(userPhone);
        return Invoke(individualUser, request);

    }


    public Dto insertIndividualUser(IndividualUser individualUser, HttpServletRequest request) {
        Integer num = individualUserService.insertIndividualUser(individualUser);


        return DtoUtil.returnSuccess("注册成功");


    }


    //图片修改
    @PostMapping("/updateIndividualUser")
    public Dto updateIndividualUser(@RequestParam("token")String token, HttpServletRequest request, @RequestParam(required = false, value = "files") MultipartFile[] files) {

        IndividualUser individualUser = tokenService.getUserInfo(token);

        for (MultipartFile file : files) {

            if (Objects.isNull(file) || file.isEmpty()) {
                return DtoUtil.returnFail("文件不能为空", "400");
            }
            try {
                //根据时间戳创建文件名
                String fileName = System.currentTimeMillis() + file.getOriginalFilename();

                //创建文件的实际路径
                String destFileName = request.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;
                System.out.println(destFileName);
                //根据文件路径创建文件对应的实际文件
                File destFile = new File(destFileName);
                //创建文件实际路径
                destFile.getParentFile().mkdirs();
                //将文件传到对应的文件位置
                file.transferTo(destFile);


                Response response = uploadService.uploadFile(destFile);
                System.out.println(response);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                individualUser.setUserpic("http://qc1qpuwx8.bkt.clouddn.com/" + putRet.hash);
                System.out.println(individualUser.getUserpic());//这个就是从七牛云获取的文件名
                individualUserService.updateAdmindual(individualUser);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return DtoUtil.returnSuccess("上传成功");


    }


    public Dto Invoke(IndividualUser individualUser, HttpServletRequest request) {
        if (individualUser != null) {
            //获取设备的useragent
            String userAgent = request.getHeader("user-agent");
            String token = tokenService.generateToken(userAgent, individualUser);
            //封装返回给浏览器的数据模型
            UserTokenVo userTokenVo = new UserTokenVo();
            userTokenVo.setToken(token);
            userTokenVo.setGenTime(System.currentTimeMillis());
            userTokenVo.setExpTime(System.currentTimeMillis() * 2 * 60 * 60 * 1000);
            //存储到redis
            tokenService.saveRedis(token, individualUser);
            System.out.println("ss");
            return DtoUtil.returnSuccess("ok", userTokenVo);

        } else {
            return DtoUtil.returnFail("登录失败!账户名密码错误！", "1001");

        }
    }

    @GetMapping("/findPage")
    public PageUtil<IndividualUser> findPage(@RequestParam("name")String name, @RequestParam("satrat")Integer satrat, @RequestParam("size")Integer size) {
        return individualUserService.getIndividualList(name, satrat, size);
    }

    @PostMapping("deleteIndividualUserById/{id}")
    public Integer deleteIndividualUserById(@PathVariable("id") Integer id) {
        return individualUserService.deleteIndividualUserById(id);
    }

    @PostMapping("/updateAdmindual")
    public Integer updateAdmindual(IndividualUser individualUser) {
        return individualUserService.updateAdmindual(individualUser);
    }

    @GetMapping("/adminSerch/{id}")
    public IndividualUser adminSerch(@PathVariable("id") Integer id) {
        return individualUserService.adminSerch(id);
    }
}
