package org.smartwork.comm.vo;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.annotations.QueryColumn;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(description="任务竞标返回VO")
public class ZGBidTaskVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8599627742678380285L;

    /**
     * 会员ID
     * <p>
     * Table:     fb_zg_task_bid
     * Column:    member_id
     * Nullable:  true
     */

    @ApiModelProperty(value = "会员ID", example = "0")
    @NotNull(message = "会员ID为空")
    private Long releaseMemberId;

    /**
     * 会员名称
     * <p>
     * Table:     fb_zg_task_bid
     * Column:    membe_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员名称", example = "")
    private String releaseMemberName;

    /**
     * 低价
     * <p>
     * Table:     fb_zg_task_bid
     * Column:    offe_start_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "低价", example = "0.00")
    @QueryColumn(column = "offe_start_price",sqlKeyword = SqlKeyword.GE)
    private BigDecimal offeStartPrice;

    /**
     * 止价
     * <p>
     * Table:     fb_zg_task_bid
     * Column:    offer_end_price
     * Nullable:  true
     */

    @ApiModelProperty(value = "止价")
    @QueryColumn(column = "offer_end_price",sqlKeyword = SqlKeyword.LE)
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
     * 0-否1-已中标
     * <p>
     * 0-否1-已中标2审核中
     *
     * Table:     fb_zg_task_bid
     * Column:    hit_state
     * Nullable:  true
     */

    @ApiModelProperty(value = "0-否1-已中标2-选标中",example="0")
    @QueryColumn(column = "hit_state",sqlKeyword = SqlKeyword.EQ)
    private String hitState;

    /**
     * 任务id
     * <p>
     * Table:     fb_zg_task_bid
     * Column:    task_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务id",example="0")
    private Long taskId;

    /**
     * 任务名称
     * <p>
     * Table:     fb_zg_task
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务名称")
    private String name;

    /**
     * 0-未发布1-发布（竞标中）2-选标中3-托管赏金4-开始工作5-提交验收6-确认验收7-支付赏金99-任务过期
     * <p>
     * Table:     fb_zg_task
     * Column:    task_state
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-未发布1-发布（竞标中）2-选标中3-托管赏金4-开始工作5-提交验收6-确认验收7-支付赏金99-任务过期(添加不传值)", example = "0")
    private String taskState;

}
