package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.pojo.EnterpriseUser;
import com.zb.pojo.IndividualUser;
import com.zb.service.TokenService;
import com.zb.util.MD5;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String generateToken(String userAgent, IndividualUser individualUser) {
        StringBuffer sbf = new StringBuffer("token:");
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        if (agent.getOperatingSystem().isMobileDevice()) {        //移动端设备
            sbf.append("MOBILE-");
        } else {//pc
            sbf.append("PC-");
        }
        sbf.append(MD5.getMd5(individualUser.getUsername(), 32) + "-");
        sbf.append(individualUser.getId() + "-");
        sbf.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-");
        sbf.append(MD5.getMd5(userAgent, 6));
        return sbf.toString();
    }

    @Override
    public String generateToken(String userAgent, EnterpriseUser enterpriseUser) {
        return null;
    }

    @Override
    public void saveRedis(String token, IndividualUser individualUser) {
        if (token.startsWith("token:MOBILE-")) {
            redisTemplate.opsForValue().set(token, JSON.toJSONString(individualUser));
        } else {
            redisTemplate.opsForValue().set(token, JSON.toJSONString(individualUser), 60 * 60 * 2, TimeUnit.SECONDS);
        }
    }

    @Override
    public void saveRedis(String token, EnterpriseUser enterpriseUser) {

    }




    @Override
    public IndividualUser getUserInfo(String token) {
        if (redisTemplate.hasKey(token)) {
            String json = redisTemplate.
                    opsForValue().get(token).toString();
            IndividualUser individualUser = JSON.parseObject(json,IndividualUser.class);
            return individualUser;
        }
        return null;
    }

    @Override
    public EnterpriseUser getUserInfoEnterpriseUser(String token) {
        return null;
    }


}
