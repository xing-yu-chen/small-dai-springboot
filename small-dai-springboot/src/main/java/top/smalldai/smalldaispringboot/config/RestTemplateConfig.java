package top.smalldai.smalldaispringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: ResultFul格式请求
 * @Data:Created in 2022/3/28 10:15 上午
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
