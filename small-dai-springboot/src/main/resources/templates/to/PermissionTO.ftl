package ${springPackageName}.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "权限TO")
public class PermissionTO implements Serializable {
    @ApiModelProperty(value = "ID", name = "pId")
    @JsonProperty(value = "pId")
    private Long pId;

    @ApiModelProperty(value = "权限名称", name = "pName")
    @JsonProperty(value = "pName")
    private String pName;

    @ApiModelProperty(value = "备注", name = "pRemark")
    @JsonProperty(value ="pRemark")
    private String pRemark;
}
