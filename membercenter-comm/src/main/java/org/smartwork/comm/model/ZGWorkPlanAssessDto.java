package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lzw
 * @date 2020/3/27 13:33
 */
@Data
@ApiModel(value = "工作计划评估参数")
public class ZGWorkPlanAssessDto implements Serializable {

    private static final long serialVersionUID = -4720084802159367403L;

    /**
     * id
     */
    @ApiModelProperty(value = "id,添加不传值", example = "0")
    private Long id;

    /**
     * 绩效评估
     *
     * Table:     fb_zg_work_plan
     * Column:    pert_assess
     * Nullable:  true
     */
    @ApiModelProperty(value = "绩效评估",example="")
    private Byte pertAssess;

    /**
     * 实际绩效评估
     *
     * Table:     fb_zg_work_plan
     * Column:    actual_pert_assess
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际绩效评估",example="")
    private Byte actualPertAssess;

    /**
     * 管理权重评估
     *
     * Table:     fb_zg_work_plan
     * Column:    manager_weight
     * Nullable:  true
     */
    @ApiModelProperty(value = "管理权重评估",example="")
    private Byte managerWeight;

    /**
     * 管理绩效评估
     *
     * Table:     fb_zg_work_plan
     * Column:    manag_pert_assess
     * Nullable:  true
     */
    @ApiModelProperty(value = "管理绩效评估",example="")
    private Byte managPertAssess;

    /**
     * 评估人ID
     *
     * Table:     fb_zg_work_plan
     * Column:    assess_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "评估人ID",example="0")
    private Long assessId;

    /**
     * 评估人
     *
     * Table:     fb_zg_work_plan
     * Column:    assess_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "评估人",example="")
    private String assessName;

    /**
     * 说明
     *
     * Table:     fb_zg_work_plan
     * Column:    des
     * Nullable:  true
     */
    @ApiModelProperty(value = "说明",example="")
    private String des;

}
