package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/***
 * 概述:项目任务分页查询条件
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/17
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@ApiModel(value = "项目任务分页查询条件")
@Data
public class ProTaskPageDto implements Serializable{
    private static final long serialVersionUID = -2271629170529450795L;

    /**
     * 任务名称
     *
     * Table:     fb_zg_pro_task
     * Column:    task_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务名称",example="")
    private String taskName;

    /**
     * 任务状态,0-位开始，1-已开始，2-完成，3延期
     *
     * Table:     fb_zg_pro_task
     * Column:    task_state
     * Nullable:  true
     */
    @ApiModelProperty(value = "任务状态,0-位开始，1-已开始，2-完成，3延期",example="")
    private Byte taskState;



}
