package ${springPackageName}.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "日志")
@TableName(value = "t_log")
public class Log implements Serializable {

    @ApiModelProperty(value = "ID" ,name = "lId")
    @TableId(value = "l_id", type = IdType.AUTO)
    private Long lId;

    @ApiModelProperty(value = "用户ID", name = "uId")
    @TableField(value = "l_user_id")
    private Long lUserId;

    @ApiModelProperty(value = "内容", name = "lContent")
    @TableField(value = "l_content")
    private String lContent;

    @ApiModelProperty(value = "IP地址",name = "lIp")
    @TableField(value = "l_ip")
    private String lIp;

    @ApiModelProperty(value = "创建时间",name = "gmtCreate")
    @TableField(value = "gmt_create")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间",name = "gmtModified")
    @TableField(value = "gmt_modified")
    private Date gmtModified;
}
