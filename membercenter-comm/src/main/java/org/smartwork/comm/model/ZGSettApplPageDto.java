package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("结算申请(佣金结算申请)分页查询条件")
public class ZGSettApplPageDto implements Serializable{

    private static final long serialVersionUID = 7626126367961497308L;

    /**
     * 申请单号
     *
     * Table:     fb_zg_sett_appl
     * Column:    appl_order_no
     * Nullable:  true
     */
    @ApiModelProperty(value = "申请单号",example="")
    private String applOrderNo;

    /**
     * 0-待审核1-审核成功2-驳回
     *
     * Table:     fb_zg_sett_appl
     * Column:    review_status
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-待审核1-审核成功2-驳回",example="")
    private String reviewStatus;

}
