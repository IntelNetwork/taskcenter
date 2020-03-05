package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description="任务竞标附件dto")
public class ZGBigAttachDto implements Serializable{
    private static final long serialVersionUID = -3969157704776621125L;

    /**
     * 竞标ID
     *
     * Table:     fb_zg_big_attach
     * Column:    bid_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "竞标ID",example="0",required = true)
    private Long bidId;

    /**
     * 中文名
     *
     * Table:     fb_zg_big_attach
     * Column:    cn_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "中文名",example="",required = true)
    private String cnName;

    /**
     * 后缀名
     *
     * Table:     fb_zg_big_attach
     * Column:    suffix
     * Nullable:  true
     */
    @ApiModelProperty(value = "后缀名",example="")
    private String suffix;

    /**
     * 文件路径
     *
     * Table:     fb_zg_big_attach
     * Column:    file_path
     * Nullable:  true
     */
    @ApiModelProperty(value = "文件路径",example="",required = true)
    private String filePath;
}
