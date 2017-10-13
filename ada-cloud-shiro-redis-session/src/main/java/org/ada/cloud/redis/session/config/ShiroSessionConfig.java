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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@EnableConfigurationProperties(ShiroRedisProperties.class)
public class ShiroSessionConfig {
	private final Logger		logger	= LoggerFactory.getLogger( getClass() );
	/**
	 * 保证实现shiro内部的生命周期函数bean的执行
	 * 
	 * @return
	 */
	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		logger.info("[shrio]配置-加载类-LifecycleBeanPostProcessor............");
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 自定义Realm
	 * 
	 * @return
	 */
	@Bean
	public ShiroDbRealm shiroDbRealm() {
		logger.info("[shrio]配置-加载类-getShiroDbRealm............");
		return new ShiroDbRealm();
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager defaultWebSecurityManager() {
		logger.info("[shrio]配置-加载类-defaultWebSecurityManager............");
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(
				shiroDbRealm());
		defaultWebSecurityManager.setCacheManager(redisCacheManager());
		defaultWebSecurityManager
				.setSessionManager(defaultWebSessionManager());
		return defaultWebSecurityManager;
	}

	
	@Autowired
	private ShiroRedisProperties shiroRedisProperties;
	/**
	 * 配置shiro redisManager 使用的是shiro-redis开源插件
	 * 
	 * @return
	 */
	@Bean("redisManager")
	public RedisManager redisManager() {
		logger.info("[shrio]配置-加载类-redisManager............");
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(shiroRedisProperties.getHost());
		redisManager.setPort(shiroRedisProperties.getPort());
		redisManager.setExpire(1800);// 配置缓存过期时间
		redisManager.setTimeout(300000);
		return redisManager;
	}

	/**
	 * session存储的实现
	 * 
	 * @return
	 */
	@Bean
	public RedisSessionDAO redisShiroSessionDAO() {
		logger.info("[shrio]配置-加载类-redisShiroSessionDAO............");
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	@Bean
	public RedisCacheManager redisCacheManager() {
		logger.info("[shrio]配置-加载类-redisCacheManager............");
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * session管理器
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager defaultWebSessionManager() {
		logger.info("[shrio]配置-加载类-defaultWebSessionManager............");
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
	 * 在Servlet容器中，默认使用JSESSIONID Cookie维护会话，且会话默认是跟容器绑定的；
	 * 在某些情况下可能需要使用自己的会话机制，此时我们可以使用DefaultWebSessionManager来维护会话：
	 * 		sessionIdCookie是sessionManager创建会话Cookie的模板：
			sessionIdCookie.name：设置Cookie名字，默认为JSESSIONID；
			sessionIdCookie.domain：设置Cookie的域名，默认空，即当前访问的域名；
			sessionIdCookie.path：设置Cookie的路径，默认空，即存储在域名根下；
			sessionIdCookie.maxAge：设置Cookie的过期时间，秒为单位，默认-1表示关闭浏览器时过期Cookie；
			sessionIdCookie.httpOnly：如果设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；此特性需要实现了Servlet 2.5 MR6及以上版本的规范的Servlet容器支持；
			sessionManager.sessionIdCookieEnabled：是否启用/禁用Session Id Cookie，默认是启用的；如果禁用后将不会设置Session Id Cookie，即默认使用了Servlet容器的JSESSIONID，且通过URL重写（URL中的“;JSESSIONID=id”部分）保存Session Id。
	
	 * @return
	 */
	@Bean
	public SimpleCookie simpleCookie() {
		logger.info("[shrio]配置-加载类-simpleCookie............");
		// cookie的name,对应的默认是 JSESSIONID
		SimpleCookie simpleCookie = new SimpleCookie("ada_clustor_session_id_");
		// jsessionId的path为 / 用于多个系统共享jsessionId
		//simpleCookie.setPath("/");
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
		logger.info("[shrio]配置-加载类-defaultAdvisorAutoProxyCreator............");
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		logger.info("[shrio]配置-加载类-authorizationAttributeSourceAdvisor............");
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor
				.setSecurityManager(defaultWebSecurityManager());
		return authorizationAttributeSourceAdvisor;
	}
	
	@Bean("shiroSecurityFilter")
	public ShiroFilterFactoryBean shiroSecurityFilter(){
		logger.info("[shrio]配置-加载类-shiroSecurityFilter............");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/success");
		shiroFilterFactoryBean.setFilterChainDefinitions("/logout=logout");
		return shiroFilterFactoryBean;
	}
}
