package top.smalldai.smalldaispringboot.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/4/3 1:44 下午
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "xml VO")
public class XmlVO implements Serializable {

    @JsonProperty(value = "sqlArgs")
    @ApiModelProperty(value = "联表查询ID的参数", name = "sqlArgs")
    private String sqlArgs;

    @JsonProperty(value = "sqlFrom")
    @ApiModelProperty(value = "联表查询ID的参数", name = "sqlFrom")
    private String sqlFrom;

    @JsonProperty(value = "sqlEqual")
    @ApiModelProperty(value = "联表查询条件的参数", name = "sqlEqual")
    private String sqlEqual;

    @JsonProperty(value = "sqlIdOne")
    @ApiModelProperty(value = "联表查询列表的ID条件", name = "sqlIdOne")
    private String sqlIdOne;

    @JsonProperty(value = "sqlIdThree")
    @ApiModelProperty(value = "联表删除的ID条件", name = "sqlIdThree")
    private String sqlIdThree;

    @JsonProperty(value = "tId")
    @ApiModelProperty(value = "联表删除的ID", name = "tId")
    private Long tId;

}
