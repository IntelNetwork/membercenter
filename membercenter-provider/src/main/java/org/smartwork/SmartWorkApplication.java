package org.smartwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages={"org.forbes","org.smartwork"})
@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients(basePackages = {"org.forbes","org.smartwork"} )
public class SmartWorkApplication {

    /***
     * main方法概述:启动类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static void main(String[] args){
        SpringApplication.run(SmartWorkApplication.class,args);
    }
}
