package com.zb.fegin;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/7/8
 * @Version V1.0
 */
@FeignClient("qgserver")
public interface PayFeginClint {
    @RequestMapping(value = "/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
