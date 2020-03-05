package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

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
    @ApiModelProperty(value = "订单编号",example="")
    private String sn;

    /**
     * 任务会员ID
     *
     * Table:     fb_task_order
     * Column:    task_member_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务会员ID",example="0",required = true)
    private Long taskMemberId;

    /**
     * 任务会员名称
     *
     * Table:     fb_task_order
     * Column:    task_member_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务会员名称",example="",required = true)
    private String taskMemberName;

    /**
     * 任务名称
     *
     * Table:     fb_task_order
     * Column:    task_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务名称",example="",required = true)
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
    private Long memberId;

    /**
     * 会员名称
     *
     * Table:     fb_task_order
     * Column:    member_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员名称",example="",required = true)
    private String memberName;

    /**
     * 0-资金托管1-开始工作2-开始验收3-确认验收99-异常状态
     *
     * Table:     fb_task_order
     * Column:    order_status
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-资金托管1-开始工作2-开始验收3-确认验收99-异常状态(添加不传值)",example="0")
    private Long orderStatus;

    /**
     * 0-未支付1--已支付3-支付异常
     *
     * Table:     fb_task_order
     * Column:    pay_status
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-未支付1--已支付3-支付异常(添加不传值)",example="0")
    private Long payStatus;
}