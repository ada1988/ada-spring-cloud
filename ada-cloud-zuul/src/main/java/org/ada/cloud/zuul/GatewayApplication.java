package org.ada.cloud.zuul;

import org.ada.cloud.zuul.filter.ErrorFilter;
import org.ada.cloud.zuul.filter.SimpleFilter;
import org.ada.cloud.zuul.filter.ThrowExceptionFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringCloudApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run( GatewayApplication.class, args );
	}

	
	@Bean	
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	
	@Bean	
	public ThrowExceptionFilter throwExceptionFilter() {
		return new ThrowExceptionFilter();
	}
	
	@Bean	
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}
}