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
@ApiModel(description = "任务附件")
@TableName("fb_zg_task_attach")
public class ZGTaskAttach extends BaseEntity {
    private static final long serialVersionUID = -8418785453198430574L;
    /**
     * 任务ID
     * <p>
     * Table:     fb_zg_task_attach
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
     * 中文名
     * <p>
     * Table:     fb_zg_task_attach
     * Column:    cn_name
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "中文名", example = "")
=======
    @ApiModelProperty(value = "中文名",example="",required = true)
    @NotEmpty(message = "中文名为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String cnName;

    /**
     * 后缀名
     * <p>
     * Table:     fb_zg_task_attach
     * Column:    suffix
     * Nullable:  true
     */
    @ApiModelProperty(value = "后缀名", example = "")
    private String suffix;

    /**
     * 文件路径
     * <p>
     * Table:     fb_zg_task_attach
     * Column:    file_path
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "文件路径", example = "")
=======
    @ApiModelProperty(value = "文件路径",example="")
    @NotEmpty(message = "文件路径为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String filePath;
}