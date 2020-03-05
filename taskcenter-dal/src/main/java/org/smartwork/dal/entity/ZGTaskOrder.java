package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Table: fb_task_order
 */
@Data
@ApiModel(description="任务订单")
@TableName("fb_task_order")
public class ZGTaskOrder extends BaseEntity {
    private static final long serialVersionUID = -4896763461643647603L;
    /**
     * 订单编号
     *
     * Table:     fb_task_order
     * Column:    sn
     * Nullable:  true
     */
    @ApiModelProperty(value = "订单编号",example="",required = true)
    @NotEmpty(message = "订单编号为空")
    private String sn;

    /**
     * 任务会员编号
     *
     * Table:     fb_task_order
     * Column:    task_member_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务会员编号",example="0",required = true)
    @NotNull(message = "任务会员编号为空")
    private Long taskMemberId;

    /**
     * 任务会员名称
     *
     * Table:     fb_task_order
     * Column:    task_member_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务会员名称",example="",required = true)
    @NotEmpty(message = "任务会员名称为空")
    private String taskMemberName;

    /**
     * 任务名称
     *
     * Table:     fb_task_order
     * Column:    task_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务名称",example="",required = true)
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
    @ApiModelProperty(value = "实际收款",example="0.00")
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
    @ApiModelProperty(value = "会员ID",example="0",required = true)
    @NotNull(message = "会员id为空")
    private Long memberId;

    /**
     * 会员名称
     *
     * Table:     fb_task_order
     * Column:    member_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员名称",example="",required = true)
    @NotEmpty(message = "会员名称为空")
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