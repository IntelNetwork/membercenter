package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("项目传入参数")
public class ZGProjectDto implements Serializable{
    private static final long serialVersionUID = -8507477860558837152L;

    /**
     * 项目名称
     * <p>
     * Table:     fb_zg_project
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "项目名称", example = "")
    private String name;

    /**
     * 项目介绍
     * <p>
     * Table:     fb_zg_project
     * Column:    intr
     * Nullable:  true
     */
    @ApiModelProperty(value = "项目介绍", example = "")
    private String intr;

    /**
     * 开始时间
     * <p>
     * Table:     fb_zg_project
     * Column:    start_date
     * Nullable:  true
     */
    @ApiModelProperty(value = "开始时间", example = "")
    private Date startDate;

    /**
     * 结束时间
     * <p>
     * Table:     fb_zg_project
     * Column:    end_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "结束时间", example = "")
    private Date endTime;

    /**
     * Table:     fb_zg_project
     * Column:    status
     * Nullable:  true
     */
    @ApiModelProperty(value = "状态", example = "")
    private Byte status;

    /**
     * 实际开始时间
     * <p>
     * Table:     fb_zg_project
     * Column:    actual_start_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际开始时间", example = "")
    private Date actualStartTime;

    /**
     * 实际结束时间
     * <p>
     * Table:     fb_zg_project
     * Column:    actual_end_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际结束时间", example = "")
    private Date actualEndTime;

    /**
     * 进度
     * <p>
     * Table:     fb_zg_project
     * Column:    ratio
     * Nullable:  true
     */
    @ApiModelProperty(value = "进度", example = "0.00")
    private BigDecimal ratio;

    /**
     * 项目附件集合
     */
    @ApiModelProperty(value = "项目附件集合", example = "0.00")
    List<ZGProAttachDto> proAttachDtos;
}
