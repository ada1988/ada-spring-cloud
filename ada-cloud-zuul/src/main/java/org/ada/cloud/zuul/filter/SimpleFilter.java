package org.ada.cloud.zuul.filter;
import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**  
 * Filename: SimpleFilter.java  <br>
 *
 * Description: 前置简易拦截器  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年5月24日 <br>
 *
 *  
 */

public class SimpleFilter extends ZuulFilter {

	private static Logger	log	= LoggerFactory.getLogger( SimpleFilter.class );

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info( String.format( "%s request to %s", request.getMethod(), request.getRequestURL().toString() ) );

		return null;
	}

}
