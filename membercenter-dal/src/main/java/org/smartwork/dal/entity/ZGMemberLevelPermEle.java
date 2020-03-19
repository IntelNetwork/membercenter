package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_member_level_perm_ele
 */
@Data
@ApiModel(description="会员等级权限要素权限")
@TableName("fb_zg_member_level_perm_ele")
public class ZGMemberLevelPermEle extends BaseEntity {
    private static final long serialVersionUID = 8500999493239800416L;
    /**
     * 权限编码
     *
     * Table:     fb_zg_member_level_perm_ele
     * Column:    perm_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限编码",example="0")
    private Long permCode;

    /**
     * 开始条件
     *
     * Table:     fb_zg_member_level_perm_ele
     * Column:    start_condition
     * Nullable:  true
     */
    @ApiModelProperty(value = "开始条件",example="0.00")
    private BigDecimal startCondition;

    /**
     * 结束条件
     *
     * Table:     fb_zg_member_level_perm_ele
     * Column:    end_condition
     * Nullable:  true
     */
    @ApiModelProperty(value = "结束条件",example="0.00")
    private BigDecimal endCondition;

    /**
     * 要素值
     *
     * Table:     fb_zg_member_level_perm_ele
     * Column:    ele_val
     * Nullable:  true
     */
    @ApiModelProperty(value = "要素值",example="0.00")
    private BigDecimal eleVal;

    /**
     * 要素描述
     *
     * Table:     fb_zg_member_level_perm_ele
     * Column:    ele_des
     * Nullable:  true
     */
    @ApiModelProperty(value = "要素描述",example="")
    private String eleDes;
}