package org.ada.cloud.client.remote.model;

import java.io.Serializable;

/**  
 * Filename: ProductRemoteView.java  <br>
 *
 * Description: 远程过程调用对象  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月8日 <br>
 *
 *  
 */

public class ProductRemoteView  implements Serializable{
	private static final long serialVersionUID = 331L;
	private String id;
	private String name;
	private String code;
	
	
	public ProductRemoteView(String id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}

