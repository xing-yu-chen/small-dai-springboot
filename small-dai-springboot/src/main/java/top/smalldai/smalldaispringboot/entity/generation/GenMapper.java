package top.smalldai.smalldaispringboot.entity.generation;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: Mapper实体
 * @Data:Created in 2022/4/3 1:22 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "Mapper实体")
@TableName(value = "t_gen_mapper")
public class GenMapper implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "mId")
    @TableId(value = "m_id", type = IdType.AUTO)
    @JsonProperty("mId")
    private Long mId;

    // 表Id
    @ApiModelProperty(value = "表ID", name = "tId")
    @TableField(value = "t_id")
    @JsonProperty("tId")
    private Long tId;

    // 是否根据ID联表
    @ApiModelProperty(value = "是否根据ID联表", name = "selectByIdMore")
    @TableField(value = "m_select_by_id_more")
    @JsonProperty("selectByIdMore")
    private Boolean selectByIdMore;

    // 是否联表获取所有
    @ApiModelProperty(value = "是否联表获取所有", name = "selectListMore")
    @TableField(value = "m_select_list_more")
    @JsonProperty("selectListMore")
    private Boolean selectListMore;

    // 是否联表删除
    @ApiModelProperty(value = "联表删除", name = "deleteMore")
    @TableField(value = "m_delete_more")
    @JsonProperty("deleteMore")
    private Boolean deleteMore;

    // 状态
    @ApiModelProperty(value = "状态", name = "deleted")
    @TableField(value = "is_deleted")
    @JsonProperty("deleted")
    private Boolean deleted;

    // 创建时间
    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonProperty("gmtCreate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    // 修改时间
    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @JsonProperty("gmtModified")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;
}
