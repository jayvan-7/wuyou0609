package com.zb.fegin;

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
