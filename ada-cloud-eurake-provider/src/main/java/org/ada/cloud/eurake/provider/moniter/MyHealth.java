package org.ada.cloud.eurake.provider.moniter;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**  
 * Filename: MyHealth.java  <br>
 *
 * Description:  自定义安全指标 <br>
 * 访问地址：项目根路径/health
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年12月20日 <br>
 *
 *  
 */
@Component
public class MyHealth implements HealthIndicator{

	@Override
	public Health health() {
		return Health.up().withDetail("prodject", "ada-cloud-eurake-provider").withDetail( "cpu-used", "100%" ).withDetail( "cpu-free", "10M" ).build();  
	}  

}
