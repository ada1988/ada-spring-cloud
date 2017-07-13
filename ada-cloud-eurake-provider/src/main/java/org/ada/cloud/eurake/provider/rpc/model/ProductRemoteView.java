package org.ada.cloud.eurake.provider.rpc.model;

import java.io.Serializable;

/**  
 * Filename: ProductBean.java  <br>
 *
 * Description:  产品视图实体  , 本视图，不对外提供 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月8日 <br>
 *
 *  
 */

public class ProductRemoteView implements Serializable{
	private static final long serialVersionUID = 331L;
	private String id;
	private String name;
	private String code;
	
	/**
	 * 必须存在，否则无法进行序列化
	 */
	public ProductRemoteView() {
		super();
	}
	
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
