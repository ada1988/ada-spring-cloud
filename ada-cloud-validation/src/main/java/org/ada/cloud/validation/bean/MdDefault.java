package org.ada.cloud.validation.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.ada.cloud.validation.base.IntParamNotNullDefault;
import org.ada.cloud.validation.base.MobileParamNotNullDefault;
import org.ada.cloud.validation.base.StringParamNotNullDefault;
import org.hibernate.validator.constraints.NotBlank;

/**  
 * Filename: UserInfo.java  <br>
 *
 * Description:  用户信息 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月6日 <br>
 *
 * @NotNull 和 @NotEmpty  和@NotBlank 区别

		@NotEmpty 用在集合类上面
		@NotBlank 用在String上面
		@NotNull  用在基本类型上
 *  
 */

public class MdDefault {
	
	@NotNull(message = "数字类型参数  may be empty", groups = IntParamNotNullDefault.class)  
	private Integer intParam;
	
	@NotBlank(message = "字符类型参数 may be empty", groups = StringParamNotNullDefault.class)  
	private String stringParam;
	/**
	 * 组合使用
	 */
	@NotBlank(message = "手机号参数 may be empty", groups = MobileParamNotNullDefault.class) 
	@Pattern(message="手机号不符合规则",regexp="^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$",groups = MobileParamNotNullDefault.class)
	private String mobileParam;
	
	public Integer getIntParam() {
		return intParam;
	}
	public void setIntParam(Integer intParam) {
		this.intParam = intParam;
	}
	public String getStringParam() {
		return stringParam;
	}
	public void setStringParam(String stringParam) {
		this.stringParam = stringParam;
	}
	public String getMobileParam() {
		return mobileParam;
	}
	public void setMobileParam(String mobileParam) {
		this.mobileParam = mobileParam;
	}

}
