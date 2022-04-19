package top.smalldai.smalldaispringboot.to;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 表信息TO类
 * @Data:Created in 2022/3/28 1:45 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "表TO")
public class TableTO implements Serializable {
    // ID
    // 字段TO
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModelProperty(value = "ID", name = "tId")
    @JsonProperty(value = "tId")
    private Long tId;

    // 表名
    @ApiModelProperty(value = "表名", name = "name")
    @JsonProperty(value = "name")
    private String name;

    // 备注
    @ApiModelProperty(value = "备注", name = "remark")
    @JsonProperty(value = "remark")
    private String remark;

    // 数据库ID
    @ApiModelProperty(value = "数据库ID", name = "dId")
    @JsonProperty(value = "dId")
    private Long dId;

    // 主键
    @ApiModelProperty(value = "主键", name = "primaryKey")
    @JsonProperty(value = "primaryKey")
    private String primaryKey;

    // 建表用户ID
    @ApiModelProperty(value = "建表用户ID", name = "uId")
    @JsonProperty(value = "uId")
    private Long uId;

    // 字段TO
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModelProperty(value = "字段TO", name = "columnTOS")
    @JsonProperty(value = "columnTOS")
    private List<ColumnTO> columnTOS;

}
