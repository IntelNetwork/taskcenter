package org.smartwork.comm.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 任务订单Dto
 */
@Data
public class ZGTaskOrderDto implements Serializable{
    private static final long serialVersionUID = -5267894747261333375L;
    /**
     * 订单编号
     *
     * Table:     fb_task_order
     * Column:    sn
     * Nullable:  true
     */
    @ApiModelProperty(value = "订单编号",example="0")
    private String sn;

    /**
     * 任务会员ID
     *
     * Table:     fb_task_order
     * Column:    task_member_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务会员id",example="0")
    private Long taskMemberId;

    /**
     * 任务会员名称
     *
     * Table:     fb_task_order
     * Column:    task_member_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务会员名称",example="")
    private String taskMemberName;

    /**
     * 任务ID
     *
     * Table:     fb_zg_task_attach
     * Column:    task_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务ID",example="0",required = true)
    private Long taskId;

    /**
     * 任务名称
     *
     * Table:     fb_task_order
     * Column:    task_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务名称",example="")
    @NotEmpty(message = "任务名称为空")
    private String taskName;

    /**
     * 托管金额
     *
     * Table:     fb_task_order
     * Column:    host_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "托管金额",example="0.00")
    private BigDecimal hostAmount;

    /**
     * 实际收款
     *
     * Table:     fb_task_order
     * Column:    actual_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际收款",example="0.00",required = true)
    @NotNull(message = "实际收款为空")
    private BigDecimal actualAmount;

    /**
     * 提点金额
     *
     * Table:     fb_task_order
     * Column:    point_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "提点金额",example="0.00")
    private BigDecimal pointAmount;

    /**
     * 会员ID
     *
     * Table:     fb_task_order
     * Column:    member_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员ID",example="0")
    private Long memberId;

    /**
     * 会员名称
     *
     * Table:     fb_task_order
     * Column:    member_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员名称",example="")
    private String memberName;

    /**
     * 0-资金托管1-开始工作2-开始验收3-确认验收99-异常状态
     *
     * Table:     fb_task_order
     * Column:    order_status
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-资金托管1-开始工作2-开始验收3-确认验收99-异常状态",example="0")
    private String orderStatus;

    /**
     * 0-未支付1--已支付3-支付异常
     *
     * Table:     fb_task_order
     * Column:    pay_status
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-未支付1--已支付3-支付异常",example="0")
    private String payStatus;

}
