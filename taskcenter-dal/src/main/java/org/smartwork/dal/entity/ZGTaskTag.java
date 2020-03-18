package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.annotations.QueryColumn;
import org.forbes.comm.annotations.ValidUnique;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;

/**
 * Table: fb_zg_task_tag
 */
@Data
@ApiModel(description = "任务标签")
@TableName("fb_zg_task_tag")
public class ZGTaskTag extends BaseEntity {
    private static final long serialVersionUID = -1450874657154153994L;
    /**
     * 标签名称
     * <p>
     * Table:     fb_zg_task_tag
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "标签名称",example="",required = true)
    @NotEmpty(message = "标签名称为空")
    @QueryColumn(column = "name",sqlKeyword = SqlKeyword.LIKE)
    @ValidUnique(column = "name",bizCode = "005006001",bizErrorMsg = "%s标签名称已经存在")
    private String name;
}