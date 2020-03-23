package org.smartwork.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 任务竞标返回vo
 * @author: xfx
 * @date: Created in 2020/3/13 17:45
 * @version:
 * @modified By:
 */
@Data
@ApiModel(description="任务返回VO")
public class ZGTaskBidVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -9020907687698802443L;


    /**
     * 会员ID
     *
     * Table:     fb_zg_task_bid
     * Column:    member_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员ID",example="0",required = true)
    @NotNull(message = "会员ID为空")
    private Long memberId;

    /**
     * 会员名称
     *
     * Table:     fb_zg_task_bid
     * Column:    membe_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员名称",example="",required = true)
    @NotEmpty(message = "会员名称为空")
    private String memberName;

    /**
     * 低价
     *
     * Table:     fb_zg_task_bid
     * Column:    offe_start_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "低价",example="0.00",required = true)
    @NotNull(message = "低价为空")
    private BigDecimal offeStartPrice;

    /**
     * 止价
     *
     * Table:     fb_zg_task_bid
     * Column:    offer_end_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "止价",example="0.00",required = true)
    @NotNull(message = "止价为空")
    private BigDecimal offerEndPrice;

    /**
     * 详细说明
     *
     * Table:     fb_zg_task_bid
     * Column:    intr
     * Nullable:  true
     */
    @ApiModelProperty(value = "详细说明",example="")
    private String intr;

    /**
     * 0-否1-已中标2审核中
     *
     * Table:     fb_zg_task_bid
     * Column:    hit_state
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-否1-已中标2审核中(添加不传值)",example="false")
    private String hitState;

    /**
     * 任务id
     *
     * Table:     fb_zg_task_bid
     * Column:    task_id
     * Nullable:  true
     */

    @ApiModelProperty(value = "任务id",example="0",required = true)
    @NotNull(message = "任务id为空")
    private Long taskId;


    /**
     * 接收任务数量
     *
     * Table:     fb_zg_task_bid
     * Column:    task_id
     * Nullable:  true
     */

    @ApiModelProperty(value = "接收任务数量",example="0",required = true)
    private Integer amount;

}
