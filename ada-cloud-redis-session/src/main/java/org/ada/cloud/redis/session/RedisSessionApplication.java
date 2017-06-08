package org.ada.cloud.redis.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RedisSessionApplication 
{
	  public static void main(String[] args) {
		    SpringApplication.run(RedisSessionApplication.class, args);
		  }
}
