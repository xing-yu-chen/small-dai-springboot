package ${springPackageName}.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "登陆鉴权TO")
public class AccountTO implements Serializable {
    @ApiModelProperty(value = "ID", name = "uId")
    @JsonProperty(value = "uId")
    private Long uId;

    @ApiModelProperty(value = "用户名", name = "uName")
    @JsonProperty(value = "uName")
    private String uName;

    @ApiModelProperty(value = "密码", name = "uPassword")
    @JsonProperty(value = "uPassword")
    private String uPassword;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModelProperty(value = "随机盐", name = "uSalt")
    @JsonProperty(value = "uSalt")
    private String uSalt;

    @ApiModelProperty(value = "验证码", name = "code")
    @JsonProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "手机号", name = "uPhone")
    @JsonProperty(value = "uPhone")
    private String uPhone;

    @ApiModelProperty(value = "邮箱", name = "uEmail")
    @JsonProperty(value = "uEmail")
    private String uEmail;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModelProperty(value = "角色TO", name = "roleTO")
    @JsonProperty(value = "roleTO")
    private RoleTO roleTO;


}
