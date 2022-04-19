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
@ApiModel(description = "权限表")
@TableName(value = "t_permission")
public class Permission implements Serializable {

    @ApiModelProperty(value = "ID", name = "pId")
    @TableId(value = "p_id", type = IdType.AUTO)
    private Long pId;

    @ApiModelProperty(value = "权限名称", name = "pName")
    @TableField(value = "p_name")
    private String pName;

    @ApiModelProperty(value = "备注", name = "pRemark")
    @TableField(value = "p_remark")
    private String pRemark;

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
