package org.ada.cloud.redis.session.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
/**
 * 权限验证
 * 
 * 一、doGetAuthorizationInfo执行时机有三个 
 * 1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
 * 2、@RequiresRoles("admin") ：在方法上加注解的时候； 
 * 3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。
 * 
 * 二、doGetAuthenticationInfo执行时机如下 当调用Subject currentUser = SecurityUtils.getSubject(); currentUser.login(token);
 * 
 * @author CZD
 *
 */
public class ShiroDbRealm extends AuthorizingRealm{

	/**
	 * 表示获取身份验证信息.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("doGetAuthorizationInfo....身份验证信息。::::"+principals.getPrimaryPrincipal());
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
		authorizationInfo.addRole("role1");  
        authorizationInfo.addRole("role2");  
        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));  
        authorizationInfo.addStringPermission("+user2+10");  
        authorizationInfo.addStringPermission("user2:*");  
        return authorizationInfo;  
	}

	/**
	 * 表示根据用户身份获取授权信息。
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token)
			throws AuthenticationException {
		System.out.println("doGetAuthenticationInfo....获取授权信息。");
		 String username = (String)token.getPrincipal();  //得到用户名  
	        String password = new String((char[])token.getCredentials()); //得到密码  
	        if(!"zhang".equals(username)) {  
	            throw new UnknownAccountException(); //如果用户名错误  
	        }  
	        if(!"123".equals(password)) {  
	            throw new IncorrectCredentialsException(); //如果密码错误  
	        }  
	        //如果身份认证验证成功，返回一个AuthenticationInfo实现；  
	        return new SimpleAuthenticationInfo(username, password, getName());  
	}

}
