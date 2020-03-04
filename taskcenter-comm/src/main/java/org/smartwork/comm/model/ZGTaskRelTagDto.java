package org.smartwork.comm.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 任务标签中间表Dto
 */
@Data
public class ZGTaskRelTagDto implements Serializable{
    private static final long serialVersionUID = 3900932562542326141L;
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
     * 标签名称
     *
     * Table:     fb_zg_task_rel_tag
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "标签名称",example="")
    private String name;
}
