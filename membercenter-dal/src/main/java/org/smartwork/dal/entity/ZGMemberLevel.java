package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import org.forbes.comm.annotations.ValidEnum;
import org.forbes.comm.annotations.ValidUnique;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.entity.BaseEntity;
import org.forbes.comm.vo.ResultEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Table: fb_zg_member_level
 */
@Data
@ApiModel(description="会员等级")
@TableName("fb_zg_member_level")
public class ZGMemberLevel extends BaseEntity {
    private static final long serialVersionUID = 8991568520001407643L;
    /**
     * 等级名称
     *
     * Table:     fb_zg_member_level
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "等级名称",example="")
    @NotEmpty(message = "等级名称为空",groups = {UpdateValid.class,SaveValid.class})
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
    @ApiModelProperty(value = "排序",example="0")
    @NotBlank(message = "等级排序为空",groups = {UpdateValid.class, SaveValid.class})
    @ValidUnique(column = "orders",bizCode = "008004001",bizErrorMsg = "%s会员等级已经存在")
    private Integer orders;

    /**
     * 费用
     *
     * Table:     fb_zg_member_level
     * Column:    cost
     * Nullable:  true
     */
    @ApiModelProperty(value = "费用",example="0.00")
    @NotBlank(message = "费用为空",groups = {UpdateValid.class,SaveValid.class})
    private BigDecimal cost;

    /**
     * 期限
     *
     * Table:     fb_zg_member_level
     * Column:    deadline
     * Nullable:  true
     */
    @ApiModelProperty(value = "期限",example="0")
    @NotBlank(message = "期限为空",groups = {UpdateValid.class,SaveValid.class})
    private Integer deadline;


    @ApiModelProperty(value = "期限单位",example="")
    @NotBlank(message = "期限单位为空",groups = {UpdateValid.class,SaveValid.class})
    @ValidEnum(classzz = ResultEnum.class,bizCode = "008004002",bizErrorMsg = "%s期限单位不存在")
    private String deadUnit;



    @ApiModelProperty(value = "状态,0-停止使用,1-使用中",example="0")
    @NotNull(message = "状态为空",groups = {SaveValid.class, UpdateValid.class})
    private Integer state;


    @ApiModelProperty(value = "会员等级权限",example="")
    @TableField(exist = false)
    private List<ZGMemberLevelPerm> memberLevelPerms;


    @ApiModelProperty(value = "权限要素",example="")
    @TableField(exist = false)
    List<ZGMemberLevelPermEle>  memberLevelPermEles;
}