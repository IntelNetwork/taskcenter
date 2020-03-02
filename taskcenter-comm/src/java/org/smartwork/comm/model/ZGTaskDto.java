package src.java.org.smartwork.comm.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.smartwork.dal.entity.ZGTaskAttach;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ZGTaskDto implements Serializable {

    private static final long serialVersionUID = 4382670048543648377L;
    /**
     * 图标
     *
     * Table:     fb_zg_task
     * Column:    t_icon
     * Nullable:  true
     */
    @ApiModelProperty(value = "图标",example="")
    private String tIcon;

    /**
     * 任务名称
     *
     * Table:     fb_zg_task
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务名称",example="")
    private String name;

    /**
     * 技能要求
     *
     * Table:     fb_zg_task
     * Column:    skills_required
     * Nullable:  true
     */
    @ApiModelProperty(value = "技能要求",example="")
    private String skillsRequired;

    /**
     * 任务起价格
     *
     * Table:     fb_zg_task
     * Column:    t_start_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务起价格",example="0.00")
    private BigDecimal tStartPrice;

    /**
     * 可否议价
     *
     * Table:     fb_zg_task
     * Column:    is_bargain
     * Nullable:  true
     */
    @ApiModelProperty(value = "可否议价",example="false")
    private Boolean isBargain;

    /**
     * 发布时间
     *
     * Table:     fb_zg_task
     * Column:    release_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "发布时间",example="")
    private Date releaseTime;

    /**
     * 任务止价
     *
     * Table:     fb_zg_task
     * Column:    t_end_price
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务止价",example="0.00")
    private BigDecimal tEndPrice;

    /**
     * 任务结束时间
     *
     * Table:     fb_zg_task
     * Column:    t_end_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务结束时间",example="0")
    private Date tEndTime;

    /**
     * 任务期限
     *
     * Table:     fb_zg_task
     * Column:    t_period
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务期限",example="0")
    private Long tPeriod;

    /**
     * 会员ID
     *
     * Table:     fb_zg_task
     * Column:    member_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员ID",example="0")
    private Long memberId;

    /**
     * 会员名称
     *
     * Table:     fb_zg_task
     * Column:    member_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员名称",example="0")
    private String memberName;

    /**
     * 0-未发布1-发布（竞标中）2-选标中3-托管赏金4-开始工作5-提交验收6-确认验收7-支付赏金99-任务过期
     *
     * Table:     fb_zg_task
     * Column:    task_state
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-未发布1-发布（竞标中）2-选标中3-托管赏金4-开始工作5-提交验收6-确认验收7-支付赏金99-任务过期",example="0")
    private Long taskState;

    /**
     * 行业ID
     *
     * Table:     fb_zg_task
     * Column:    industry_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "行业ID",example="0")
    private Long industryId;

    /**
     * 任务类型名称
     *
     * Table:     fb_zg_task
     * Column:    t_type_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务类型名称",example="")
    private String tTypeName;

    /**
     * 任务类型编码
     *
     * Table:     fb_zg_task
     * Column:    t_type_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务类型编码",example="")
    private String tTypeCode;

    /**
     * 行业名称
     *
     * Table:     fb_zg_task
     * Column:    industry
     * Nullable:  true
     */
    @ApiModelProperty(value = "行业名称",example="")
    private String industry;

    /**
     * 任务描述
     *
     * Table:     fb_zg_task
     * Column:    t_des
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务描述",example="")
    private String tDes;


    /**
     * 任务附件
     */
     List<ZGTaskAttachDto> zgTaskAttachDtos;
}
