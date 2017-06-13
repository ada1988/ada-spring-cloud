package org.ada.cloud.eurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * @EnableEurekaServer 启用 服务注册、发现中心 的配置
 * 
 * 通过@EnableEurekaServer注解启动一个服务注册中心提供给其他应用进行对话
 * @author: CZD  
 * @Createtime: 2017年6月8日
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
