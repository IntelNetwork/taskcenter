package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 任务标签中间表Dto
 */
@Data
@ApiModel(description="任务标签中间表Dto")
public class ZGTaskRelTagDto implements Serializable{
    private static final long serialVersionUID = 3900932562542326141L;
    /**
     * 标签ID
     *
     * Table:     fb_zg_task_rel_tag
     * Column:    ta_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "标签ID",example="0",required = true)
    private Long taId;

    /**
     * 任务ID
     *
     * Table:     fb_zg_task_rel_tag
     * Column:    ta_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务ID",example="0",required = true)
    private Long taskId;

    /**
     * 标签名称
     *
     * Table:     fb_zg_task_rel_tag
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "标签名称",example="",required = true)
    private String name;
}
