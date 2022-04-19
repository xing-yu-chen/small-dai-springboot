package top.smalldai.smalldaispringboot.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Description: 客户端传入的数据库VO
 * @Data:Created in 2022/3/28 9:38 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "数据库VO")
public class DatabaseVO implements Serializable {

    // 数据库备注
    @ApiModelProperty(value = "数据库备注", name = "remark")
    @JsonProperty(value = "remark")
    private String remark;

    // 建库用户ID
    @ApiModelProperty(value = "建库用户ID", name = "uId")
    @JsonProperty(value = "uId")
    private Long uId;

    // 数据库地址
    @ApiModelProperty(value = "数据库地址", name = "address")
    @JsonProperty(value = "address")
    private String address;

    // 数据库端口
    @ApiModelProperty(value = "数据库端口", name = "port")
    @JsonProperty(value = "port")
    private Integer port;

    // 数据库账号
    @ApiModelProperty(value = "数据库账号", name = "username")
    @JsonProperty(value = "username")
    private String username;

    // 数据库密码
    @ApiModelProperty(value = "数据库密码", name = "password")
    @JsonProperty(value = "password")
    private String password;

    // spring组名
    @ApiModelProperty(value = "组名", name = "springGroup")
    @JsonProperty(value = "springGroup")
    private String springGroup;

    // spring工件名
    @ApiModelProperty(value = "工件名", name = "springArtifact")
    @JsonProperty(value = "springArtifact")
    private String springArtifact;

    // spring项目描述
    @ApiModelProperty(value = "项目描述", name = "springDescription")
    @JsonProperty(value = "springDescription")
    private String springDescription;

}
