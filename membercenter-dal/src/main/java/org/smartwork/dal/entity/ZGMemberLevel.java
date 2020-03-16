package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_member_level
 */
@Data
@ApiModel(description="会员等级")
@TableName("fb_zg_member_level")
public class ZGMemberLevel extends BaseEntity {
    /**
     * 等级名称
     *
     * Table:     fb_zg_member_level
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "等级名称",example="")
    private String name;

    /**
     * 等级介绍
     *
     * Table:     fb_zg_member_level
     * Column:    instr
     * Nullable:  true
     */
    @ApiModelProperty(value = "等级介绍",example="")
    private String instr;

    /**
     * 排序
     *
     * Table:     fb_zg_member_level
     * Column:    orders
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序",example="")
    private Byte orders;

    /**
     * 费用
     *
     * Table:     fb_zg_member_level
     * Column:    cost
     * Nullable:  true
     */
    @ApiModelProperty(value = "费用",example="0.00")
    private BigDecimal cost;

    /**
     * 期限
     *
     * Table:     fb_zg_member_level
     * Column:    deadline
     * Nullable:  true
     */
    @ApiModelProperty(value = "期限",example="")
    private Byte deadline;
}