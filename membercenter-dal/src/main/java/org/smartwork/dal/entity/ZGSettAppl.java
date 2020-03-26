package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * Table: fb_zg_sett_appl
 */
@Data
@ApiModel(description="结算申请")
@TableName("fb_zg_sett_appl")
public class ZGSettAppl extends BaseEntity {


    private static final long serialVersionUID = 3538186117068392469L;
    /**
     * 申请标题
     *
     * Table:     fb_zg_sett_appl
     * Column:    title
     * Nullable:  true
     */
    @ApiModelProperty(value = "申请标题",example="")
    private String title;
    /**
     * 申请结算金额
     *
     * Table:     fb_zg_sett_appl
     * Column:    amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "申请结算金额",example="0.00")
    @NotNull(message = "申请结算金额为空")
    private BigDecimal amount;

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
     * 实际结算金额
     *
     * Table:     fb_zg_sett_appl
     * Column:    actual_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "实际结算金额",example="0.00")
    private BigDecimal actualAmount;

    /**
     * 平台扣点金额
     *
     * Table:     fb_zg_sett_appl
     * Column:    plat_ded_amount
     * Nullable:  true
     */
    @ApiModelProperty(value = "平台扣点金额",example="0.00")
    private BigDecimal platDedAmount;

    /**
     * 0-待审核1-审核成功2-驳回
     *
     * Table:     fb_zg_sett_appl
     * Column:    review_status
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-待审核1-审核成功2-驳回",example="")
    private String reviewStatus;

    /**
     * 审核人
     *
     * Table:     fb_zg_sett_appl
     * Column:    reviewer
     * Nullable:  true
     */
    @ApiModelProperty(value = "审核人",example="")
    private String reviewer;

    /**
     * 审核时间
     *
     * Table:     fb_zg_sett_appl
     * Column:    reviewer_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "审核时间",example="")
    private Date reviewerTime;

    /**
     * 审核备注
     *
     * Table:     fb_zg_sett_appl
     * Column:    review_note
     * Nullable:  true
     */
    @ApiModelProperty(value = "审核备注",example="")
    private String reviewNote;

    /**
     * 结算账号
     *
     * Table:     fb_zg_sett_appl
     * Column:    settl_account
     * Nullable:  true
     */
    @ApiModelProperty(value = "结算账号",example="")
    private String settlAccount;

    /**
     * 账号名称
     *
     * Table:     fb_zg_sett_appl
     * Column:    account_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "账号名称",example="")
    private String accountName;

    /**
     * 0-支付宝转支付宝1-支付宝转银行卡2-微信转微信3-线下结算
     *
     * Table:     fb_zg_sett_appl
     * Column:    settl_type
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-支付宝转支付宝1-支付宝转银行卡2-微信转微信3-线下结算",example="")
    private String settlType;

    /**
     * 0-未结算1-已结算3-结算异常
     *
     * Table:     fb_zg_sett_appl
     * Column:    settl_status
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-未结算1-已结算3-结算异常",example="")
    private String settlStatus;

    /**
     * 支付凭证
     *
     * Table:     fb_zg_sett_appl
     * Column:    pay_cred
     * Nullable:  true
     */
    @ApiModelProperty(value = "支付凭证",example="")
    private String payCred;

    /**
     * 失败原因
     *
     * Table:     fb_zg_sett_appl
     * Column:    failure
     * Nullable:  true
     */
    @ApiModelProperty(value = "失败原因",example="")
    private String failure;

    /**
     * 重试次数
     *
     * Table:     fb_zg_sett_appl
     * Column:    retry_count
     * Nullable:  true
     */
    @ApiModelProperty(value = "重试次数",example="")
    private Byte retryCount;

    /**
     * 失败次数
     *
     * Table:     fb_zg_sett_appl
     * Column:    failure_count
     * Nullable:  true
     */
    @ApiModelProperty(value = "失败次数",example="")
    private Byte failureCount;

    @ApiModelProperty(value = "结算商户ID",example="")
    private String mchId;

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
}