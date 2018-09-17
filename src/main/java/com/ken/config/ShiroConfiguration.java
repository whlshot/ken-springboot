package com.ken.config;

import com.ken.realm.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800);//配置缓存过期时间
        redisManager.setTimeout(timeout);
        return redisManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(redisManager());
        return cacheManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        //redisSessionDAO.setKeyPrefix("");redis缓存中key的前缀
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     * shiro通过SessionManager来管理Session，
     * 对于Session的操作是通过SessionDao来实现的，
     * 默认情况下，shiro实现了两种SessionDao,
     * 分别为CachingSessionDao和MemorySessionDao,
     * 当我们使用EhCache缓存时，则是使用CachingSessionDao。
     * 不使用缓存的情况下，就会选择基于内存的SessionDao。
     * 如果我们想实现基于Redis的分布式Session共享，重点在于重写
     * SessionManager中的SessionDao
     *
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(redisCacheManager());//加入缓存管理器
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setDeleteInvalidSessions(true);//删除过期的session
        sessionManager.setGlobalSessionTimeout(120000);//设置全局的session超时时间
        sessionManager.setSessionValidationSchedulerEnabled(true);//是否定时检查session
        sessionManager.setSessionIdCookie(shareSession());
        return sessionManager;
    }

    /**
     * Java代码中，通过SecurityUtils.getSubject().getSession()获取session即可
     *
     * @return
     */
    @Bean
    public SimpleCookie shareSession() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("JSESSIONID");//cookie的name，对应的默认值是JESSIONID
        simpleCookie.setPath("/");//jsessionId的path为/用于多个系统共享jsessionId
        simpleCookie.setHttpOnly(false);
        return simpleCookie;
    }

    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher());
        return shiroRealm;
    }

    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        return credentialsMatcher;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 可以自动的调用配置在SpringIOC容器中的Shiro Bean的生命周期方法
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * IOC容器中使用shiro注解（必须在配置了 LifecycleBeanPostProcessor 之后才能使用）
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/login", "anon");
        map.put("/**", "authc");
        map.put("/logout", "logout");

        //自定义加载权限资源关系
        //List<Resources> resourcesList = resourcesService.queryAll();
        //for(Resources resources: resourcesList){
        //
        //    if (StringUtil.isNotEmpty(resources.getResurl())) {
        //        String permission = "perms[" + resources.getResurl()+ "]";
        //        map.put(resources.getResurl(),permission);
        //    }
        //}

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setSuccessUrl("/success");
        shiroFilter.setLoginUrl("/notLogin");
        shiroFilter.setUnauthorizedUrl("/unauthorized");
        shiroFilter.setFilterChainDefinitionMap(map);
        return shiroFilter;
    }
}

