package org.ada.cloud.redis.session.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**  
 * Filename: ShiroRedisProperties.java  <br>
 *
 * Description: shiro-redis 配置文件  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年8月21日 <br>
 *
 *  
 */
@ConfigurationProperties("ada.shiro.redis")
public class ShiroRedisProperties {
	public String host;
	public Integer port;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
}
