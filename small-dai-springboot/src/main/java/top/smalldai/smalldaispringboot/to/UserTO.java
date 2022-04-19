package top.smalldai.smalldaispringboot.to;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.smalldai.smalldaispringboot.entity.system.SysRole;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 返回给前端的用户TO
 * @Data:Created in 2022/3/27 10:52 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "用户TO")
public class UserTO implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "uId")
    @JsonProperty(value = "uId")
    private Long uId;

    // 用户名
    @ApiModelProperty(value = "用户名", name = "name")
    @JsonProperty(value = "name")
    private String name;

    // 密码
    @ApiModelProperty(value = "密码", name = "password")
    @JsonProperty(value = "password")
    private String password;

    // 年龄
    @ApiModelProperty(value = "年龄", name = "age")
    @JsonProperty(value = "age")
    private Integer age;

    // 手机号
    @ApiModelProperty(value = "手机号", name = "tel")
    @JsonProperty(value = "tel")
    private String tel;

    // 邮箱
    @ApiModelProperty(value = "邮箱", name = "email")
    @JsonProperty(value = "email")
    private String email;

    // 注册IP
    @ApiModelProperty(value = "注册IP", name = "createIp")
    @JsonProperty(value = "createIp")
    private String createIp;

    // 状态
    @ApiModelProperty(value = "状态", name = "deleted")
    @JsonProperty(value = "deleted")
    private Integer deleted;

    // 创建时间
    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @JsonProperty(value = "gmtCreate")
    private Date gmtCreate;

    // 修改时间
    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @JsonProperty(value = "gmtModified")
    private Date gmtModified;

    // 数据库来源
    @ApiModelProperty(value = "数据库来源", name = "dbSource")
    @JsonProperty(value = "dbSource")
    private String dbSource;

    // 用户角色
    @ApiModelProperty(value = "用户角色", name = "sysRoleTO")
    @JsonProperty(value = "roleTO")
    private RoleTO roleTO;

    @ApiModelProperty(value = "token", name = "token")
    @JsonProperty(value = "token")
    private String token;
}
