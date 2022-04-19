package top.smalldai.smalldaispringboot.to;

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
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 字段TO
 * @Data:Created in 2022/3/28 2:26 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "字段TO")
public class ColumnTO implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "tcId")
    @JsonProperty(value = "tcId")
    private Long tcId;

    // 字段名
    @ApiModelProperty(value = "字段名", name = "name")
    @JsonProperty(value = "name")
    private String name;

    // 备注
    @ApiModelProperty(value = "备注", name = "remark")
    @JsonProperty(value = "remark")
    private String remark;

    // 字段属性
    @ApiModelProperty(value = "字段属性", name = "type")
    @JsonProperty(value = "type")
    private String type;

    // 字段大小
    @ApiModelProperty(value = "字段大小", name = "size")
    @JsonProperty(value = "size")
    private Integer size;

    // 表ID
    @ApiModelProperty(value = "表ID", name = "tId")
    @JsonProperty(value = "tId")
    private Long tId;

    // 建字段用户ID
    @ApiModelProperty(value = "建字段用户ID", name = "uId")
    @JsonProperty(value = "uId")
    private Long uId;

    // 是否为主键
    @ApiModelProperty(value = "是否为主键", name = "primaryKey")
    @JsonProperty(value = "primaryKey")
    private Boolean primaryKey;

    // 是否为空
    @ApiModelProperty(value = "是否为空", name = "ifNull")
    @JsonProperty(value = "ifNull")
    private Boolean ifNull;

    // 是否自增
    @ApiModelProperty(value = "是否自增", name = "auto")
    @JsonProperty(value = "auto")
    private Boolean auto;

}
