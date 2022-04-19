package top.smalldai.smalldaispringboot.vo;

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
 * @Description: 生成表VO
 * @Data:Created in 2022/3/29 3:16 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "生成表VO")
public class TableVO implements Serializable {
    // ID
    // 字段TO
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty(value = "tId")
    @ApiModelProperty(value = "ID", name = "tId")
    private Long tId;

    // 表名
    @JsonProperty(value = "name")
    @ApiModelProperty(value = "表名", name = "name")
    private String name;

    // 备注
    @JsonProperty(value = "remark")
    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;

    // 数据库ID
    @JsonProperty(value = "dId")
    @ApiModelProperty(value = "数据库ID", name = "dId")
    private Long dId;

    // 用户ID
    @JsonProperty(value = "uId")
    @ApiModelProperty(value = "用户ID", name = "uId")
    private Long uId;

}
