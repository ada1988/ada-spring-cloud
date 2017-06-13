package org.ada.cloud.client.controller;

import org.ada.cloud.client.remote.IProductRemoteService;
import org.ada.cloud.client.remote.model.ProductRemoteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Filename: ClientController.java <br>
 *
 * Description: 客户端发现 <br>
 * 
 * @author: CZD <br>
 * @version: 1.0 <br>
 * @Createtime: 2017年6月8日 <br>
 *
 * 
 */
@Controller
public class ClientController {

	@Autowired
	private IProductRemoteService	productRemoteService;

	@RequestMapping("/client/product/{id}")
	@ResponseBody
	public ProductRemoteView clientProductById(@PathVariable Integer id) {
		ProductRemoteView product = productRemoteService.queryProductById( id );
		return product;
	}
}
