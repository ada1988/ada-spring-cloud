package org.ada.cloud.eurake.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 注册两个服务，分别为：
 * 			/product/rpc/queryProductById.do  获取产品信息，通过id 
 *			/service-instances/{applicationName} 获取注册中心的指定项目名称的所有服务服务
 *
 * org.ada.cloud.eurake.provider.rpc
 *         该包下，为内网、系统间的调用，非外部
 */
@SpringBootApplication(scanBasePackages="org.ada.cloud.eurake.provider.rpc")
@EnableEurekaClient //你的应用使用@EnableEurekaClient注解，那么只能使用eureka来发现服务实例
public class EurekaProviderApplication 
{
    public static void main( String[] args )
    {
    	ConfigurableApplicationContext context = SpringApplication.run(EurekaProviderApplication.class, args);
    	context.getEnvironment();
    }
    
    /**
     * 由于开启了spring.profiles.active=dev
     * 
     * 打印svn配置中心，下载的eureka-provider-dev.properties 配置文件
     * 
     * svn://192.168.127.129/repository/config/trunk/eureka-provider-dev.properties
     * 
     * @param environment
     * @author: CZD  
     * @Createtime: 2017年6月14日
     */
    public static void printSvnProperties(ConfigurableEnvironment environment){
    	System.out.println(">>>>>>>>>>>>>"+environment.getProperty( "eureka.client.serviceUrl.defaultZone" ));
    }
}
