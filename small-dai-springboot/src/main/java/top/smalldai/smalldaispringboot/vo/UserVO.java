package top.smalldai.smalldaispringboot.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 注册传入的用户信息
 * @Data:Created in 2022/3/25 3:19 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "注册传入的用户信息")
public class UserVO implements Serializable {

    // 用户名
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "name")
    @ApiModelProperty(value = "用户名", name = "name")
    private String name;

    // 密码
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "password")
    @ApiModelProperty(value = "密码", name = "password")
    private String password;

    // 年龄
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "age")
    @ApiModelProperty(value = "年龄", name = "age")
    private Integer age;

    // 手机号
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "tel")
    @ApiModelProperty(value = "手机号", name = "tel")
    private String tel;

    // 邮箱
    @JsonProperty(value = "email", required = true)
    @ApiModelProperty(value = "邮箱", name = "email")
    private String email;

    // 验证码
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "code")
    @ApiModelProperty(value = "验证码", name = "code")
    private String code;

    // 注册IP
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "createIp")
    @ApiModelProperty(value = "注册IP", name = "createIp")
    private String createIp;
}
