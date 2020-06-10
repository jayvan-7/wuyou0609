package com.zb.fegin;


import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("orderserver")
public interface OrderFeginClient {

}
