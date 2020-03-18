package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/***
 * 类概述:任务附件dto
 * @创建人 niehy(Frunk)
 * @创建时间 2020/2/29
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@Data
@ApiModel(description="任务附件dto")
public class ZGTaskAttachDto implements Serializable{

    private static final long serialVersionUID = 3591264373289084697L;
    /**
     * 任务ID
     *
     * Table:     fb_zg_task_attach
     * Column:    task_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务ID",example="0",required = true)
    @NotNull(message = "任务ID为空")
    private Long taskId;

    /**
     * 中文名
     *
     * Table:     fb_zg_task_attach
     * Column:    cn_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "中文名",example="",required = true)
    @NotEmpty(message = "中文名为空")
    private String cnName;

    /**
     * 后缀名
     *
     * Table:     fb_zg_task_attach
     * Column:    suffix
     * Nullable:  true
     */
    @ApiModelProperty(value = "后缀名",example="")
    private String suffix;

    /**
     * 文件路径
     *
     * Table:     fb_zg_task_attach
     * Column:    file_path
     * Nullable:  true
     */
    @ApiModelProperty(value = "文件路径",example="")
    @NotEmpty(message = "文件路径为空")
    private String filePath;
}
