package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Table: fb_zg_task_rel_tag
 */
@Data
@ApiModel(description = "任务标签关联表")
@TableName("fb_zg_task_rel_tag")
public class ZGTaskRelTag extends BaseEntity {
    private static final long serialVersionUID = -3484337919941762637L;
    /**
     * 标签ID
     * <p>
     * Table:     fb_zg_task_rel_tag
     * Column:    ta_id
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "标签ID", example = "0")
=======
    @ApiModelProperty(value = "标签ID",example="0",required = true)
    @NotNull(message = "标签ID为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private Long taId;

    /**
     * 任务ID
     * <p>
     * Table:     fb_zg_task_rel_tag
     * Column:    task_id
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务ID", example = "0")
=======
    @ApiModelProperty(value = "任务ID",example="0",required = true)
    @NotNull(message = "任务ID为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private Long taskId;

    /**
     * 标签名称
     * <p>
     * Table:     fb_zg_task_rel_tag
     * Column:    name
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "标签名称", example = "")
=======
    @ApiModelProperty(value = "标签名称",example="",required = true)
    @NotEmpty(message = "标签名称为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String name;
}