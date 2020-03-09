package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_task_tag
 */
@Data
@ApiModel(description = "任务标签")
@TableName("fb_zg_task_tag")
public class ZGTaskTag extends BaseEntity {
    private static final long serialVersionUID = -1450874657154153994L;
    /**
     * 标签名称
     * <p>
     * Table:     fb_zg_task_tag
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "标签名称", example = "")
    private String name;
}