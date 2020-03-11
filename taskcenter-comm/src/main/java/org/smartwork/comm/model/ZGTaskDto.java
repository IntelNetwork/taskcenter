package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 任务Dto
 */
@Data
@ApiModel(description="任务dto")
public class ZGTaskDto implements Serializable {

    private static final long serialVersionUID = 4382670048543648377L;

    /**
     * id
     */
    @ApiModelProperty(value = "id,添加不传值", example = "0")
    private Long id;

    /**
     * 图标
     * <p>
     * Table:     fb_zg_task
     * Column:    t_icon
     * Nullable:  true
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 任务名称
     * <p>
     * Table:     fb_zg_task
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务名称",required = true)
    @NotEmpty(message = "任务名称为空")
    private String name;

    /**
     * 技能要求
     * <p>
     * Table:     fb_zg_task
     * Column:    skills_required
     * Nullable:  true
     */
    @ApiModelProperty(value = "技能要求",required = true)
    @NotEmpty(message = "技能要求为空")
    private String skillsRequired;

    /**
     * 任务起价格
     * <p>
     * Table:     fb_zg_task
     * Column:    t_start_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务起价格", example = "0.00",required = true)
    @NotNull(message = "任务起价格为空")
    private BigDecimal startPrice;

    /**
     * 可否议价0否1是
     * <p>
     * Table:     fb_zg_task
     * Column:    is_bargain
     * Nullable:  true
     */
    @ApiModelProperty(value = "可否议价0否1是", example = "false",required = true)
    @NotEmpty(message = "可否议价为空")
    private String isBargain;

    /**
     * 发布时间
     * <p>
     * Table:     fb_zg_task
     * Column:    release_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "发布时间,添加不传值")
    @NotNull(message = "发布时间为空")
    private Date releaseTime;

    /**
     * 任务止价
     * <p>
     * Table:     fb_zg_task
     * Column:    t_end_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务止价", example = "0.00",required = true)
    @NotNull(message = "任务止价为空")
    private BigDecimal endPrice;

    /**
     * 任务结束时间
     * <p>
     * Table:     fb_zg_task
     * Column:    t_end_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务结束时间", example = "0")
    private Date endTime;

    /**
     * 任务期限
     * <p>
     * Table:     fb_zg_task
     * Column:    t_period
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务期限,单位(月)", example = "0",required = true)
    @NotNull(message = "任务期限为空")
    private Long period;

    /**
     * 会员ID
     * <p>
     * Table:     fb_zg_task
     * Column:    member_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员ID", example = "0",required = true)
    @NotNull(message = "会员ID为空")
    private Long memberId;

    /**
     * 会员名称
     * <p>
     * Table:     fb_zg_task
     * Column:    member_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员名称", example = "0",required = true)
    @NotEmpty(message = "会员名称为空")
    private String memberName;

    /**
     * 0-未发布1-发布（竞标中）2-选标中3-托管赏金4-开始工作5-提交验收6-确认验收7-支付赏金99-任务过期
     * <p>
     * Table:     fb_zg_task
     * Column:    task_state
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-未发布1-发布（竞标中）2-选标中3-托管赏金4-开始工作5-提交验收6-确认验收7-支付赏金99-任务过期(添加不传值)", example = "0")
    private String taskState;

    /**
     * 行业ID
     * <p>
     * Table:     fb_zg_task
     * Column:    industry_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "行业ID", example = "0",required = true)
    @NotNull(message = "行业ID为空")
    private Long industryId;

    /**
     * 任务类型名称
     * <p>
     * Table:     fb_zg_task
     * Column:    t_type_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务类型名称", example = "",required = true)
    @NotEmpty(message = "任务类型名称为空")
    private String typeName;

    /**
     * 任务类型编码
     * <p>
     * Table:     fb_zg_task
     * Column:    t_type_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务类型编码,添加不传值")
    private String typeCode;

    /**
     * 行业名称
     * <p>
     * Table:     fb_zg_task
     * Column:    industry
     * Nullable:  true
     */
    @ApiModelProperty(value = "行业名称",required = true)
    @NotEmpty(message = "行业名称为空")
    private String industry;

    /**
     * 任务描述
     * <p>
     * Table:     fb_zg_task
     * Column:    t_des
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务描述",required = true)
    @NotEmpty(message = "任务描述为空")
    private String des;


    /**
     * 任务附件
     */
    @ApiModelProperty(value = "任务附件(集合)")
    List<ZGTaskAttachDto> zgTaskAttachDtos;

    /**
     * 任务标签中间表
     */
    @ApiModelProperty(value = "任务标签中间表(集合)")
    List<ZGTaskRelTagDto> zgTaskRelTagDtos;


    /**
     * 制定服务方中间表
     */
    @ApiModelProperty(value = "指定服务方中间表")
    ZGTaskBidDto zgTaskBidDto;


      /**
     * 任务订单
     */
    @ApiModelProperty(value = "任务订单")
    ZGTaskOrderDto zgTaskOrderDto;


}
