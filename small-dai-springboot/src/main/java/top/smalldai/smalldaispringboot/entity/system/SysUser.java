package top.smalldai.smalldaispringboot.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 系统用户
 * @Data:Created in 2022/3/24 10:51 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "用户")
@TableName("t_sys_user")
public class SysUser implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "uId")
    @TableId(value = "u_id", type = IdType.AUTO)
    @JsonProperty(value = "uId")
    private Long uId;

    // 用户名
    @ApiModelProperty(value = "用户名", name = "name")
    @TableField(value = "u_name")
    @JsonProperty(value = "name")
    private String name;

    // 密码
    @ApiModelProperty(value = "密码", name = "password")
    @TableField(value = "u_password")
    @JsonProperty(value = "password")
    private String password;

    // 随机盐
    @ApiModelProperty(value = "随机盐", name = "salt")
    @TableField(value = "u_salt")
    @JsonProperty(value = "salt")
    private String salt;


    // 年龄
    @ApiModelProperty(value = "年龄", name = "age")
    @TableField(value = "u_age")
    @JsonProperty(value = "age")
    private Integer age;

    // 手机号
    @ApiModelProperty(value = "手机号", name = "tel")
    @TableField(value = "u_tel")
    @JsonProperty(value = "tel")
    private String tel;

    // 邮箱
    @ApiModelProperty(value = "邮箱", name = "email")
    @TableField(value = "u_email")
    @JsonProperty(value = "email")
    private String email;

    // 角色ID
    @ApiModelProperty(value = "角色ID", name = "rId")
    @TableField(value = "u_role_id")
    @JsonProperty(value = "rId")
    private Long rId;

    // 注册IP
    @ApiModelProperty(value = "注册IP", name = "createIp")
    @TableField(value = "u_create_ip")
    @JsonProperty(value = "createIp")
    private String createIp;

    // 状态
    @ApiModelProperty(value = "状态", name = "deleted")
    @TableField(value = "is_deleted")
    @JsonProperty(value = "deleted")
    private Integer deleted;

    // 创建时间
    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonProperty(value = "gmtCreate")
    private Date gmtCreate;

    // 修改时间
    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @JsonProperty(value = "gmtModified")
    private Date gmtModified;

    // 数据库来源
    @ApiModelProperty(value = "数据库来源", name = "dbSource")
    @TableField(value = "db_source")
    @JsonProperty(value = "dbSource")
    private String dbSource;
}
