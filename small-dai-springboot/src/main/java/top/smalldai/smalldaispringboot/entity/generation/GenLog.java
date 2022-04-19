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
 * @Description: 生成日志
 * @Data:Created in 2022/3/24 3:25 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "生成日志")
@TableName(value = "t_gen_log")
public class GenLog implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "lId")
    @TableId(value = "l_id", type = IdType.AUTO)
    @JsonProperty("lId")
    private Long lId;

    // 行为
    @ApiModelProperty(value = "行为", name = "action")
    @TableField(value = "l_action")
    @JsonProperty("action")
    private String action;

    // SQL
    @ApiModelProperty(value = "SQL", name = "sql")
    @TableField(value = "l_content")
    @JsonProperty("sql")
    private String sqlContent;

    // IP地址
    @ApiModelProperty(value = "IP地址", name = "ip")
    @TableField(value = "l_ip")
    @JsonProperty("ip")
    private String ip;

    // 用户ID
    @ApiModelProperty(value = "用户ID", name = "uId")
    @TableField(value = "u_id")
    @JsonProperty("uId")
    private Long uId;

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

    // 数据库来源
    @ApiModelProperty(value = "数据库来源", name = "dbSource")
    @TableField(value = "db_source")
    @JsonProperty("dbSource")
    private String dbSource;

}
