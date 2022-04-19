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
@ApiModel(description = "角色表")
@TableName(value = "t_role")
public class Role implements Serializable {
    @ApiModelProperty(value = "ID", name = "rId")
    @TableId(value = "r_id", type = IdType.AUTO)
    private Long rId;

    @ApiModelProperty(value = "角色名", name = "rName")
    @TableField(value = "r_name")
    private String rName;

    @ApiModelProperty(value = "备注", name = "rRemark")
    @TableField(value = "r_remark")
    private String rRemark;

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
