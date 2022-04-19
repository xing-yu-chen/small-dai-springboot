package top.smalldai.smalldaispringboot.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.smalldai.smalldaispringboot.common.constant.AccountConstant;
import top.smalldai.smalldaispringboot.config.realm.ShiroRealm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: Shiro配置
 * @Data:Created in 2022/3/26 2:49 下午
 */
@Configuration
public class ShiroConfig {
    // 1. 创建ShiroFilter
    // 功能:负责拦截所有请求
    // 需要将DefaultWebSecurityManager自动注入进ShiroFilter中
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //经过shiro的所有请求都会经过当前的filter
        System.out.println("拦截了数据");
        //需要告诉filter哪些资源是公共资源，哪些资源是受限资源
        //配置系统受限资源
        Map<String,String> map=new HashMap<>();
        //配置系统的公共资源,不受限的资源放在上面,要用到的认证资源路径不能拦截
        //比如登录、注册
        map.put("/account/login","anon");
        map.put("/account/email/send/password", "anon");
        map.put("/account/email/send", "anon");
        map.put("/account/register", "anon");
        map.put("/account/password/update", "anon");
        // swagger的资源放行
        map.put("/swagger*/**", "anon");
        map.put("/webjars/**", "anon");
        map.put("/v2/**", "anon");

        //authc受限资源拦截，表示请求这个资源需要认证和授权
        //anon是不需要认证和授权的匿名访问
        //map.put("/**", "anon");
        map.put("/**","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/index");


        return shiroFilterFactoryBean;
    }

    // 2. 创建SecurityManager
    // 需要Realm数据来源自动注入到SecurityManager里
    // 功能:设置Realm
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置Realm
        defaultWebSecurityManager.setRealm(realm);
        defaultWebSecurityManager.setSessionManager(new ShiroSession());
        return defaultWebSecurityManager;
    }

    // 3. 创建自定义Realm
    @Bean
    public Realm getRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        //修改realm中默认的密码凭证校验匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("Md5");
        hashedCredentialsMatcher.setHashIterations(AccountConstant.HASH_NUMBER);
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return shiroRealm;
    }

    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
