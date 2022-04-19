package top.smalldai.smalldaispringboot.to;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
import top.smalldai.smalldaispringboot.entity.system.SysMenu;
import top.smalldai.smalldaispringboot.entity.system.SysPermission;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 角色TO封装
 * @Data:Created in 2022/3/27 10:56 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "用户角色TO")
public class RoleTO implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "rId")
    @JsonProperty(value = "rId")
    private Long rId;

    // 英文角色名
    @ApiModelProperty(value = "英文角色名", name = "nameEn")
    @JsonProperty(value = "nameEn")
    private String nameEn;

    // 中文角色名
    @ApiModelProperty(value = "中文角色名", name = "nameZh")
    @JsonProperty(value = "nameZh")
    private String nameZh;

    // 状态
    @ApiModelProperty(value = "状态", name = "deleted")
    @JsonProperty(value = "deleted")
    private Boolean deleted;

    // 创建时间
    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @JsonProperty(value = "gmtCreate")
    private Date gmtCreate;

    // 修改时间
    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @JsonProperty(value = "gmtModified")
    private Date gmtModified;

    // 数据库来源
    @ApiModelProperty(value = "数据库来源", name = "dbSource")
    @JsonProperty(value = "dbSource")
    private Date dbSource;

    // 权限数据
    @ApiModelProperty(value = "权限集合", name = "sysPermissions")
    @JsonProperty(value = "permissionTOS")
    private List<PermissionTO> permissionTOS;

    // 菜单集合
    @ApiModelProperty(value = "菜单集合", name = "sysMenus")
    @JsonProperty(value = "menuTOS")
    private List<MenuTO> menuTOS;
}
