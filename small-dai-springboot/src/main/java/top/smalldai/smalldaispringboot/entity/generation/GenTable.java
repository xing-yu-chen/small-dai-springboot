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
 * @Description: 生成表
 * @Data:Created in 2022/3/24 3:47 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "生成表")
@TableName(value = "t_gen_table")
public class GenTable implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "tId")
    @TableId(value = "t_id", type = IdType.AUTO)
    @JsonProperty(value = "tId")
    private Long tId;

    // 表名
    @ApiModelProperty(value = "表名", name = "name")
    @TableField(value = "t_name")
    @JsonProperty(value = "name")
    private String name;

    // 备注
    @ApiModelProperty(value = "备注", name = "remark")
    @TableField(value = "t_remark")
    @JsonProperty(value = "remark")
    private String remark;

    // 数据库ID
    @ApiModelProperty(value = "数据库ID", name = "dId")
    @TableField(value = "d_id")
    @JsonProperty(value = "dId")
    private Long dId;

    // 主键
    @ApiModelProperty(value = "主键", name = "primaryKey")
    @TableField(value = "t_primary_key")
    @JsonProperty(value = "primaryKey")
    private String primaryKey;

    // 建表用户ID
    @ApiModelProperty(value = "建表用户ID", name = "uId")
    @TableField(value = "u_id")
    @JsonProperty(value = "uId")
    private Long uId;

    // 状态
    @ApiModelProperty(value = "状态", name = "deleted")
    @TableField(value = "is_deleted")
    @JsonProperty(value = "deleted")
    private Boolean deleted;

    // 修改时间
    @ApiModelProperty(value = "修改时间", name = "gmtCreate")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty(value = "gmtCreate")
    private Date gmtCreate;

    // 修改时间
    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @JsonProperty(value = "gmtModified")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;
}
