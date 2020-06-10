package com.zb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.tools.EsTool;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(value = "com.zb.mapper")
public class MySearchServerApp {
    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext context= SpringApplication.run(MySearchServerApp.class,args);

        //创建索引，并将数据库的数据导入es中
        /* EsTool esTool=context.getBean(EsTool.class);
        esTool.addIndex();
        esTool.importData();*/


    }


}
