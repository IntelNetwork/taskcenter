package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel("审核任务")
public class AuditTaskDto implements Serializable{
    private static final long serialVersionUID = 1594490053469252935L;

    @ApiModelProperty(value = "任务id")
    Long id;
    @ApiModelProperty(value = "taskState,状态,1审核通过7审核未通过")
    String taskState;
}
