package org.ada.cloud.zuul.filter;

import javax.servlet.http.HttpServletResponse;

import org.ada.cloud.zuul.constants.EFilterTypeConstants;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * Filename: RateLimitZuulFilter.java <br>
 *
 * Description:单节点Zuul下的限流 <br>
 * 
 * 分布式：
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年12月10日 <br>
 *
 * @copy http://mp.weixin.qq.com/s/mh4BFWOsXakrn17XvahszA
 * 
 */

public class RateLimitZuulFilter extends ZuulFilter {
	private final RateLimiter	rateLimiter	= RateLimiter.create( 1000.0 );

	@Override
	public String filterType() {
		return EFilterTypeConstants.前置.getValue();
	}

	@Override
	public int filterOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

	@Override
	public boolean shouldFilter() {
		// 这里可以考虑弄个限流开启的开关，开启限流返回true，关闭限流返回false，你懂的。
		return true;
	}

	@Override
	public Object run()
	{
		try{
			RequestContext currentContext =  RequestContext.getCurrentContext();
			HttpServletResponse response = currentContext.getResponse();
			if(!rateLimiter.tryAcquire()){
				HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
				response.setContentType( MediaType.TEXT_PLAIN_VALUE );
				response.setStatus( httpStatus.value() );
				response.getWriter().append( httpStatus.getReasonPhrase() );
				currentContext.setSendZuulResponse( false );
				throw new ZuulException( httpStatus.getReasonPhrase(), httpStatus.value(), httpStatus.getReasonPhrase());
			}
		}catch(Exception e){
			ReflectionUtils.rethrowRuntimeException( e );
		}
		return null;
	}
}
