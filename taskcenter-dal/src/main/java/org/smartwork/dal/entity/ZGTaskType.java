package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;

/**
 * Table: fb_zg_task_type
 */
@Data
@ApiModel(description = "任务类型")
@TableName("fb_zg_task_type")
public class ZGTaskType extends BaseEntity {
    private static final long serialVersionUID = 5805408493238599695L;
    /**
     * 类型编码
     * <p>
     * Table:     fb_zg_task_type
     * Column:    code
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "类型编码", example = "")
=======
    @ApiModelProperty(value = "类型编码",example="",required = true)
    @NotEmpty(message = "类型编码为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String code;

    /**
     * 名称
     * <p>
     * Table:     fb_zg_task_type
     * Column:    name
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "名称", example = "")
=======
    @ApiModelProperty(value = "名称",example="",required = true)
    @NotEmpty(message = "名称为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String name;

    /**
     * 任务说明
     * <p>
     * Table:     fb_zg_task_type
     * Column:    type_inst
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务说明", example = "")
    private String typeInst;
}