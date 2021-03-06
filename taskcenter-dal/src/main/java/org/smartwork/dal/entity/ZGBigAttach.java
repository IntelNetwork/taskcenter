package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.annotations.QueryColumn;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Table: fb_zg_big_attach
 */
@Data
@ApiModel(description = "任务竞标附件")
@TableName("fb_zg_big_attach")
public class ZGBigAttach extends BaseEntity {
    private static final long serialVersionUID = 1737942080920080495L;
    /**
     * 竞标ID
     * <p>
     * Table:     fb_zg_big_attach
     * Column:    bid_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "竞标ID", example = "0", required = true)
    @NotNull(message = "竞标ID为空")
    @QueryColumn(column = "bid_id", sqlKeyword = SqlKeyword.EQ)
    private Long bidId;

    /**
     * 中文名
     * <p>
     * Table:     fb_zg_big_attach
     * Column:    cn_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "中文名", example = "", required = true)
    @NotEmpty(message = "中文名为空")
    @QueryColumn(column = "cn_name", sqlKeyword = SqlKeyword.LIKE)
    private String cnName;

    /**
     * 后缀名
     * <p>
     * Table:     fb_zg_big_attach
     * Column:    suffix
     * Nullable:  true
     */
    @ApiModelProperty(value = "后缀名", example = "")
    private String suffix;

    /**
     * 文件路径
     * <p>
     * Table:     fb_zg_big_attach
     * Column:    file_path
     * Nullable:  true
     */
    @ApiModelProperty(value = "文件路径", example = "", required = true)
    @NotEmpty(message = "文件路径为空")
    private String filePath;
}