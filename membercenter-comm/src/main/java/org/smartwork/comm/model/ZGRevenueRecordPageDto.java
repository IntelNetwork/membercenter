package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 个人营收纪录查询条件
 */
@Data
@ApiModel("个人营收纪录查询条件")
public class ZGRevenueRecordPageDto implements Serializable{

    private static final long serialVersionUID = 7381793443741393656L;

    /**
     * 用户名
     *
     * Table:     fb_zg_revenue_record
     * Column:    user_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户名",example="",required = true)
    @NotEmpty(message = "用户名为空")
    private String userName;

    /**
     * 来源标题
     *
     * Table:     fb_zg_revenue_record
     * Column:    source_title
     * Nullable:  true
     */
    @ApiModelProperty(value = "来源标题",example="")
    private String sourceTitle;

    /**
     * 0-任务佣金1-佣金结算
     *
     * Table:     fb_zg_revenue_record
     * Column:    data_type
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-任务佣金1-佣金结算",example="")
    private String dataType;

}
