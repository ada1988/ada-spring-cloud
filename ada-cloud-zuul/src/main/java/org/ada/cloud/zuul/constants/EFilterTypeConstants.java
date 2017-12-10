package org.ada.cloud.zuul.constants;


/**  
 * Filename: FilterConstants.java  <br>
 *
 * Description:   <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年12月10日 <br>
 *
 *  
 */

public enum EFilterTypeConstants {
	前置("前置","pre"),
	环绕("环绕","routing"),
	后置("后置","post");
	private String name;
	private String value;
	
	public static EFilterTypeConstants get(String value){
		EFilterTypeConstants[] types = EFilterTypeConstants.values();
		for(EFilterTypeConstants type : types){
			if(type.value.equals(value)){
				return type;
			}
		}
		return null;
	}
	
	private EFilterTypeConstants(String name,String value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
