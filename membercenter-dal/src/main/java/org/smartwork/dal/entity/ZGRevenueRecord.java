package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_revenue_record
 */
@Data
@ApiModel(description="个人营收记录")
@TableName("fb_zg_revenue_record")
public class ZGRevenueRecord extends BaseEntity {
    private static final long serialVersionUID = 6294865772383544042L;
    /**
     * Table:     fb_zg_revenue_record
     * Column:    user_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "",example="0")
    private Long userId;

    /**
     * 用户名
     *
     * Table:     fb_zg_revenue_record
     * Column:    user_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户名",example="")
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
     * 来源介绍
     *
     * Table:     fb_zg_revenue_record
     * Column:    source_intr
     * Nullable:  true
     */
    @ApiModelProperty(value = "来源介绍",example="")
    private String sourceIntr;

    /**
     * 数据来源ID
     *
     * Table:     fb_zg_revenue_record
     * Column:    data_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "数据来源ID",example="0")
    private Long dataId;

    /**
     * 0-任务佣金1-佣金结算
     *
     * Table:     fb_zg_revenue_record
     * Column:    data_type
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-任务佣金1-佣金结算",example="")
    private String dataType;

    /**
     * 修改之前金额
     *
     * Table:     fb_zg_revenue_record
     * Column:    before_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "修改之前金额",example="0.00")
    private BigDecimal beforeAmount;

    /**
     * 修改之后金额
     *
     * Table:     fb_zg_revenue_record
     * Column:    after_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "修改之后金额",example="0.00")
    private BigDecimal afterAmount;

    /**
     * 产生年月日
     *
     * Table:     fb_zg_revenue_record
     * Column:    produce_date
     * Nullable:  true
     */
    @ApiModelProperty(value = "产生年月日",example="")
    private String produceDate;

    /**
     * 变动金额可为负数
     *
     * Table:     fb_zg_revenue_record
     * Column:    change_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "变动金额可为负数",example="0.00")
    private BigDecimal changeAmount;
}