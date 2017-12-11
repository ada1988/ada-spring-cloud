package org.ada.cloud.eurake.provider.rpc;

import java.util.List;
import java.util.Map;

import org.ada.cloud.eurake.provider.rpc.model.ProductRemoteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public ProductRemoteView queryProductById(@RequestParam("id")Integer id) throws Exception {
		System.out.println("远程接口被调用RpcProductRemoteService.queryProductById.....");
		return new ProductRemoteView( "1", "九鼎投资基金", "MD-1028" );
	}
	
	
	@RequestMapping(value ="upload.do", method =RequestMethod.POST)
	public String handleFileUpload(@RequestPart(value ="file") MultipartFile file){
		System.out.println("远程接口被调用RpcProductRemoteService.handleFileUpload.....");
		//fastFDS 实现上传，并返回 url
		return "upload sucess";
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
