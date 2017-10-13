package org.ada.cloud.redis.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShiroRedisSessionApplication {
	public static void main(String[] args) {
		SpringApplication.run( ShiroRedisSessionApplication.class, args );
	}
}
