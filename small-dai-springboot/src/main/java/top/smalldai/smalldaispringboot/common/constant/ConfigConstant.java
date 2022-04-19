package top.smalldai.smalldaispringboot.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 配置常量池
 * @Data:Created in 2022/3/31 12:42 下午
 */
public interface ConfigConstant {
    // config基础包
    public static final String CONFIG_BASE_PACKAGE = "package ${springPackageName}.config;\n\n";

    // 跨域问题Config
    public static final String CROS_CONFIG = "\n" +
            "import org.springframework.context.annotation.Bean;\n" +
            "import org.springframework.context.annotation.Configuration;\n" +
            "import org.springframework.web.cors.CorsConfiguration;\n" +
            "import org.springframework.web.cors.UrlBasedCorsConfigurationSource;\n" +
            "import org.springframework.web.filter.CorsFilter;\n" +
            "\n" +
            "import java.util.Collections;\n" +
            "@Configuration\n" +
            "public class CorsConfig{\n" +
            "    @Bean\n" +
            "    public CorsFilter corsFilter() {\n" +
            "        CorsConfiguration corsConfiguration = new CorsConfiguration();\n" +
            "        //1,允许任何来源\n" +
            "        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList(\"*\"));\n" +
            "        //2,允许任何请求头\n" +
            "        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);\n" +
            "        //3,允许任何方法\n" +
            "        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);\n" +
            "        //4,允许凭证\n" +
            "        corsConfiguration.setAllowCredentials(true);\n" +
            "\n" +
            "        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();\n" +
            "        source.registerCorsConfiguration(\"/**\", corsConfiguration);\n" +
            "        return new CorsFilter(source);\n" +
            "    }\n" +
            "}\n";

    // druid配置
    public static final String DRUID_CONFIG = "import com.alibaba.druid.pool.DruidDataSource;\n" +
            "import com.alibaba.druid.support.http.StatViewServlet;\n" +
            "import com.alibaba.druid.support.http.WebStatFilter;\n" +
            "import org.springframework.boot.context.properties.ConfigurationProperties;\n" +
            "import org.springframework.boot.web.servlet.FilterRegistrationBean;\n" +
            "import org.springframework.boot.web.servlet.ServletRegistrationBean;\n" +
            "import org.springframework.context.annotation.Bean;\n" +
            "import org.springframework.context.annotation.Configuration;\n" +
            "\n" +
            "import javax.sql.DataSource;\n" +
            "\n" +
            "@Configuration\n" +
            "public class DruidConfig {\n" +
            "    @Bean\n" +
            "    public ServletRegistrationBean druidServlet() { // 主要实现WEB监控的配置处理\n" +
            "        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(\n" +
            "                new StatViewServlet(), \"/druid/*\"); // 现在要进行druid监控的配置处理操作\n" +
            "        servletRegistrationBean.addInitParameter(\"allow\", \"127.0.0.1${whiteIP}\"); // 白名单\n" +
            "        // servletRegistrationBean.addInitParameter(\"deny\", \"${blackIP}\"); // 黑名单\n" +
            "        servletRegistrationBean.addInitParameter(\"loginUsername\", \"${loginUsername}\"); // 用户名\n" +
            "        servletRegistrationBean.addInitParameter(\"loginPassword\", \"${loginPassword}\"); // 密码\n" +
            "        servletRegistrationBean.addInitParameter(\"resetEnable\", \"false\"); // 是否可以重置数据源\n" +
            "        return servletRegistrationBean ;\n" +
            "    }\n" +
            "\n" +
            "    @Bean\n" +
            "    public FilterRegistrationBean filterRegistrationBean() {\n" +
            "        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean() ;\n" +
            "        filterRegistrationBean.setFilter(new WebStatFilter());\n" +
            "        filterRegistrationBean.addUrlPatterns(\"/*\"); // 所有请求进行监控处理\n" +
            "        filterRegistrationBean.addInitParameter(\"exclusions\", \"*.js,*.gif,*.jpg,*.css,/druid/*\");\n" +
            "        return filterRegistrationBean ;\n" +
            "    }\n" +
            "\n" +
            "    @Bean(destroyMethod = \"close\",initMethod = \"init\")\n" +
            "    @ConfigurationProperties(prefix = \"spring.datasource\")\n" +
            "    public DataSource druidDataSource() {\n" +
            "        return new DruidDataSource();\n" +
            "    }\n" +
            "}";

    // mybatis plus config
    public static final String MYBATIS_PLUS_CONFIG = "import com.baomidou.mybatisplus.annotation.DbType;\n" +
            "import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;\n" +
            "import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;\n" +
            "import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;\n" +
            "import org.apache.ibatis.reflection.MetaObject;\n" +
            "import org.springframework.context.annotation.Bean;\n" +
            "import org.springframework.context.annotation.Configuration;\n" +
            "\n" +
            "import java.util.Date;\n" +
            "\n" +
            "@Configuration\n" +
            "public class MybatisPlusConfig implements MetaObjectHandler {\n" +
            "    @Bean\n" +
            "    public MybatisPlusInterceptor mybatisPlusInterceptor() {\n" +
            "        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();\n" +
            "        // 添加分页插件\n" +
            "        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor();\n" +
            "        // 设置请求的页面大于最大页后操作，true调回到首页，false继续请求。默认false\n" +
            "        pageInterceptor.setOverflow(false);\n" +
            "        // 单页分页条数限制，默认无限制\n" +
            "        pageInterceptor.setMaxLimit(500L);\n" +
            "        // 设置数据库类型\n" +
            "        pageInterceptor.setDbType(DbType.MYSQL);\n" +
            "\n" +
            "        interceptor.addInnerInterceptor(pageInterceptor);\n" +
            "        return interceptor;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void insertFill(MetaObject metaObject) {\n" +
            "        //属性名称，不是字段名称\n" +
            "        this.setFieldValByName(\"gmtCreate\", new Date(), metaObject);\n" +
            "        this.setFieldValByName(\"gmtModified\", new Date(), metaObject);\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void updateFill(MetaObject metaObject) {\n" +
            "        this.setFieldValByName(\"gmtModified\", new Date(), metaObject);\n" +
            "    }\n" +
            "}\n";

    // swagger config
    public static final String SWAGGER_CONFIG = "import org.springframework.context.annotation.Bean;\n" +
            "import org.springframework.context.annotation.Configuration;\n" +
            "import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;\n" +
            "import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;\n" +
            "import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;\n" +
            "import springfox.documentation.builders.ApiInfoBuilder;\n" +
            "import springfox.documentation.builders.PathSelectors;\n" +
            "import springfox.documentation.builders.RequestHandlerSelectors;\n" +
            "import springfox.documentation.service.ApiInfo;\n" +
            "import springfox.documentation.spi.DocumentationType;\n" +
            "import springfox.documentation.spring.web.plugins.Docket;\n" +
            "import springfox.documentation.swagger2.annotations.EnableSwagger2;\n" +
            "\n" +
            "@Configuration\n" +
            "@EnableSwagger2\n" +
            "\n" +
            "public class SwaggerConfig extends WebMvcConfigurationSupport {\n" +
            "    @Bean\n" +
            "    public Docket createRestApi(){\n" +
            "        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())\n" +
            "                .select()\n" +
            "                .apis(RequestHandlerSelectors.any())\n" +
            "                .paths(PathSelectors.regex(\"(?!/error.*).*\")).build();\n" +
            "    }\n" +
            "\n" +
            "    private ApiInfo apiInfo(){\n" +
            "        return new ApiInfoBuilder()\n" +
            "                .title(\"${swaggerHeader}\")\n" +
            "                .description(\"${swaggerDescription}\")\n" +
            "                .version(\"${swaggerVersion}\")\n" +
            "                .build();\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void addResourceHandlers(ResourceHandlerRegistry registry) {\n" +
            "        registry.\n" +
            "                addResourceHandler( \"/swagger-ui/**\")\n" +
            "                .addResourceLocations(\"classpath:/META-INF/resources/webjars/springfox-swagger-ui/\")\n" +
            "                .resourceChain(false);\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void addViewControllers(ViewControllerRegistry registry) {\n" +
            "        registry.addViewController( \"/swagger-ui/\")\n" +
            "                .setViewName(\"forward:\" +  \"/swagger-ui/index.html\");\n" +
            "    }\n" +
            "}";

    // ShiroConfig
    public static final String SHIRO_CONFIG = "import org.apache.shiro.authc.credential.HashedCredentialsMatcher;\n" +
            "import org.apache.shiro.realm.Realm;\n" +
            "import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;\n" +
            "import org.apache.shiro.spring.web.ShiroFilterFactoryBean;\n" +
            "import org.apache.shiro.web.mgt.DefaultWebSecurityManager;\n" +
            "import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;\n" +
            "import org.springframework.context.annotation.Bean;\n" +
            "import org.springframework.context.annotation.Configuration;\n" +
            "import ${springPackageName}.config.realm.ShiroRealm;\n" +
            "\n" +
            "import java.util.HashMap;\n" +
            "import java.util.Map;\n" +
            "\n" +
            "@Configuration\n" +
            "public class ShiroConfig {\n" +
            "    @Bean(name = \"shiroFilterFactoryBean\")\n" +
            "    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){\n" +
            "        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();\n" +
            "\n" +
            "        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);\n" +
            "        Map<String,String> map=new HashMap<>();\n" +
            "        map.put(\"/account/login\",\"anon\");\n" +
            "        map.put(\"/account/email/send/password\", \"anon\");\n" +
            "        map.put(\"/account/email/send\", \"anon\");\n" +
            "        map.put(\"/account/register\", \"anon\");\n" +
            "        map.put(\"/account/password/update\", \"anon\");\n" +
            "        map.put(\"/swagger*/**\", \"anon\");\n" +
            "        map.put(\"/webjars/**\", \"anon\");\n" +
            "        map.put(\"/v2/**\", \"anon\");\n" +
            "\n" +
            "        map.put(\"/**\",\"anon\");\n" +
            "\n" +
            "        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);\n" +
            "\n" +
            "        shiroFilterFactoryBean.setLoginUrl(\"/index\");\n" +
            "\n" +
            "\n" +
            "        return shiroFilterFactoryBean;\n" +
            "    }\n" +
            "\n" +
            "    @Bean\n" +
            "    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){\n" +
            "        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();\n" +
            "        //给安全管理器设置Realm\n" +
            "        defaultWebSecurityManager.setRealm(realm);\n" +
            "        return defaultWebSecurityManager;\n" +
            "    }\n" +
            "\n" +
            "    @Bean\n" +
            "    public Realm getRealm(){\n" +
            "        ShiroRealm shiroRealm = new ShiroRealm();\n" +
            "        //修改realm中默认的密码凭证校验匹配器\n" +
            "        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();\n" +
            "        hashedCredentialsMatcher.setHashAlgorithmName(\"Md5\");\n" +
            "        hashedCredentialsMatcher.setHashIterations(1024);\n" +
            "        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);\n" +
            "        return shiroRealm;\n" +
            "    }\n" +
            "    @Bean\n" +
            "    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){\n" +
            "        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();\n" +
            "        advisorAutoProxyCreator.setProxyTargetClass(true);\n" +
            "        return advisorAutoProxyCreator;\n" +
            "    }\n" +
            "\n" +
            "    @Bean\n" +
            "    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {\n" +
            "        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();\n" +
            "        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);\n" +
            "        return authorizationAttributeSourceAdvisor;\n" +
            "    }\n" +
            "}\n";

    public static final String REALM_CONFIG = "package ${springPackageName}.config.realm;" +
            "import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;\n" +
            "import org.apache.shiro.authc.AuthenticationException;\n" +
            "import org.apache.shiro.authc.AuthenticationInfo;\n" +
            "import org.apache.shiro.authc.AuthenticationToken;\n" +
            "import org.apache.shiro.authc.SimpleAuthenticationInfo;\n" +
            "import org.apache.shiro.authz.AuthorizationInfo;\n" +
            "import org.apache.shiro.authz.SimpleAuthorizationInfo;\n" +
            "import org.apache.shiro.realm.AuthorizingRealm;\n" +
            "import org.apache.shiro.subject.PrincipalCollection;\n" +
            "import org.apache.shiro.util.ByteSource;\n" +
            "import ${springPackageName}.entity.User;\n" +
            "import ${springPackageName}.service.UserService;\n" +
            "import ${springPackageName}.to.AccountTO;\n" +
            "import ${springPackageName}.to.PermissionTO;\n" +
            "\n" +
            "import javax.annotation.Resource;\n" +
            "\n" +
            "\n" +
            "public class ShiroRealm extends AuthorizingRealm {\n" +
            "\n" +
            "    @Resource\n" +
            "    private UserService userService;\n" +
            "\n" +
            "    @Override\n" +
            "    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {\n" +
            "        User user = (User) principalCollection.getPrimaryPrincipal();\n" +
            "        AccountTO userTO = userService.listUserById(user.getUEmail());\n" +
            "        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();\n" +
            "        simpleAuthorizationInfo.addRole(userTO.getRoleTO().getRName());\n" +
            "        for (PermissionTO permissionTO : userTO.getRoleTO().getPermissionTOList()) {\n" +
            "            simpleAuthorizationInfo.addStringPermission(permissionTO.getPName());\n" +
            "        }\n" +
            "        return simpleAuthorizationInfo;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {\n" +
            "        String email = (String) authenticationToken.getPrincipal();\n" +
            "        User user = userService.getOne(new QueryWrapper<User>().eq(\"u_email\", email));\n" +
            "        if (user == null) {\n" +
            "            return null;\n" +
            "        }\n" +
            "        return new SimpleAuthenticationInfo(user, user.getUPassword(), ByteSource.Util.bytes(user.getUSalt()), getName());\n" +
            "    }\n" +
            "}\n";
}
