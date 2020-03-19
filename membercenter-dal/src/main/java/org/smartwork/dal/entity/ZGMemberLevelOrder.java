package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_member_level_order
 */
@Data
@ApiModel(description="会员订单")
@TableName("fb_zg_member_level_order")
public class ZGMemberLevelOrder extends BaseEntity {
    private static final long serialVersionUID = -3088159050893581767L;
    /**
     * 会员id
     *
     * Table:     fb_zg_member_level_order
     * Column:    member_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员id",example="0")
    private Long memberId;

    /**
     * 会员名
     *
     * Table:     fb_zg_member_level_order
     * Column:    member_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员名",example="")
    private String memberName;

    /**
     * 支付金额
     *
     * Table:     fb_zg_member_level_order
     * Column:    pay_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "支付金额",example="0.00")
    private BigDecimal payAmount;

    /**
     * 开始日期
     *
     * Table:     fb_zg_member_level_order
     * Column:    start_date
     * Nullable:  true
     */
    @ApiModelProperty(value = "开始日期",example="")
    private Date startDate;

    /**
     * 结束日期
     *
     * Table:     fb_zg_member_level_order
     * Column:    end_date
     * Nullable:  true
     */
    @ApiModelProperty(value = "结束日期",example="")
    private Date endDate;

    /**
     * 0-否1-是
     *
     * Table:     fb_zg_member_level_order
     * Column:    take_effect
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-否1-是",example="false")
    private Boolean takeEffect;

    /**
     * 等级订单号
     *
     * Table:     fb_zg_member_level_order
     * Column:    ml_order_no
     * Nullable:  true
     */
    @ApiModelProperty(value = "等级订单号",example="")
    private String mlOrderNo;
}