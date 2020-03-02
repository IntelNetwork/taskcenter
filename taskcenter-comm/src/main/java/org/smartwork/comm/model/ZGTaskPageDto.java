package org.smartwork.comm.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lzw
 * @date 2020/3/2 12:17
 */
@Data
public class ZGTaskPageDto implements Serializable {

    private static final long serialVersionUID = -7171433698560607582L;

    /**
     * 任务类型名称
     *
     * Table:     fb_zg_task
     * Column:    t_type_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务类型名称",example="")
    private String tTypeName;

    /**
     * 行业类型名称
     *
     * Table:     fb_zg_t_ind_type
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "行业类型名称",example="")
    private String name;


}
