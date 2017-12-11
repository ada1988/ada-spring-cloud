package org.ada.cloud.client.remote;

import org.ada.cloud.client.remote.model.ProductRemoteView;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**  
 * Filename: IProductRemoteService.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月8日 <br>
 *
 *  
 */
@FeignClient(name="EUREKA-PROVIDER",fallback=ProductFallbackServiceImpl.class)
public interface IProductRemoteService {
	
	@RequestMapping("/rpc/product/queryProductById.do")
	public ProductRemoteView queryProductById(@RequestParam("id")Integer id) throws Exception;
}
