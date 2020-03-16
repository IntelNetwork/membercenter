package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_project
 */
@Data
@ApiModel(description="项目")
@TableName("fb_zg_project")
public class ZGProject extends BaseEntity {
    private static final long serialVersionUID = 8379802470410876556L;
    /**
     * 项目名称
     *
     * Table:     fb_zg_project
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "项目名称",example="")
    private String name;

    /**
     * 项目介绍
     *
     * Table:     fb_zg_project
     * Column:    intr
     * Nullable:  true
     */
    @ApiModelProperty(value = "项目介绍",example="")
    private String intr;

    /**
     * 开始时间
     *
     * Table:     fb_zg_project
     * Column:    start_date
     * Nullable:  true
     */
    @ApiModelProperty(value = "开始时间",example="")
    private Date startDate;

    /**
     * 结束时间
     *
     * Table:     fb_zg_project
     * Column:    end_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "结束时间",example="")
    private Date endTime;

    /**
     * Table:     fb_zg_project
     * Column:    status
     * Nullable:  true
     */
    @ApiModelProperty(value = "",example="")
    private Byte status;

    /**
     * 实际开始时间
     *
     * Table:     fb_zg_project
     * Column:    actual_start_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际开始时间",example="")
    private Date actualStartTime;

    /**
     * 实际结束时间
     *
     * Table:     fb_zg_project
     * Column:    actual_end_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际结束时间",example="")
    private Date actualEndTime;

    /**
     * 进度
     *
     * Table:     fb_zg_project
     * Column:    ratio
     * Nullable:  true
     */
    @ApiModelProperty(value = "进度",example="0.00")
    private BigDecimal ratio;
}