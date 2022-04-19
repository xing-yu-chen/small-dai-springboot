package top.smalldai.smalldaispringboot.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 配置VO
 * @Data:Created in 2022/4/1 10:46 上午
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "配置VO")
public class ConfigVO implements Serializable {

    // 数据库ID
    @JsonProperty(value = "dId")
    @ApiModelProperty(value = "数据库ID", name = "dId")
    private Long dId;

    // IP黑名单
    @JsonProperty(value = "blackIP", defaultValue = "10.88.*.*")
    @ApiModelProperty(value = "IP黑名单", name = "blackIP")
    private String blackIP;

    // IP白名单
    @JsonProperty(value = "whiteIP", defaultValue = "localhost")
    @ApiModelProperty(value = "IP白名单", name = "whiteIP")
    private String whiteIP;

    // druid登陆账号
    @JsonProperty(value = "loginUsername")
    @ApiModelProperty(value = "druid登陆账号", name = "loginUsername")
    private String loginUsername;

    // druid登陆密码
    @JsonProperty(value = "loginPassword")
    @ApiModelProperty(value = "druid登陆密码", name = "loginPassword")
    private String loginPassword;


    // swagger 标题
    @JsonProperty(value = "swaggerHeader")
    @ApiModelProperty(value = "swagger 标题", name = "swaggerHeader")
    private String swaggerHeader;

    // swagger 描述
    @JsonProperty(value = "swaggerDescription")
    @ApiModelProperty(value = "swagger描述", name = "swaggerDescription")
    private String swaggerDescription;

    // swagger版本号
    @JsonProperty(value = "swaggerVersion")
    @ApiModelProperty(value = "swagger版本号", name = "swaggerVersion")
    private String swaggerVersion;

}
