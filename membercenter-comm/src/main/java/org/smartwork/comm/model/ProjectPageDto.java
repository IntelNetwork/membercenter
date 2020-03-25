package org.smartwork.comm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目分页条件
 */
@Data
@ApiModel(value = "项目分页查询条件")
public class ProjectPageDto implements Serializable{
    private static final long serialVersionUID = 6607093900835372287L;

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
     * 开始时间
     *
     * Table:     fb_zg_project
     * Column:    start_date
     * Nullable:  true
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间",example="")
    private Date startDate;

    /**
     * 结束时间
     *
     * Table:     fb_zg_project
     * Column:    end_time
     * Nullable:  true
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间",example="")
    private Date endTime;
}
