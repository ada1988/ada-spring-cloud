package org.ada.cloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @EnableDiscoveryClient 启用 服务发现 的配置
 * @EnableFeignClients 启动Feign客户端
 * @author: CZD  
 * @Createtime: 2017年6月8日
 */
@EnableDiscoveryClient
@EnableEurekaClient //你的应用使用@EnableEurekaClient注解，那么只能使用eureka来发现服务实例
@EnableFeignClients
@SpringBootApplication
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}
