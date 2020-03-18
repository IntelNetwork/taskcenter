package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 任务标签Dto
 */
@Data
@ApiModel(description="任务标签Dto")
public class ZGTaskTagDto implements Serializable{
    private static final long serialVersionUID = -8684810642532732488L;

    /**
     * id
     */
    @ApiModelProperty(value = "id,添加不传值", example = "0",required = true)
    private Long id;

    /**
     * 标签名称
     *
     * Table:     fb_zg_task_tag
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "标签名称",example="",required = true)
    @NotEmpty(message = "标签名称为空")
    private String name;
}
