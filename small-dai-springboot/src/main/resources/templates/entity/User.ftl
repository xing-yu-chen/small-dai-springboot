package ${springPackageName}.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "用户表")
@TableName(value = "t_user")
public class User implements Serializable {

    @ApiModelProperty(value = "ID", name = "uId")
    @TableId(value = "u_id", type = IdType.AUTO)
    private Long uId;

    @ApiModelProperty(value = "用户名", name = "uName")
    @TableField(value = "u_name")
    private String uName;

    @ApiModelProperty(value = "密码", name = "uPassword")
    @TableField(value = "u_password")
    private String uPassword;

    @ApiModelProperty(value = "随机盐", name = "uSalt")
    @TableField(value = "u_salt")
    private String uSalt;

    @ApiModelProperty(value = "手机号", name = "uPhone")
    @TableField(value = "u_phone")
    private String uPhone;

    @ApiModelProperty(value = "邮箱", name = "uEmail")
    @TableField(value = "u_email")
    private String uEmail;

    @ApiModelProperty(value = "角色ID", name = "uRoleId")
    @TableField(value = "u_role_id")
    private Long uRoleId;

    @ApiModelProperty(value = "状态", name = "deleted")
    @TableField(value = "is_deleted")
    private Boolean deleted;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
