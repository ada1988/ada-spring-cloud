package org.ada.cloud.validation.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.ada.cloud.validation.base.GroupIntAndStringDefault;
import org.ada.cloud.validation.bean.MdDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**  
 * Filename: UserInfoController.java  <br>
 *
 * Description: 用户信息控制器  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月6日 <br>
 *
 *  
 */
@Controller
public class UserInfoController {
	
	/**
	 * 单个基础类型验证，不可以获取异常提示
	 * @param id
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年6月6日
	 */
	@RequestMapping("/index")
	@ResponseBody
	public String queryUserById(@Min(value=1,message = "id必须大于等于1") @RequestParam Integer id){
		System.out.println("进入方法");
		return "测试";
	}
	
	/**
	 * post提交数据时使用，可获取异常提示。
	 * @param test
	 * @param result
	 * @return
	 * @author: CZD  
	 * @Createtime: 2017年6月6日
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(@Validated(GroupIntAndStringDefault.class) MdDefault test,BindingResult result){
		List<ObjectError> errors = result.getAllErrors();
		for(ObjectError error:errors){
			System.out.println(error.getDefaultMessage());
		}
		System.out.println("进入方法");
		return "测试";
	}
}
