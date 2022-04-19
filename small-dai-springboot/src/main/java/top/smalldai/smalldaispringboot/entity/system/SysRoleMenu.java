package top.smalldai.smalldaispringboot.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @Description: 系统角色菜单
 * @Data:Created in 2022/3/24 11:28 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "角色菜单")
@TableName(value = "t_sys_role_menu")
public class SysRoleMenu implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "rmId")
    @TableId(value = "rm_id", type = IdType.AUTO)
    @JsonProperty("rmId")
    private Long rmId;

    // 角色ID
    @ApiModelProperty(value = "角色ID", name = "rId")
    @TableField(value = "r_id")
    @JsonProperty("rId")
    private Long rId;

    // 菜单ID
    @ApiModelProperty(value = "菜单ID", name = "mId")
    @TableField(value = "m_id")
    @JsonProperty("mId")
    private Long mId;

    // 状态
    @ApiModelProperty(value = "状态", name = "deleted")
    @TableField(value = "is_deleted")
    @JsonProperty("isDeleted")
    private Boolean deleted;

    // 创建时间
    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonProperty("gmtCreate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    // 修改时间
    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @JsonProperty("gmtModified")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    // 数据库来源
    @ApiModelProperty(value = "数据库来源", name = "dbSource")
    @TableField(value = "db_source")
    @JsonProperty("dbSource")
    private String dbSource;
}
