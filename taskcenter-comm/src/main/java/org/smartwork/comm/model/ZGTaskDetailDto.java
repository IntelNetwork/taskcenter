package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: TODO 任务详情dto
 * @author: xfx
 * @date: Created in 2020/3/6 16:52
 * @version: 1.0
 * @modified By:
 */
@Data
@ApiModel(description="任务详情dto")
public class ZGTaskDetailDto implements Serializable {

    private static final long serialVersionUID = 5290924348914325953L;

    /**
     * id
     */
    @ApiModelProperty(value = "id,添加不传值", example = "0")
    @NotNull(message = "任务id不能为空")
    private Long id;


    /**
     * userId
     */
    @ApiModelProperty(value = "userId,添加不传值", example = "0")
    @NotEmpty(message = "用户id不能为空")
    private Long  userId;

    /**
     * memberName
     */
    @ApiModelProperty(value = "memberName,添加不传值", example = "0")
    private String  memberName;
}
