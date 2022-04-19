package top.smalldai.smalldaispringboot.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: yml VO
 * @Data:Created in 2022/4/1 9:50 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "yml VO")
public class YmlVO implements Serializable {
    // ip
    @JsonProperty(value = "address")
    private String address;

    // 端口
    @JsonProperty(value = "port")
    private String port;

    // 用户名
    @JsonProperty(value = "username")
    private String username;

    // 密码
    @JsonProperty(value = "password")
    private String password;

    // 数据库ID
    @JsonProperty(value = "dId")
    private Long dId;

    // QQ 邮箱
    @JsonProperty(value = "qqEmail")
    private String qqEmail;

    // QQ 邮箱token
    @JsonProperty(value = "token")
    private String token;
}
