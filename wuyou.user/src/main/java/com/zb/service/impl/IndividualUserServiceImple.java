package com.zb.service.impl;


import com.zb.mapper.IndividualUserMapper;
import com.zb.pojo.IndividualUser;
import com.zb.pojo.Power;
import com.zb.service.IndividualUserService;
import com.zb.service.PowerService;
import com.zb.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @USER : LQY
 * @DATE : 2020/6/11 15:27
 */

@Service
public class IndividualUserServiceImple implements IndividualUserService {
    @Autowired
    private IndividualUserMapper individualUserMapper;

    @Autowired
    private PowerService powerService;


    @Override
    public IndividualUser loginIndividualUser(String name, String pwd) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", name);
        map.put("password", pwd);
        try {
            IndividualUser individualUser = individualUserMapper.search(map);
            if (individualUser != null) {

                List<Power> powerAll = powerService.getPowerAll(individualUser.getRoleid());
                individualUser.setList(powerAll);


                return individualUser;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public IndividualUser phoneLogin(String userphone) {


        try {
            IndividualUser individualUser = individualUserMapper.phoneLogin(userphone);
            if (individualUser != null) {

                List<Power> powerAll = powerService.getPowerAll(individualUser.getRoleid());
                individualUser.setList(powerAll);


                return individualUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insertIndividualUser(IndividualUser individualUser) {
        try {
            return individualUserMapper.insertIndividualUser(individualUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateIndividualUser(IndividualUser individualUser) {
        try {
            return individualUserMapper.updateIndividualUser(individualUser);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Integer deleteIndividualUserById(Integer id) {

        try {
            return individualUserMapper.deleteIndividualUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageUtil<IndividualUser> getIndividualList(String name, Integer satrat, Integer size) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", name);
        map.put("beginPos",(satrat-1)*size);
        map.put("pageSize",size);
        try {
            List<IndividualUser> data = individualUserMapper.getIndividualUserListByMap(map);
            Integer count = individualUserMapper.getIndividualUserCountByMap(map);
            PageUtil<IndividualUser> pageUtil=new PageUtil<>();
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
    public Integer updateAdmindual(IndividualUser individualUser) {
        try {
            return individualUserMapper.updateIndividualUser(individualUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IndividualUser adminSerch(Integer id) {
        try {
            return individualUserMapper.getIndividualUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
