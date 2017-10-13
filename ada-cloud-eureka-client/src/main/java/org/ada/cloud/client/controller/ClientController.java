package org.ada.cloud.client.controller;

import org.ada.cloud.client.remote.IProductRemoteService;
import org.ada.cloud.client.remote.model.ProductRemoteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
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

	@Autowired
	private Tracer tracer;
	@RequestMapping("/client/product/{id}")
	@ResponseBody
	public ProductRemoteView clientProductById(@PathVariable Integer id) {
		ProductRemoteView product = null;
		// 创建一个 span
		final Span span = tracer.createSpan("3rd_service");
		try {
		  span.tag(Span.SPAN_LOCAL_COMPONENT_TAG_NAME, "3rd_service");
		  span.logEvent(Span.CLIENT_SEND);
		  // 这里时调用第三方 API 的代码
		  product = productRemoteService.queryProductById( id );
		} finally {
			span.tag(Span.SPAN_PEER_SERVICE_TAG_NAME, "3rd_service");
			span.logEvent(Span.CLIENT_RECV);
			tracer.close(span);
		}
		return product;
	}
}
