package org.smartwork.comm.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ZGTaskBidDto implements Serializable{

    private static final long serialVersionUID = 7402520219650107403L;
    /**
     * 会员ID
     *
     * Table:     fb_zg_task_bid
     * Column:    member_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员ID",example="0")
    private Long memberId;

    /**
     * 会员名称
     *
     * Table:     fb_zg_task_bid
     * Column:    membe_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员名称",example="")
    private String membeName;

    /**
     * 低价
     *
     * Table:     fb_zg_task_bid
     * Column:    offe_start_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "低价",example="0.00")
    private BigDecimal offeStartPrice;

    /**
     * 止价
     *
     * Table:     fb_zg_task_bid
     * Column:    offer_end_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "止价",example="0.00")
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
     * 0-否1-已中标
     *
     * Table:     fb_zg_task_bid
     * Column:    hit_state
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-否1-已中标",example="false")
    private String hitState;

    /**
     * 任务id
     *
     * Table:     fb_zg_task_bid
     * Column:    task_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务id",example="0")
    private Long taskId;

    /**
     * 任务竞标附件
     */
    private List<ZGBigAttachDto> zgBigAttachDtos;
}
