package org.smartwork.comm.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 任务标签Dto
 */
@Data
public class ZGTaskTagDto implements Serializable{
    private static final long serialVersionUID = -8684810642532732488L;

    /**
     * 标签名称
     *
     * Table:     fb_zg_task_tag
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "标签名称",example="")
    private String name;
}
