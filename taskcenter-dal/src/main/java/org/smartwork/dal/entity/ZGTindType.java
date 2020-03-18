package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.annotations.QueryColumn;
import org.forbes.comm.annotations.ValidUnique;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;

/**
 * Table: fb_zg_t_ind_type
 */
@Data
@ApiModel(description = "行业类型")
@TableName("fb_zg_t_ind_type")
public class ZGTindType extends BaseEntity {
    private static final long serialVersionUID = -7227676690296482053L;
    /**
     * 行业类型名称
     * <p>
     * Table:     fb_zg_t_ind_type
     * Column:    name
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "行业类型名称", example = "")
=======
    @ApiModelProperty(value = "行业类型名称",example="",required = true)
    @NotEmpty(message = "行业类型名称为空")
<<<<<<< HEAD
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
=======
    @ValidUnique(column = "name",bizCode = "005004001",bizErrorMsg = "%s行业类型名称已经存在")
    @QueryColumn(column = "name",sqlKeyword = SqlKeyword.LIKE)
>>>>>>> ff4fe57aee4664f9b08028366befd56a6f84d35f
    private String name;
}