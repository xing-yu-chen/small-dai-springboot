package top.smalldai.smalldaispringboot.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 表字段VO
 * @Data:Created in 2022/3/29 4:05 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "表字段VO")
public class ColumnVO implements Serializable {

    // ID
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "tcId")
    @ApiModelProperty(value = "ID", name = "tcId")
    private Long tcId;

    // 字段名
    @JsonProperty(value = "name")
    @ApiModelProperty(value = "字段名", name = "name")
    private String name;

    // 备注
    @JsonProperty(value = "remark")
    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;

    // 字段属性
    @JsonProperty(value = "type")
    @ApiModelProperty(value = "字段属性", name = "type")
    private String type;

    // 字段大小
    @JsonProperty(value = "size")
    @ApiModelProperty(value = "字段大小", name = "size")
    private Integer size;

    // 表ID
    @JsonProperty(value = "tId")
    @ApiModelProperty(value = "表ID", name = "tId")
    private Long tId;

    // 建字段用户ID
    @JsonProperty(value = "uId")
    @ApiModelProperty(value = "建字段用户ID", name = "uId")
    private Long uId;

    // 是否为主键
    @JsonProperty(value = "primaryKey")
    @ApiModelProperty(value = "是否为主键", name = "primaryKey")
    private Boolean primaryKey;

    // 是否为空
    @JsonProperty(value = "ifNull")
    @ApiModelProperty(value = "是否为空", name = "ifNull")
    private Boolean ifNull;

    // 是否自增
    @JsonProperty(value = "auto")
    @ApiModelProperty(value = "是否自增", name = "auto")
    private Boolean auto;
}
