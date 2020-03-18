package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Table: fb_zg_task
 */
@Data
@ApiModel(description = "任务")
@TableName("fb_zg_task")
public class ZGTask extends BaseEntity {
    private static final long serialVersionUID = -5209454207363035362L;
    /**
     * 图标
     * <p>
     * Table:     fb_zg_task
     * Column:    t_icon
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "图标", example = "")
    private String tIcon;
=======
    @ApiModelProperty(value = "图标")
    private String icon;
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb

    /**
     * 任务名称
     * <p>
     * Table:     fb_zg_task
     * Column:    name
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务名称", example = "")
=======
    @ApiModelProperty(value = "任务名称",required = true)
    @NotEmpty(message = "任务名称为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String name;

    /**
     * 技能要求
     * <p>
     * Table:     fb_zg_task
     * Column:    skills_required
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "技能要求", example = "")
=======
    @ApiModelProperty(value = "技能要求",required = true)
    @NotEmpty(message = "技能要求为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String skillsRequired;

    /**
     * 任务起价格
     * <p>
     * Table:     fb_zg_task
     * Column:    t_start_price
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务起价格", example = "0.00")
    private BigDecimal tStartPrice;

    /**
     * 可否议价
=======
    @ApiModelProperty(value = "任务起价格", example = "0.00",required = true)
    @NotNull(message = "任务起价格为空")
    private BigDecimal startPrice;

    /**
     * 可否议价0否1是
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
     * <p>
     * Table:     fb_zg_task
     * Column:    is_bargain
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "可否议价", example = "false")
    private Boolean isBargain;
=======
    @ApiModelProperty(value = "可否议价0否1是", example = "false",required = true)
    @NotEmpty(message = "可否议价为空")
    private String isBargain;
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb

    /**
     * 发布时间
     * <p>
     * Table:     fb_zg_task
     * Column:    release_time
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "发布时间", example = "")
=======
    @ApiModelProperty(value = "发布时间,添加不传值")
    @NotNull(message = "发布时间为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private Date releaseTime;

    /**
     * 任务止价
     * <p>
     * Table:     fb_zg_task
     * Column:    t_end_price
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务止价", example = "0.00")
    private BigDecimal tEndPrice;
=======
    @ApiModelProperty(value = "任务止价", example = "0.00",required = true)
    @NotNull(message = "任务止价为空")
    private BigDecimal endPrice;
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb

    /**
     * 任务结束时间
     * <p>
     * Table:     fb_zg_task
     * Column:    t_end_time
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务结束时间", example = "")
    private Date tEndTime;
=======
    @ApiModelProperty(value = "任务结束时间", example = "0")
    private Date endTime;
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb

    /**
     * 任务期限
     * <p>
     * Table:     fb_zg_task
     * Column:    t_period
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务期限", example = "0")
    private Long tPeriod;
=======
    @ApiModelProperty(value = "任务期限,单位(月)", example = "0",required = true)
    @NotNull(message = "任务期限为空")
    private Long period;
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb

    /**
     * 会员ID
     * <p>
     * Table:     fb_zg_task
     * Column:    member_id
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "会员ID", example = "0")
=======
    @ApiModelProperty(value = "会员ID", example = "0",required = true)
    @NotNull(message = "会员ID为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private Long memberId;

    /**
     * 会员名称
     * <p>
     * Table:     fb_zg_task
     * Column:    member_name
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "会员名称", example = "")
    private String memberName;

    /**
<<<<<<< HEAD
     * 0-未发布1-发布（竞标中）2-选标中3-托管赏金4-开始工作5-提交验收6-确认验收7-支付赏金99-任务过期
     * <p>
=======
     * 0-未发布1-已下架2-发布（竞标中）3-选标中4-托管赏金5-开始工作6-提交验收7-确认验收8-支付赏金9-任务过期
     *
>>>>>>> c1a4acb33da5ac588c229fecb639c6bd5a72b282
=======
    @ApiModelProperty(value = "会员名称", example = "0",required = true)
    @NotEmpty(message = "会员名称为空")
    private String memberName;

    /**
     * 0-未发布1-发布（竞标中）2-选标中3-托管赏金4-开始工作5-提交验收6-确认验收7-支付赏金99-任务过期
     * <p>
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
     * Table:     fb_zg_task
     * Column:    task_state
     * Nullable:  true
     */
<<<<<<< HEAD
<<<<<<< HEAD
    @ApiModelProperty(value = "0-未发布1-发布（竞标中）2-选标中3-托管赏金4-开始工作5-提交验收6-确认验收7-支付赏金99-任务过期", example = "0")
    private Long taskState;
=======
    @ApiModelProperty(value = "0-未发布1-已下架2-发布（竞标中）3-选标中4-托管赏金5-开始工作6-提交验收7-确认验收8-支付赏金9-任务过期",example="0")
=======
    @ApiModelProperty(value = "0-未发布1-发布（竞标中）2-选标中3-托管赏金4-开始工作5-提交验收6-确认验收7-支付赏金99-任务过期(添加不传值)", example = "0")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String taskState;
>>>>>>> c1a4acb33da5ac588c229fecb639c6bd5a72b282

    /**
     * 行业ID
     * <p>
     * Table:     fb_zg_task
     * Column:    industry_id
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "行业ID", example = "0")
=======
    @ApiModelProperty(value = "行业ID", example = "0",required = true)
    @NotNull(message = "行业ID为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private Long industryId;

    /**
     * 任务类型名称
     * <p>
     * Table:     fb_zg_task
     * Column:    t_type_name
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务类型名称", example = "")
    private String tTypeName;
=======
    @ApiModelProperty(value = "任务类型名称", example = "",required = true)
    @NotEmpty(message = "任务类型名称为空")
    private String typeName;
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb

    /**
     * 任务类型编码
     * <p>
     * Table:     fb_zg_task
     * Column:    t_type_code
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务类型编码", example = "")
    private String tTypeCode;
=======
    @ApiModelProperty(value = "任务类型编码",required = true)
    @NotEmpty(message = "任务类型编码为空")
    private String typeCode;
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb

    /**
     * 行业名称
     * <p>
     * Table:     fb_zg_task
     * Column:    industry
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "行业名称", example = "")
=======
    @ApiModelProperty(value = "行业名称",required = true)
    @NotEmpty(message = "行业名称为空")
>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
    private String industry;

    /**
     * 任务描述
     * <p>
     * Table:     fb_zg_task
     * Column:    t_des
     * Nullable:  true
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "任务描述", example = "")
    private String tDes;
=======
    @ApiModelProperty(value = "任务描述",required = true)
    @NotEmpty(message = "任务描述为空")
    private String des;

>>>>>>> 6232757370a9bb111f84727dbc9a18cc5fbc83fb
}