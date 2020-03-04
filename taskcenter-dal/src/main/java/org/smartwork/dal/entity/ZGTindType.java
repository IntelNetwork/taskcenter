package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_t_ind_type
 */
@Data
@ApiModel(description = "行业类型")
@TableName("fb_zg_t_ind_type")
public class ZGTindType extends BaseEntity {
    /**
     * 行业类型名称
     * <p>
     * Table:     fb_zg_t_ind_type
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "行业类型名称", example = "")
    private String name;
}