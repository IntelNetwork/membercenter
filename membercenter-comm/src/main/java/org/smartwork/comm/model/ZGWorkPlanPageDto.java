package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Table: fb_zg_work_plan
 * @author Administrator
 */
@Data
@ApiModel(value = "工作计划分页参数")
public class ZGWorkPlanPageDto implements Serializable {

    private static final long serialVersionUID = 1148004460600484385L;

    /**
     * 人员id
     *
     * Table:     fb_zg_work_plan
     * Column:    user_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "人员id",example="0")
    private Long userId;

    /**
     * 姓名
     *
     * Table:     fb_zg_work_plan
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "姓名",example="")
    private String name;

    /**
     * 标题
     *
     * Table:     fb_zg_work_plan
     * Column:    title
     * Nullable:  true
     */
    @ApiModelProperty(value = "标题",example="")
    private String title;
}