package com.zb;


import com.zb.tools.CanalTools;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(value = "com.zb.mapper")
public class MySearchServerApp {
    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext context= SpringApplication.run(MySearchServerApp.class,args);

        //创建索引，并将数据库的数据导入es中
         /*EsTool esTool=context.getBean(EsTool.class);
        esTool.addIndex();
        esTool.importData();*/

        //开启Canal，完成es与db的数据同步
        CanalTools canalTools = context.getBean(CanalTools.class);
        canalTools.execution();
    }

    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
