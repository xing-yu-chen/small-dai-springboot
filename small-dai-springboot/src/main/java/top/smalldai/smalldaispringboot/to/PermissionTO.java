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

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 给用户返回的权限TO
 * @Data:Created in 2022/3/27 11:17 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "权限TO")
public class PermissionTO implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "pId")
    @JsonProperty(value = "pId")
    private Long pId;

    // 英文权限名
    @ApiModelProperty(value = "英文权限名", name = "nameEn")
    @JsonProperty(value = "nameEn")
    private String nameEn;

    // 中文权限名
    @ApiModelProperty(value = "中文权限名", name = "nameZh")
    @JsonProperty(value = "nameZh")
    private String nameZh;

    // URL路径
    @ApiModelProperty(value = "URL路径", name = "url")
    @JsonProperty(value = "url")
    private String url;
}
