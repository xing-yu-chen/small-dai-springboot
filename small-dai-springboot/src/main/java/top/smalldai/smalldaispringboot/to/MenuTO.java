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
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 返回给用户的菜单TO
 * @Data:Created in 2022/3/27 11:36 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "菜单TO")
public class MenuTO implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "mId")
    @JsonProperty(value = "mId")
    private Long mId;

    // 英文菜单名
    @ApiModelProperty(value = "英文菜单名", name = "nameEn")
    @JsonProperty(value = "nameEn")
    private String nameEn;

    // 中文菜单名
    @ApiModelProperty(value = "中文菜单名", name = "nameZh")
    @JsonProperty(value = "nameZh")
    private String nameZh;

    // 菜单地址
    @ApiModelProperty(value = "菜单地址", name = "url")
    @JsonProperty(value = "url")
    private String url;

    // 父ID
    @ApiModelProperty(value = "父ID", name = "parentId")
    @JsonProperty(value = "parentId")
    private Long parentId;

    // 已提供图标的英文名
    @ApiModelProperty(value = "已提供图标的英文名", name = "iconName")
    @JsonProperty(value = "iconName")
    private String iconName;

    // 自定义图标URL
    @ApiModelProperty(value = "自定义图标URL", name = "iconUrl")
    @JsonProperty(value = "iconUrl")
    private String iconUrl;

    // 菜单TO集合
    @ApiModelProperty(value = "菜单TO集合", name = "menuTOS")
    @JsonProperty(value = "menuTOS")
    private List<MenuTO> menuTOS;
}
