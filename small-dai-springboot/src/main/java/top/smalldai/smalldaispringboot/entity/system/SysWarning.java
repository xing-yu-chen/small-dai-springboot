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
 * @Description: 系统警告
 * @Data:Created in 2022/3/24 1:43 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "警告")
@TableName(value = "t_sys_warning")
public class SysWarning implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "wId")
    @TableId(value = "w_id", type = IdType.AUTO)
    @JsonProperty("wId")
    private Long wId;

    // 警告标题
    @ApiModelProperty(value = "警告标题", name = "name")
    @TableField(value = "w_name")
    @JsonProperty("wName")
    private String name;

    // 警告描述
    @ApiModelProperty(value = "警告描述", name = "description")
    @TableField(value = "w_desc")
    @JsonProperty("wDesc")
    private String description;

    // 警告内容
    @ApiModelProperty(value = "警告内容", name = "content")
    @TableField(value = "w_content")
    @JsonProperty("wContent")
    private String content;

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
