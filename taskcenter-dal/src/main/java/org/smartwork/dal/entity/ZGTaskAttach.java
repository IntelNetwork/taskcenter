package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Table: fb_zg_task_attach
 */
@Data
@ApiModel(description="任务附件")
@TableName("fb_zg_task_attach")
public class ZGTaskAttach extends BaseEntity {
    private static final long serialVersionUID = -8418785453198430574L;
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