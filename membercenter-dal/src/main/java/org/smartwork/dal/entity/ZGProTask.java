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
 * Table: fb_zg_pro_task
 */
@Data
@ApiModel(description = "项目-任务")
@TableName("fb_zg_pro_task")
public class ZGProTask extends BaseEntity {
    private static final long serialVersionUID = -2198950292956314850L;
    /**
     * 项目ID
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    pro_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "项目ID", example = "0", required = true)
    @NotNull(message = "所属项目ID为空")
    private Long proId;

    /**
     * 任务名称
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    task_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务名称", example = "", required = true)
    @NotEmpty(message = "任务名称为空")
    private String taskName;

    /**
     * Table:     fb_zg_pro_task
     * Column:    task_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "负责人ID", example = "", required = true)
    @NotEmpty(message = "负责人ID为空")
    private String userId;

    /**
     * 任务名称
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    task_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "负责人姓名", example = "", required = true)
    @NotEmpty(message = "负责人姓名为空")
    private String userName;

    /**
     * 前置任务ID
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    pre_task_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "前置任务ID", example = "0")
    private Long preTaskId;

    /**
     * 后置任务ID
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    rear_task_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "后置任务ID", example = "0")
    private Long rearTaskId;

    /**
     * 进度
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    ratio
     * Nullable:  true
     */
    @ApiModelProperty(value = "进度", example = "0.00")
    private BigDecimal ratio;

    /**
     * 任务状态,0-未开始，1-已开始，2-完成，3延期
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    task_state
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务状态,0-未开始，1-已开始，2-完成，3延期", example = "0")
    private Byte taskState;

    /**
     * 延期说明
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    delay
     * Nullable:  true
     */
    @ApiModelProperty(value = "延期说明", example = "")
    private String delay;

    /**
     * 任务描述
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    des
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务描述", example = "")
    private String des;

    /**
     * 开始时间
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    start_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "开始时间", example = "")
    private Date startTime;

    /**
     * 结束时间
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    end_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "结束时间", example = "")
    private Date endTime;

    /**
     * 实际开始时间
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    actual_start_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际开始时间", example = "")
    private Date actualStartTime;

    /**
     * 实际结束时间
     * <p>
     * Table:     fb_zg_pro_task
     * Column:    actual_end_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际结束时间", example = "")
    private Date actualEndTime;
}