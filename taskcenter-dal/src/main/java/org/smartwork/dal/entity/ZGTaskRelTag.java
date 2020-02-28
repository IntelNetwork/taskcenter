package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_task_rel_tag
 */
@Data
@ApiModel(description="任务标签关联表")
@TableName("fb_zg_task_rel_tag")
public class ZGTaskRelTag extends BaseEntity {
    /**
     * 标签ID
     *
     * Table:     fb_zg_task_rel_tag
     * Column:    ta_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "标签ID",example="0")
    private Long taId;

    /**
     * 任务ID
     *
     * Table:     fb_zg_task_rel_tag
     * Column:    task_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务ID",example="0")
    private Long taskId;

    /**
     * 标签名称
     *
     * Table:     fb_zg_task_rel_tag
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "标签名称",example="")
    private String name;
}