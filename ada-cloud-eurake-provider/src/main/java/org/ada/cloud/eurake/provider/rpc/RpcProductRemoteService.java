package org.ada.cloud.eurake.provider.rpc;

import java.util.List;
import java.util.Map;

import org.ada.cloud.eurake.provider.rpc.model.ProductRemoteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**  
 * Filename: ProductController.java  <br>
 *
 * Description: 产品中心，注册产品服务  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月8日 <br>
 *
 *  
 */
@RestController
@RequestMapping("/rpc/product/")
public class RpcProductRemoteService {
	
	@RequestMapping("queryProductById.do")
	public ProductRemoteView queryProductById(Integer id) throws Exception {
		System.out.println("远程接口被调用RpcProductRemoteService.queryProductById.....");
		return new ProductRemoteView( "1", "九鼎投资基金", "MD-1028" );
	}
	
	@Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
    	List<String> services = discoveryClient.getServices();
    	for(String name : services){System.out.println("discovers::::>>>>>>>>>>>>>>>>>>"+name);}
    	Map<String, String> map = discoveryClient.getLocalServiceInstance().getMetadata();
    	for(String key:map.keySet()){
    		System.out.println(key+":"+map.get( key ));
    	}
    	return this.discoveryClient.getInstances(applicationName);
    }
    
}
