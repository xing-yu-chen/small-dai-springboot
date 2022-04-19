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
 * @Description: 系统菜单
 * @Data:Created in 2022/3/24 1:50 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "菜单")
@TableName(value = "t_sys_menu")
public class SysMenu implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "mId")
    @TableId(value = "m_id", type = IdType.AUTO)
    @JsonProperty("mId")
    private Long mId;

    // 英文菜单名
    @ApiModelProperty(value = "英文菜单名", name = "nameEn")
    @TableField(value = "m_name_en")
    @JsonProperty("mNameEn")
    private String nameEn;

    // 中文菜单名
    @ApiModelProperty(value = "中文菜单名", name = "nameZh")
    @TableField(value = "m_name_zh")
    @JsonProperty("mNameZh")
    private String nameZh;

    // 菜单地址
    @ApiModelProperty(value = "菜单地址", name = "url")
    @TableField(value = "m_url")
    @JsonProperty("mUrl")
    private String url;

    // 父ID
    @ApiModelProperty(value = "父ID", name = "parentId")
    @TableField(value = "m_parent_id")
    @JsonProperty("mParentId")
    private Long parentId;

    // 已提供图标的英文名
    @ApiModelProperty(value = "已提供图标的英文名", name = "iconName")
    @TableField(value = "m_icon_name")
    @JsonProperty("mIconName")
    private String iconName;

    // 自定义图标URL
    @ApiModelProperty(value = "自定义图标URL", name = "iconUrl")
    @TableField(value = "m_icon_url")
    @JsonProperty("mIconUrl")
    private String iconUrl;

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
