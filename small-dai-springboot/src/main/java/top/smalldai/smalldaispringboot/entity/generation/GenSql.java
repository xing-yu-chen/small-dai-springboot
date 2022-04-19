package top.smalldai.smalldaispringboot.entity.generation;

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
 * @Description: 生成通用SQL
 * @Data:Created in 2022/3/24 3:38 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "生成通用SQL")
@TableName(value = "t_gen_sql")
public class GenSql implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "sId")
    @TableId(value = "s_id", type = IdType.AUTO)
    @JsonProperty("sId")
    private Long sId;

    // SQL类型
    @ApiModelProperty(value = "SQL类型", name = "type")
    @TableField(value = "s_type")
    @JsonProperty("type")
    private String type;

    // SQL内容
    @ApiModelProperty(value = "SQL内容", name = "content")
    @TableField(value = "s_content")
    @JsonProperty("content")
    private String content;

    // SQL描述
    @ApiModelProperty(value = "SQL描述", name = "description")
    @TableField(value = "s_desc")
    @JsonProperty("description")
    private String description;

    // 状态
    @ApiModelProperty(value = "状态", name = "deleted")
    @TableField(value = "is_deleted")
    @JsonProperty("deleted")
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
}
