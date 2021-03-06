package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(description="任务竞标dto")
public class ZGTaskBidDto implements Serializable{

    private static final long serialVersionUID = 4353077127997753814L;
    /**
     * ID
     *
     * Table:     fb_zg_task_bid
     * Column:    id
     * Nullable:  true
     */
    @ApiModelProperty(value = "ID,添加不传值",example="0")
    private Long id;

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
    @ApiModelProperty(value = "0-否1-已中标2选标中(添加不传值)",example="2")
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
    @ApiModelProperty(value = "任务竞标附件(集合)")
    private List<ZGBigAttachDto> zgBigAttachDtos;
}
