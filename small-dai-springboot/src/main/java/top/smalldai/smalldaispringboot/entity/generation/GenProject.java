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
 * @Description: 生成项目文件
 * @Data:Created in 2022/3/24 3:30 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "生成项目文件")
@TableName(value = "t_gen_project")
public class GenProject implements Serializable {
    // ID
    @ApiModelProperty(value = "ID", name = "pId")
    @TableId(value = "p_id", type = IdType.AUTO)
    @JsonProperty("pId")
    private Long pId;

    // 数据库ID
    @ApiModelProperty(value = "数据库ID", name = "dId")
    @TableField(value = "d_id")
    @JsonProperty("dId")
    private Long dId;

    // 项目名中文
    @ApiModelProperty(value = "项目名中文", name = "nameZh")
    @TableField(value = "p_name_zh")
    @JsonProperty("nameZh")
    private String nameZh;

    // 项目名英文
    @ApiModelProperty(value = "项目名英文", name = "name")
    @TableField(value = "p_name")
    @JsonProperty("name")
    private String name;

    // 建文件的用户ID
    @ApiModelProperty(value = "建文件的用户ID", name = "uId")
    @TableField(value = "u_id")
    @JsonProperty("uId")
    private Long uId;

    // 项目文件存储地址
    @ApiModelProperty(value = "项目文件存储地址", name = "path")
    @TableField(value = "p_path")
    @JsonProperty("path")
    private String path;

    // spring组名
    @ApiModelProperty(value = "组名", name = "springGroup")
    @TableField(value = "spring_group")
    @JsonProperty("springGroup")
    private String springGroup;

    // spring工件名
    @ApiModelProperty(value = "工件名", name = "springArtifact")
    @TableField(value = "spring_artifact")
    @JsonProperty("springArtifact")
    private String springArtifact;

    // spring文件名
    @ApiModelProperty(value = "项目文件名", name = "springFileName")
    @TableField(value = "spring_file_name")
    @JsonProperty("springFileName")
    private String springFileName;

    // spring项目描述
    @ApiModelProperty(value = "项目描述", name = "springDescription")
    @TableField(value = "spring_description")
    @JsonProperty("springDescription")
    private String springDescription;

    // spring包名
    @ApiModelProperty(value = "包名", name = "springPackageName")
    @TableField(value = "spring_package_name")
    @JsonProperty("springPackageName")
    private String springPackageName;

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
