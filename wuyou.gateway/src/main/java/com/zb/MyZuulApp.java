package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
@SpringBootApplication
@EnableZuulProxy        //开启ZUUL组件功能
public class MyZuulApp {
    public static void main(String[] args) {
        SpringApplication.run(MyZuulApp.class,args);
    }
}
