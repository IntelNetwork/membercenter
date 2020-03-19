package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Table: fb_zg_member_level_perm
 */
@Data
@ApiModel(description="会员权限")
@TableName("fb_zg_member_level_perm")
public class ZGMemberLevelPerm extends BaseEntity {
    private static final long serialVersionUID = -8815931498133825735L;
    /**
     * 等级ID
     *
     * Table:     fb_zg_member_level_perm
     * Column:    level_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "等级ID",example="0")
    private Long levelId;

    /**
     * 权限编码
     *
     * Table:     fb_zg_member_level_perm
     * Column:    perm_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限编码",example="0")
    @NotEmpty(message = "权限编码为空")
    private String permCode;


    @ApiModelProperty(value = "权限要素",example="")
    @TableField(exist = false)
    List<ZGMemberLevelPermEle>  memberLevelPermEles;
}