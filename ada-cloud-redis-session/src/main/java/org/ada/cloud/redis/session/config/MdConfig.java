package org.ada.cloud.redis.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**  
 * Filename: MdConfig.java  <br>
 *
 * Description: redis-session config  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年5月31日 <br>
 *
 *  
 */
@EnableRedisHttpSession
public class MdConfig {
	@Bean
    public LettuceConnectionFactory connectionFactory() {
            return new LettuceConnectionFactory(); 
    }
}
