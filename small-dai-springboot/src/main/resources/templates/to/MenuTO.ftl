package ${springPackageName}.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "菜单TO")
public class MenuTO implements Serializable {
    @ApiModelProperty(value = "ID", name = "mId")
    @JsonProperty(value = "mId")
    private Long mId;

    @ApiModelProperty(value = "菜单名称", name = "mName")
    @JsonProperty(value = "mName")
    private String mName;

    @ApiModelProperty(value = "菜单URL", name = "mUrl")
    @JsonProperty(value = "mUrl")
    private String mUrl;

    @ApiModelProperty(value = "菜单图标", name = "mIcon")
    @JsonProperty(value = "mIcon")
    private String mIcon;

    @ApiModelProperty(value = "父ID", name = "mParentId")
    @JsonProperty(value = "mParentId")
    private Long mParentId;

    @ApiModelProperty(value = "排序", name = "mSort")
    @JsonProperty(value = "mSort")
    private Integer mSort;

    private List<MenuTO> children;
}
