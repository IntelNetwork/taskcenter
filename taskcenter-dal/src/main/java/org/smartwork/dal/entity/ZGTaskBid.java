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
 * Table: fb_zg_task_bid
 */
@Data
@ApiModel(description = "任务竞标记录")
@TableName("fb_zg_task_bid")
public class ZGTaskBid extends BaseEntity {
    private static final long serialVersionUID = 6751914535022694502L;
    /**
     * 会员ID
     * <p>
     * Table:     fb_zg_task_bid
     * Column:    member_id
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "会员ID", example = "0")
=======
    @ApiModelProperty(value = "会员ID",example="0",required = true)
    @NotNull(message = "会员ID为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private Long memberId;

    /**
     * 会员名称
     * <p>
     * Table:     fb_zg_task_bid
     * Column:    membe_name
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "会员名称", example = "")
=======
    @ApiModelProperty(value = "会员名称",example="",required = true)
    @NotEmpty(message = "会员名称为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String membeName;

    /**
     * 低价
     * <p>
     * Table:     fb_zg_task_bid
     * Column:    offe_start_price
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "低价", example = "0.00")
=======
    @ApiModelProperty(value = "低价",example="0.00",required = true)
    @NotNull(message = "低价为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private BigDecimal offeStartPrice;

    /**
     * 止价
     * <p>
     * Table:     fb_zg_task_bid
     * Column:    offer_end_price
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "止价", example = "0.00")
=======
    @ApiModelProperty(value = "止价",example="0.00",required = true)
    @NotNull(message = "止价为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private BigDecimal offerEndPrice;

    /**
     * 详细说明
     * <p>
     * Table:     fb_zg_task_bid
     * Column:    intr
     * Nullable:  true
     */
    @ApiModelProperty(value = "详细说明", example = "")
    private String intr;

    /**
<<<<<<< HEAD
     * 0-否1-已中标
     * <p>
=======
     * 0-否1-已中标2审核中
     *
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
     * Table:     fb_zg_task_bid
     * Column:    hit_state
     * Nullable:  true
     */
<<<<<<< HEAD
<<<<<<< HEAD
    @ApiModelProperty(value = "0-否1-已中标", example = "false")
    private Boolean hitState;
=======
    @ApiModelProperty(value = "0-否1-已中标",example="false")
=======
    @ApiModelProperty(value = "0-否1-已中标2审核中(添加不传值)",example="false")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String hitState;
>>>>>>> c1a4acb33da5ac588c229fecb639c6bd5a72b282

    /**
     * 任务id
     * <p>
     * Table:     fb_zg_task_bid
     * Column:    task_id
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务id", example = "0")
=======

    @ApiModelProperty(value = "任务id",example="0",required = true)
    @NotNull(message = "任务id为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private Long taskId;
}