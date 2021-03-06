package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.annotations.QueryColumn;
import org.forbes.comm.annotations.ValidUnique;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;

/**
 * Table: fb_zg_task_type
 */
@Data
@ApiModel(description = "任务类型")
@TableName("fb_zg_task_type")
public class ZGTaskType extends BaseEntity {
    private static final long serialVersionUID = 5805408493238599695L;
    /**
     * 类型编码
     * <p>
     * Table:     fb_zg_task_type
     * Column:    code
     * Nullable:  true
     */
    @ApiModelProperty(value = "类型编码",example="",required = true)
    @NotEmpty(message = "类型编码为空",groups ={UpdateValid.class, SaveValid.class})
    @ValidUnique(column = "code",bizCode = "005001000",bizErrorMsg = "%s任务类型编码已经存在")
    private String code;

    /**
     * 名称
     * <p>
     * Table:     fb_zg_task_type
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "名称",example="",required = true)
    @NotEmpty(message = "名称为空",groups ={UpdateValid.class, SaveValid.class})
    @ValidUnique(column = "name",bizCode = "005001001",bizErrorMsg = "%s任务类型名称已经存在")
    @QueryColumn(column = "name",sqlKeyword = SqlKeyword.LIKE)
    private String name;

    /**
     * 任务说明
     * <p>
     * Table:     fb_zg_task_type
     * Column:    type_inst
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务说明", example = "")
    private String typeInst;
}