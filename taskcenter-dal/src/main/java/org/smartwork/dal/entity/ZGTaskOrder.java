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
@ApiModel(description = "任务订单")
@TableName("fb_task_order")
public class ZGTaskOrder extends BaseEntity {
    private static final long serialVersionUID = -4896763461643647603L;
    /**
     * 订单编号
     * <p>
     * Table:     fb_task_order
     * Column:    sn
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "订单编号", example = "")
    private String sn;

    /**
     * 订单编号
     * <p>
=======
    @ApiModelProperty(value = "订单编号",example="",required = true)
    @NotEmpty(message = "订单编号为空")
    private String sn;

    /**
     * 任务会员编号
     *
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
     * Table:     fb_task_order
     * Column:    task_member_id
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "订单编号", example = "0")
=======
    @ApiModelProperty(value = "任务会员编号",example="0",required = true)
    @NotNull(message = "任务会员编号为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private Long taskMemberId;

    /**
     * 任务会员名称
     * <p>
     * Table:     fb_task_order
     * Column:    task_member_name
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务会员名称", example = "")
=======
    @ApiModelProperty(value = "任务会员名称",example="",required = true)
    @NotEmpty(message = "任务会员名称为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String taskMemberName;

    /**
     * 任务名称
     * <p>
     * Table:     fb_task_order
     * Column:    task_name
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务名称", example = "")
=======
    @ApiModelProperty(value = "任务名称",example="",required = true)
    @NotEmpty(message = "任务名称为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String taskName;

    /**
     * 托管金额
     * <p>
     * Table:     fb_task_order
     * Column:    host_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "托管金额", example = "0.00")
    private BigDecimal hostAmount;

    /**
     * 实际收款
     * <p>
     * Table:     fb_task_order
     * Column:    actual_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际收款", example = "0.00")
    private BigDecimal actualAmount;

    /**
     * 提点金额
     * <p>
     * Table:     fb_task_order
     * Column:    point_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "提点金额", example = "0.00")
    private BigDecimal pointAmount;

    /**
     * 会员ID
     * <p>
     * Table:     fb_task_order
     * Column:    member_id
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "会员ID", example = "0")
=======
    @ApiModelProperty(value = "会员ID",example="0",required = true)
    @NotNull(message = "会员id为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private Long memberId;

    /**
     * 会员名称
     * <p>
     * Table:     fb_task_order
     * Column:    member_name
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "会员名称", example = "")
=======
    @ApiModelProperty(value = "会员名称",example="",required = true)
    @NotEmpty(message = "会员名称为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String memberName;

    /**
     * 0-资金托管1-开始工作2-开始验收3-确认验收99-异常状态
     * <p>
     * Table:     fb_task_order
     * Column:    order_status
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "0-资金托管1-开始工作2-开始验收3-确认验收99-异常状态", example = "0")
    private Long orderStatus;
=======
    @ApiModelProperty(value = "0-资金托管1-开始工作2-开始验收3-确认验收99-异常状态",example="0")
    private String orderStatus;
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb

    /**
     * 0-未支付1--已支付3-支付异常
     * <p>
     * Table:     fb_task_order
     * Column:    pay_status
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "0-未支付1--已支付3-支付异常", example = "0")
    private Long payStatus;
=======
    @ApiModelProperty(value = "0-未支付1--已支付3-支付异常",example="0")
    private String payStatus;
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
}