package com.lin.shiro.core.config;

import com.lin.shiro.core.realm.CustomSecurityRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Configuration
public class ShiroConfig {

//      <!--
//    关于这里,
//            1. 如果你定义是多个同名的key, 那么Shiro会将其合并.
//					2. 如果当前请求匹配多个key, Shiro只会取第一个匹配的.
//					3. 以上是默认行为, 如果有特殊需求可以覆写。
//            -->
//            /login.jsp = authc
//            /logout = logout
//            /unauthorized.jsp = anon
//			/main**           = authc
//            /admin/list**     = authc,perms[admin:manage]
//            /user/info-anon** = anon
//            /user/info**      = authc
//    /** = anon
//

        @Bean(name = "shiroFilter")
        public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

            ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
            shiroFilterFactoryBean.setSecurityManager(securityManager);
            // 如果不设置,默认会自动寻找Web工程根目录下的"/login.jsp"页面
            shiroFilterFactoryBean.setLoginUrl("/login");
            shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");//未授权跳转!
            shiroFilterFactoryBean.setSuccessUrl("/index"); //登录成功跳转的链接

            Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>(); //拦截器.
            //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了 gan
            filterChainDefinitionMap.put("/logout", "logout");
            // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//            filterChainDefinitionMap.put("/webjars/**", "anon"); // 无效 ?? webjars是maven添加静态资源依赖时的路径
            filterChainDefinitionMap.put("/jquery/**", "anon");
            filterChainDefinitionMap.put("/demo/**", "anon");
            filterChainDefinitionMap.put("/project/**", "anon");
            filterChainDefinitionMap.put("/plugins/**", "anon");
            filterChainDefinitionMap.put("/mycookie", "anon");
            filterChainDefinitionMap.put("/classify/**", "anon");
            filterChainDefinitionMap.put("/error/**", "anon");
            filterChainDefinitionMap.put("/login", "anon");
            filterChainDefinitionMap.put("/register", "anon");
            filterChainDefinitionMap.put("/", "anon");
            filterChainDefinitionMap.put("/index", "anon");
            filterChainDefinitionMap.put("/index.html", "anon");
            filterChainDefinitionMap.put("/search", "anon");

//            filterChainDefinitionMap.put("/front/**", "anon");//?


            filterChainDefinitionMap.put("/api/**", "authc");
            filterChainDefinitionMap.put("/admin/**", "authc");
            filterChainDefinitionMap.put("/user/**", "authc");
            //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
            filterChainDefinitionMap.put("/**", "authc");
            shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
            return shiroFilterFactoryBean;

        }

    /**
     * Shiro 提供了三个默认实现：
     *
     * DefaultSessionManager：DefaultSecurityManager 使用的默认实现，用于 JavaSE 环境；
     * ServletContainerSessionManager：DefaultWebSecurityManager 使用的默认实现，
     * 用于 Web 环境，其直接使用 Servlet 容器的会话；
     * DefaultWebSessionManager：用于 Web 环境的实现，
     * 可以替代 ServletContainerSessionManager，自己维护着会话，
     * 直接废弃了 Servlet 容器的会话管理。
     * -----
     * DefaultSecurityManager 及 DefaultWebSecurityManager
     * 默认 SecurityManager 都继承了 SessionsSecurityManager。
     * @return
     */
//    @Bean
//        public SecurityManager securityManager() {
//            DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
//            defaultSecurityManager.setRealm(customSecurityRealm());
////        defaultSecurityManager.setSessionManager(new DefaultWebSessionManager()); //例如
//            return defaultSecurityManager;
//        }


    //使用自身的sessionmanager ,替换容器的session
    //sessionIdCookie 是 sessionManager 创建会话 Cookie 的模板：
    //sessionIdCookie.name：设置 Cookie 名字，默认为 JSESSIONID；
    //sessionIdCookie.domain：设置 Cookie 的域名，默认空，即当前访问的域名；
    //sessionIdCookie.path：设置 Cookie 的路径，默认空，即存储在域名根下；
    //sessionIdCookie.maxAge：设置 Cookie 的过期时间，秒为单位，默认 - 1 表示关闭浏览器时过期 Cookie；
    //sessionIdCookie.httpOnly：如果设置为 true，则客户端不会暴露给客户端脚本代码，使用 HttpOnly cookie 有助于减少某些类型的跨站点脚本攻击；此特性需要实现了 Servlet 2.5 MR6 及以上版本的规范的 Servlet 容器支持；
    //sessionManager.sessionIdCookieEnabled：是否启用 / 禁用 Session Id Cookie，默认是启用的；如果禁用后将不会设置 Session Id Cookie，即默认使用了 Servlet 容器的 JSESSIONID，且通过 URL 重写（URL 中的 “;JSESSIONID=id” 部分）保存 Session Id。
    //另外我们可以如 “sessionManager. sessionIdCookie.name=sid” 这种方式操作 Cookie 模板。
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customSecurityRealm());

        //自己管理session
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setMaxAge(18000);
        simpleCookie.setName("sid");
        simpleCookie.setHttpOnly(true);
      //  simpleCookie.setHttpOnly(true);
        defaultWebSessionManager.setSessionIdCookie(simpleCookie);
        defaultWebSessionManager.setGlobalSessionTimeout(18000000); //session有效时间默认30分钟,单位毫秒


        defaultSecurityManager.setSessionManager(defaultWebSessionManager); //!!

        return defaultSecurityManager;
    }


        @Bean
        public CustomSecurityRealm customSecurityRealm() {
            CustomSecurityRealm customSecurityRealm = new CustomSecurityRealm();
            customSecurityRealm.setCredentialsMatcher(hashedCredentialsMatcher()); // gan ??
            return customSecurityRealm;
        }

        //-----------下面功能未知.可能引起程序崩溃?

    /**
     * 凭证匹配器
     * 由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true); //表示是否存储散列后的密码为 16 进制，需要和生成密码时的一样，默认是 base64；
        return hashedCredentialsMatcher;
    }


    //? 缓存
    @Bean
    public CacheManager cacheManager() {
        // Caching isn't needed in this example, but we will use the MemoryConstrainedCacheManager for this example.
        return new MemoryConstrainedCacheManager();
    }

    //??
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

//    /**
//     * 注册全局异常处理
//     * @return
//     */
//    @Bean(name = "exceptionHandler")
//    public HandlerExceptionResolver handlerExceptionResolver() {
//        return new ExceptionHandler();
//    }


}