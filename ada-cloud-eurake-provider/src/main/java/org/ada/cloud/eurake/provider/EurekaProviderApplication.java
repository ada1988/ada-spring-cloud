package org.ada.cloud.eurake.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 注册两个服务，分别为：
 * 			/product/rpc/queryProductById.do  获取产品信息，通过id 
 *			/service-instances/{applicationName} 获取注册中心的指定项目名称的所有服务服务
 *
 * org.ada.cloud.eurake.provider.rpc
 *         该包下，为内网、系统间的调用，非外部
 */
@EnableDiscoveryClient
//@EnableEurekaClient //你的应用使用@EnableEurekaClient注解，那么只能使用eureka来发现服务实例
@SpringBootApplication(scanBasePackages="org.ada.cloud.eurake.provider.rpc")
public class EurekaProviderApplication 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(EurekaProviderApplication.class, args);
    }
}
