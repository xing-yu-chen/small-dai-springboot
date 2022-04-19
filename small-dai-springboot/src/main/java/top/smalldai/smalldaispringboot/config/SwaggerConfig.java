package top.smalldai.smalldaispringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: Swagger配置
 * @Data:Created in 2022/3/24 5:40 下午
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("(?!/error.*).*")).build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("小呆代码生成器-SpringBoot版-Swagger")
                .description("这是小呆代码生成器的SpringBoot版本，欢迎大家加入我们学习了解，同时我们提供一个性价比极高的个性化生成器平台。")
                .version("v1")
                .build();
    }

    /**
     * @Author: xingyuchen
     * @Discription: 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式/此处存在两个报错点，springboot版本过高不兼容，静态资源路径被覆盖，swagger2 2.9.2版本有2个漏洞,所以UI使用2.9.2,swagger2用3.0
     * @param registry
     * @Date: 2021/12/22 4:36 上午
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler( "/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/swagger-ui/")
                .setViewName("forward:" +  "/swagger-ui/index.html");
    }
}