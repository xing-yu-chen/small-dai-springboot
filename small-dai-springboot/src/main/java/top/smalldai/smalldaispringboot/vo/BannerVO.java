package top.smalldai.smalldaispringboot.vo;

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
 * @Description: banner VO
 * @Data:Created in 2022/4/1 1:25 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "banner VO")
public class BannerVO implements Serializable {
    @JsonProperty(value = "author")
    @ApiModelProperty(value = "作者", name = "author")
    private String author;

    @JsonProperty(value = "createTime")
    @ApiModelProperty(value = "项目创建时间", name = "createTime")
    private String createTime;

    @JsonProperty(value = "dId")
    @ApiModelProperty(value = "数据库ID", name = "dId")
    private Long dId;
}
