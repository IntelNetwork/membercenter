package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_member_level_perm_member
 */
@Data
@ApiModel(description="null")
@TableName("fb_zg_member_level_perm_member")
public class ZGLevelPermMember extends BaseEntity {
    /**
     * 权限编码
     *
     * Table:     fb_zg_member_level_perm_member
     * Column:    perm_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限编码",example="0")
    private Long permCode;

    /**
     * 权限id
     *
     * Table:     fb_zg_member_level_perm_member
     * Column:    perm_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限id",example="0")
    private Long permId;

    /**
     * 会员id
     *
     * Table:     fb_zg_member_level_perm_member
     * Column:    member_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员id",example="0")
    private Long memberId;
}