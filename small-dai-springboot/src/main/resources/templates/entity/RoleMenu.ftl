package ${springPackageName}.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@ApiModel(description = "角色菜单表")
@TableName(value = "t_role_menu")
public class RoleMenu implements Serializable {

    @ApiModelProperty(value = "ID", name = "rmId")
    @TableId(value = "rm_id")
    private Long rmId;

    @ApiModelProperty(value = "角色ID", name = "rmRoleId")
    @TableField(value = "rm_role_id")
    private Long rmRoleId;

    @ApiModelProperty(value = "菜单ID", name = "rmMenuId")
    @TableField(value = "rm_menu_id")
    private Long rmMenuId;

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
