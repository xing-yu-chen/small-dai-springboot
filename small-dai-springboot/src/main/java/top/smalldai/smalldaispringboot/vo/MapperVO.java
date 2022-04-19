package top.smalldai.smalldaispringboot.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/4/1 5:45 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "mapperVO")
public class MapperVO implements Serializable {

    /* 选择单表或者多表查询单对象，默认是单表false */
    @JsonProperty(value = "selectByIdMore")
    private Boolean selectByIdMore;


    /* 选择联表或单表查询所有对象, 默认是单表false */
    @JsonProperty(value = "selectListMore")
    private Boolean selectListMore;


    /* 选择联表或单表删除,默认是单表 */
    @JsonProperty(value = "deleteMore")
    private Boolean deleteMore;


    /* 表ID */
    @JsonProperty(value = "tId")
    private Long tId;
}
