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
 * @Description: 定时任务
 * @Data:Created in 2022/3/24 3:03 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "定时任务")
@TableName(value = "t_sys_cron")
public class SysCron implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "cId")
    @TableId(value = "c_id", type = IdType.AUTO)
    @JsonProperty("cId")
    private Long cId;

    // 定时时间
    @ApiModelProperty(value = "定时时间", name = "time")
    @TableField(value = "c_time")
    @JsonProperty("cTime")
    private String time;

    // 命令
    @ApiModelProperty(value = "命令", name = "command")
    @TableField(value = "c_command")
    @JsonProperty(value = "cCommand")
    private String command;

    // 任务描述
    @ApiModelProperty(value = "任务描述", name = "description")
    @TableField(value = "c_desc")
    @JsonProperty(value = "cDesc")
    private String description;

    // 状态
    @ApiModelProperty(value = "状态", name = "deleted")
    @TableField(value = "is_deleted")
    @JsonProperty(value = "isDeleted")
    private Boolean deleted;

    // 创建时间
    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonProperty(value = "gmtCreate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    // 修改时间
    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @JsonProperty(value = "gmtModified")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    // 数据库来源
    @ApiModelProperty(value = "数据库来源", name = "dbSource")
    @TableField(value = "db_source")
    @JsonProperty(value = "dbSource")
    private String dbSource;
}
