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
 * @Description: 生成SQL脚本
 * @Data:Created in 2022/3/24 3:43 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "生成SQL脚本")
@TableName(value = "t_gen_sql_script")
public class GenSqlScript implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "ssId")
    @TableId(value = "ss_id", type = IdType.AUTO)
    @JsonProperty("ssId")
    private Long ssId;

    // SQL脚本名称
    @ApiModelProperty(value = "SQL脚本名称", name = "name")
    @TableField(value = "ss_name")
    @JsonProperty("name")
    private String name;

    // SQL文件存储路径
    @ApiModelProperty(value = "SQL文件存储路径", name = "path")
    @TableField(value = "ss_path")
    @JsonProperty("path")
    private String path;

    // 来源数据库ID
    @ApiModelProperty(value = "来源数据库ID", name = "dId")
    @TableField(value = "d_id")
    @JsonProperty("dId")
    private Long dId;

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
