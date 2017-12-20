package org.ada.cloud.eurake.provider.business.service.impl;

import org.ada.cloud.eurake.provider.business.service.IProductService;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**  
 * Filename: ProductServiceImpl.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年12月20日 <br>
 *
 *  
 */
@Service("productService")
public class ProductServiceImpl implements IProductService{

	@Override
	@HystrixCommand(groupKey="product", 
			commandKey = "queryProductNameById",
			fallbackMethod="fallback",
			commandProperties = {
	            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
	        },
	            threadPoolProperties = {
	                        @HystrixProperty(name = "coreSize", value = "30"),
	                        @HystrixProperty(name = "maxQueueSize", value = "101"),
	                        @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
	                        @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
	                        @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
	                        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
	        })
	public String queryProductNameById(Integer productId) {
		return "基金产品（110026）";
	}
	
	public String fallback(){
        return "org.ada.cloud.eurake.provider.business.service.impl.ProductServiceImpl#queryProductNameById:some exception occur call fallback method.";
    }

}
