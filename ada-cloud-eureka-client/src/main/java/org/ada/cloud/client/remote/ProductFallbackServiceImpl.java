package org.ada.cloud.client.remote;

import org.ada.cloud.client.remote.model.ProductRemoteView;
import org.springframework.stereotype.Component;

/**  
 * Filename: ProductFallbackServiceImpl.java  <br>
 *
 * Description: 远程服务调用  托底数据<br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月8日 <br>
 *
 *  
 */
@Component
public class ProductFallbackServiceImpl implements IProductRemoteService{

	@Override
	public ProductRemoteView queryProductById(Integer id) {
		System.out.println("本地容错接口被调用IProductFallbackService.queryProductById.....");
		return new ProductRemoteView( "1", "本地 托底数据", "MD0001" );
	}

}
