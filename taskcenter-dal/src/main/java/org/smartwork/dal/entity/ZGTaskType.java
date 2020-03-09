package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

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
    @ApiModelProperty(value = "类型编码", example = "")
    private String code;

    /**
     * 名称
     * <p>
     * Table:     fb_zg_task_type
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "名称", example = "")
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