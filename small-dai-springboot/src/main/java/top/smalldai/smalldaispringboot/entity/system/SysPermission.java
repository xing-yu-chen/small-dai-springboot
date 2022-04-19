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
 * @Description: 系统权限
 * @Data:Created in 2022/3/24 11:44 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "权限")
@TableName(value = "t_sys_permission")
public class SysPermission implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "pId")
    @TableId(value = "p_id", type = IdType.AUTO)
    @JsonProperty("pId")
    private Long pId;

    // 英文权限名
    @ApiModelProperty(value = "英文权限名", name = "nameEn")
    @TableField(value = "p_name_en")
    @JsonProperty("pNameEn")
    private String nameEn;

    // 中文权限名
    @ApiModelProperty(value = "中文权限名", name = "nameZh")
    @TableField(value = "p_name_zh")
    @JsonProperty("pNameZh")
    private String nameZh;

    // URL路径
    @ApiModelProperty(value = "URL路径", name = "url")
    @TableField(value = "p_url")
    @JsonProperty("pUrl")
    private String url;

    // 状态
    @ApiModelProperty(value = "I状态D", name = "deleted")
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
