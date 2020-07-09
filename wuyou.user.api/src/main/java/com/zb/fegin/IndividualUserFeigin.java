package com.zb.fegin;

import com.zb.dto.Dto;
import com.zb.fegin.imple.IndividualUserFeiginImple;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @USER : LQY
 * @DATE : 2020/6/17 17:30
 */

@FeignClient(value = "userserver",fallback =IndividualUserFeiginImple.class)
public interface IndividualUserFeigin {

    @GetMapping(value = "/individualInfo")
    public Dto userInfo(@RequestParam("token")String token);

}
