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
@ApiModel(description = "角色权限表")
@TableName(value = "role_permission")
public class RolePermission implements Serializable {

    @ApiModelProperty(value = "ID", name = "rpId")
    @TableId(value = "rp_id", type = IdType.AUTO)
    private Long rpId;

    @ApiModelProperty(value = "角色ID", name = "rId")
    @TableField(value = "r_id")
    private Long rId;

    @ApiModelProperty(value = "权限ID", name = "pId")
    @TableField(value = "p_id")
    private Long pId;

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
