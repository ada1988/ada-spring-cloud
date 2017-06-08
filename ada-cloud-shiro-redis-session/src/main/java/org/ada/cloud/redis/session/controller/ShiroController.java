package org.ada.cloud.redis.session.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShiroController {
	
	/**
	 * 登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/loginIn" })
	@ResponseBody
	public String loginIn(HttpServletRequest request) {
		System.out.println("loginIn sessionId:" + request.getSession().getId());

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return request.getSession().getId();
	}

	@RequestMapping(value = { "/loginOut" })
	@ResponseBody
	public String loginOut(HttpServletRequest request) {
		System.out.println("loginOut sessionId:" + request.getSession().getId());
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return request.getSession().getId();
	}
	
	@RequiresRoles("role1")
	@RequestMapping(value = { "", "/", "/home" })
	@ResponseBody
	public String home(HttpServletRequest request) {
		System.out.println("sessionId:" + request.getSession().getId());
		return request.getSession().getId();
	}

	@RequestMapping(value = { "/login" })
	@ResponseBody
	public String login(HttpServletRequest request) {
		System.out.println("login sessionId:" + request.getSession().getId());
		return request.getSession().getId();
	}

	@RequestMapping(value = { "/success" })
	@ResponseBody
	public String success(HttpServletRequest request) {
		System.out.println("success sessionId:" + request.getSession().getId());
		return request.getSession().getId();
	}

}
