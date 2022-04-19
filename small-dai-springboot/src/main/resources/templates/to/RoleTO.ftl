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
@ApiModel(description = "角色TO")
public class RoleTO implements Serializable {
    @ApiModelProperty(value = "ID", name = "rId")
    @JsonProperty(value = "rId")
    private Long rId;

    @ApiModelProperty(value = "角色名", name = "rName")
    @JsonProperty(value = "rName")
    private String rName;

    @ApiModelProperty(value = "备注", name = "rRemark")
    @JsonProperty(value = "rRemark")
    private String rRemark;

    @ApiModelProperty(value = "权限集合", name = "permissionTOList")
    @JsonProperty(value = "permissionTOList")
    private List<PermissionTO> permissionTOList;

    @ApiModelProperty(value = "菜单集合", name = "menuTOList")
    @JsonProperty(value = "menuTOList")
    private List<MenuTO> menuTOList;
}
