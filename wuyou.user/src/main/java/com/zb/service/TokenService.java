package com.zb.service;


import com.zb.pojo.EnterpriseUser;
import com.zb.pojo.IndividualUser;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
public interface TokenService {
    public String generateToken(String userAgent, IndividualUser individualUser);
    public String generateToken(String userAgent, EnterpriseUser enterpriseUser);

    public void saveRedis(String token, IndividualUser individualUser);
    public void saveRedis(String token, EnterpriseUser enterpriseUser);

    public IndividualUser getUserInfo(String token);
    public EnterpriseUser getUserInfoEnterpriseUser(String token);
}
