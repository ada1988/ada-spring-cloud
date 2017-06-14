package org.ada.cloud.redis.session.config;

import org.ada.cloud.redis.session.shiro.ShiroDbRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ShiroSessionConfig {

	/**
	 * 保证实现shiro内部的生命周期函数bean的执行
	 * 
	 * @return
	 */
	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		System.out.println("LifecycleBeanPostProcessor............");
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 自定义Realm
	 * 
	 * @return
	 */
	@Bean
	public ShiroDbRealm shiroDbRealm() {
		System.out.println("getShiroDbRealm............");
		return new ShiroDbRealm();
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager defaultWebSecurityManager() {
		System.out.println("defaultWebSecurityManager............");
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(
				shiroDbRealm());
		defaultWebSecurityManager.setCacheManager(redisCacheManager());
		defaultWebSecurityManager
				.setSessionManager(defaultWebSessionManager());
		//defaultWebSecurityManager.setSubjectFactory();
		return defaultWebSecurityManager;
	}

	/**
	 * 配置shiro redisManager 使用的是shiro-redis开源插件
	 * 
	 * @return
	 */
	@Bean("redisManager")
	public RedisManager redisManager() {
		System.out.println("redisManager............");
		RedisManager redisManager = new RedisManager();
		redisManager.setHost("192.168.30.128");
		redisManager.setPort(7000);
		redisManager.setExpire(1800);// 配置缓存过期时间
		redisManager.setTimeout(300000);
		// redisManager.setPassword(password);
		return redisManager;
	}

	/**
	 * session存储的实现
	 * 
	 * @return
	 */
	@Bean
	public RedisSessionDAO redisShiroSessionDAO() {
		System.out.println("redisShiroSessionDAO............");
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	@Bean
	public RedisCacheManager redisCacheManager() {
		System.out.println("redisCacheManager............");
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * session管理器
	 * 
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager defaultWebSessionManager() {
		System.out.println("defaultWebSessionManager............");
		DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
		// 设置全局会话超时时间，默认30分钟(1800000)
		defaultWebSessionManager.setGlobalSessionTimeout(1800000);
		// 是否在会话过期后会调用SessionDAO的delete方法删除会话 默认true
		defaultWebSessionManager.setDeleteInvalidSessions(true);
		// 会话验证器调度时间
		defaultWebSessionManager.setSessionValidationInterval(1800000);
		// session存储的实现
		defaultWebSessionManager.setSessionDAO(redisShiroSessionDAO());
		//sessionIdCookie的实现,用于重写覆盖容器默认的JSESSIONID
		defaultWebSessionManager.setSessionIdCookie(simpleCookie());
		// 定时检查失效的session
		defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
		return defaultWebSessionManager;
	}
	
	/**
	 * sessionIdCookie的实现,用于重写覆盖容器默认的JSESSIONID
	 * 
	 * @return
	 */
	@Bean
	public SimpleCookie simpleCookie() {
		System.out.println("simpleCookie............");
		// cookie的name,对应的默认是 JSESSIONID
		SimpleCookie simpleCookie = new SimpleCookie(".ada.com");
		// jsessionId的path为 / 用于多个系统共享jsessionId
		simpleCookie.setPath("/");
		simpleCookie.setHttpOnly(true);
		return simpleCookie;
	}

	/**
	 * 开启shiro的注解，需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 
	 * @return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		System.out.println("defaultAdvisorAutoProxyCreator............");
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		System.out.println("authorizationAttributeSourceAdvisor............");
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor
				.setSecurityManager(defaultWebSecurityManager());
		return authorizationAttributeSourceAdvisor;
	}
	
	@Bean("shiroSecurityFilter")
	public ShiroFilterFactoryBean shiroSecurityFilter(){
		System.out.println("shiroSecurityFilter............");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/success");
		shiroFilterFactoryBean.setFilterChainDefinitions("/logout=logout");
		return shiroFilterFactoryBean;
	}
}
