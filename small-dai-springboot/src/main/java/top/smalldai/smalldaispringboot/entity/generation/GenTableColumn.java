package top.smalldai.smalldaispringboot.entity.generation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 生成表字段名
 * @Data:Created in 2022/3/24 3:52 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "生成表字段名")
@TableName(value = "t_gen_table_column")
public class GenTableColumn implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "tcId")
    @TableId(value = "tc_id", type = IdType.AUTO)
    @JsonProperty("tcId")
    private Long tcId;

    // 字段名
    @ApiModelProperty(value = "字段名", name = "name")
    @TableField(value = "tc_name")
    @JsonProperty("name")
    private String name;

    // 备注
    @ApiModelProperty(value = "备注", name = "remark")
    @TableField(value = "tc_remark")
    @JsonProperty("remark")
    private String remark;

    // 字段属性
    @ApiModelProperty(value = "字段属性", name = "type")
    @TableField(value = "tc_type")
    @JsonProperty("type")
    private String type;

    // 字段大小
    @ApiModelProperty(value = "字段大小", name = "size")
    @TableField(value = "tc_size")
    @JsonProperty("size")
    private Integer size;

    // 表ID
    @ApiModelProperty(value = "表ID", name = "tId")
    @TableField(value = "t_id")
    @JsonProperty("tId")
    private Long tId;

    // 建字段用户ID
    @ApiModelProperty(value = "建字段用户ID", name = "uId")
    @TableField(value = "u_id")
    @JsonProperty("uId")
    private Long uId;

    // 是否为主键
    @ApiModelProperty(value = "是否为主键", name = "primaryKey")
    @TableField(value = "tc_primary_key")
    @JsonProperty("primaryKey")
    private Boolean primaryKey;

    // 是否为空
    @ApiModelProperty(value = "是否为空", name = "ifNull")
    @TableField(value = "tc_if_null")
    @JsonProperty("ifNull")
    private Boolean ifNull;

    // 是否自增
    @ApiModelProperty(value = "是否自增", name = "auto")
    @TableField(value = "tc_auto")
    @JsonProperty("auto")
    private Boolean auto;

    // 状态
    @ApiModelProperty(value = "状态", name = "deleted")
    @TableField(value = "is_deleted")
    @JsonProperty("deleted")
    private Boolean deleted;

    // 创建时间
    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField(value = "gmt_create")
    @JsonProperty("gmtCreate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    // 修改时间
    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField(value = "gmt_modified")
    @JsonProperty("gmtModified")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;
}
