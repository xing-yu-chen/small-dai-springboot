package top.smalldai.smalldaispringboot.entity.generation;

import com.baomidou.mybatisplus.annotation.*;
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
 * @Description: 生成数据库
 * @Data:Created in 2022/3/24 3:09 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "生成数据库")
@TableName(value = "t_gen_database")
public class GenDatabase implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "dId")
    @TableId(value = "d_id", type = IdType.AUTO)
    @JsonProperty(value = "dId")
    private Long dId;

    // 数据库名
    @ApiModelProperty(value = "数据库名", name = "name")
    @TableField(value = "d_name")
    @JsonProperty(value = "name")
    private String name;

    // 数据库备注
    @ApiModelProperty(value = "数据库备注", name = "remark")
    @TableField(value = "d_remark")
    @JsonProperty(value = "remark")
    private String remark;

    // 建库用户ID
    @ApiModelProperty(value = "建库用户ID", name = "uId")
    @TableField(value = "u_id")
    @JsonProperty(value = "uId")
    private Long uId;

    // 数据库地址
    @ApiModelProperty(value = "数据库地址", name = "address")
    @TableField(value = "d_address")
    @JsonProperty(value = "address")
    private String address;

    // 数据库端口
    @ApiModelProperty(value = "数据库端口", name = "port")
    @TableField(value = "d_port")
    @JsonProperty(value = "port")
    private Integer port;

    // 数据库账号
    @ApiModelProperty(value = "数据库账号", name = "username")
    @TableField(value = "d_username")
    @JsonProperty(value = "username")
    private String username;

    // 数据库密码
    @ApiModelProperty(value = "数据库密码", name = "password")
    @TableField(value = "d_password")
    @JsonProperty(value = "password")
    private String password;

    // 状态
    @ApiModelProperty(value = "状态", name = "deleted")
    @TableField(value = "is_deleted")
    @JsonProperty(value = "deleted")
    private Boolean deleted;

    // 创建时间
    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonProperty(value = "gmtCreate")
    private Date gmtCreate;

    // 修改时间
    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @JsonProperty(value = "gmtModified")
    private Date gmtModified;
}
