package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_work_plan
 * @author Administrator
 */
@Data
@ApiModel(description="工作计划")
@TableName("fb_zg_work_plan")
public class ZGWorkPlan extends BaseEntity {
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

    /**
     * 工作内容
     *
     * Table:     fb_zg_work_plan
     * Column:    content
     * Nullable:  true
     */
    @ApiModelProperty(value = "工作内容",example="")
    private String content;

    /**
     * 开始时间
     *
     * Table:     fb_zg_work_plan
     * Column:    start_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "开始时间",example="")
    private Date startTime;

    /**
     * 结束时间
     *
     * Table:     fb_zg_work_plan
     * Column:    end_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "结束时间",example="")
    private Date endTime;

    /**
     * Table:     fb_zg_work_plan
     * Column:    status
     * Nullable:  true
     */
    @ApiModelProperty(value = "",example="")
    private String status;

    /**
     * 实际开始时间
     *
     * Table:     fb_zg_work_plan
     * Column:    actual_start_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际开始时间",example="")
    private Date actualStartTime;

    /**
     * 实际结束时间
     *
     * Table:     fb_zg_work_plan
     * Column:    actual_end_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际结束时间",example="")
    private Date actualEndTime;

    /**
     * 进度
     *
     * Table:     fb_zg_work_plan
     * Column:    ratio
     * Nullable:  true
     */
    @ApiModelProperty(value = "进度",example="0.00")
    private BigDecimal ratio;

    /**
     * 权重点
     *
     * Table:     fb_zg_work_plan
     * Column:    weight
     * Nullable:  true
     */
    @ApiModelProperty(value = "权重点",example="")
    private Byte weight;

    /**
     * 绩效点
     *
     * Table:     fb_zg_work_plan
     * Column:    performance
     * Nullable:  true
     */
    @ApiModelProperty(value = "绩效点",example="")
    private Byte performance;

    /**
     * 实际绩效点
     *
     * Table:     fb_zg_work_plan
     * Column:    actual_pert
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际绩效点",example="")
    private Byte actualPert;

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