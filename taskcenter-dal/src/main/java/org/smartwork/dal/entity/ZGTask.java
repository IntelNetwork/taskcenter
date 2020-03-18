package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import org.forbes.comm.annotations.QueryColumn;
import org.forbes.comm.annotations.ValidUnique;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
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
    @ApiModelProperty(value = "图标", example = "")
    private String tIcon;

    /**
     * 任务名称
     * <p>
     * Table:     fb_zg_task
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务名称",required = true)
    @ValidUnique(column = "ad_code",bizCode = "005001004",bizErrorMsg = "%s编码已经存在")
    @NotEmpty(message = "任务名称为空",groups = {UpdateValid.class, SaveValid.class})
    @QueryColumn(column = "name",sqlKeyword = SqlKeyword.LIKE)
    private String name;

    /**
     * 技能要求
     * <p>
     * Table:     fb_zg_task
     * Column:    skills_required
     * Nullable:  true
     */
    @ApiModelProperty(value = "技能要求", example = "")
    @NotEmpty(message = "技能要求为空")
    private String skillsRequired;

    /**
     * 任务起价格
     * <p>
     * Table:     fb_zg_task
     * Column:    t_start_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务起价格", example = "0.00")
    private BigDecimal tStartPrice;

    /**
     * 可否议价
     * <p>
     * Table:     fb_zg_task
     * Column:    is_bargain
     * Nullable:  true
     */
    @ApiModelProperty(value = "可否议价0否1是", example = "0",required = true)
    @NotEmpty(message = "可否议价为空")
    private String isBargain;

    /**
     * 发布时间
     * <p>
     * Table:     fb_zg_task
     * Column:    release_time
     * Nullable:  true
     */

    @JsonFormat(pattern="yyyy-MM-dd")
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
     * 竞标结束时间
     * <p>
     * Table:     fb_zg_task
     * Column:    t_end_time
     * Nullable:  true
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "竞标结束时间", example = "0",required = true)
    private Date endTime;


    /**
     * 任务期限
     * <p>
     * Table:     fb_zg_task
     * Column:    t_period
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务期限", example = "0")
    private Long tPeriod;
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
    @ApiModelProperty(value = "会员ID", example = "0")
    private Long memberId;

    /**
     * 会员名称
     * <p>
     * Table:     fb_zg_task
     * Column:    member_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员名称", example = "")
    private String memberName;


    /**
     * 0-待审核1-竞标中2-托管赏金3-开始工作4-提交验收5-确认验收6-支付赏金7-审核未通过 99-任务过期(添加不传值)
     * <p>
     * Table:     fb_zg_task
     * Column:    task_state
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-待审核1-竞标中2-托管赏金3-开始工作4-提交验收5-确认验收6-支付赏金7-审核未通过 99-任务过期(添加不传值)", example = "0")
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
    @QueryColumn(column = "type_name",sqlKeyword = SqlKeyword.LIKE)
    private String typeName;

    /**
     * 任务类型编码
     * <p>
     * Table:     fb_zg_task
     * Column:    t_type_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务类型编码",required = true)
    @NotEmpty(message = "任务类型编码为空")
    private String typeCode;

    /**
     * 行业名称
     * <p>
     * Table:     fb_zg_task
     * Column:    industry
     * Nullable:  true
     */
    @NotEmpty(message = "行业名称为空")
    @ApiModelProperty(value = "行业名称")
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
}