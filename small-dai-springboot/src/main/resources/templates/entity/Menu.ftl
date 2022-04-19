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
@ApiModel(description = "菜单表")
@TableName(value = "用户菜单")
public class Menu implements Serializable {
    @ApiModelProperty(value = "ID", name = "mId")
    @TableId(value = "m_id", type = IdType.AUTO)
    private Long mId;

    @ApiModelProperty(value = "菜单名称", name = "mName")
    @TableField(value = "m_name")
    private String mName;

    @ApiModelProperty(value = "菜单URL", name = "mUrl")
    @TableField(value = "m_url")
    private String mUrl;

    @ApiModelProperty(value = "菜单图标", name = "mIcon")
    @TableField(value = "m_icon")
    private String mIcon;

    @ApiModelProperty(value = "父ID", name = "mParentId")
    @TableField(value = "m_parent_id")
    private Long mParentId;

    @ApiModelProperty(value = "排序", name = "mSort")
    @TableField(value = "m_sort")
    private Integer mSort;

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
