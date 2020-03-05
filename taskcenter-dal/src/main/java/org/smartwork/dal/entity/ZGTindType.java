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
@ApiModel(description="行业类型")
@TableName("fb_zg_t_ind_type")
public class ZGTindType extends BaseEntity {
    private static final long serialVersionUID = -7227676690296482053L;
    /**
     * 行业类型名称
     *
     * Table:     fb_zg_t_ind_type
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "行业类型名称",example="",required = true)
    private String name;
}