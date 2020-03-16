package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_member_level_perm
 */
@Data
@ApiModel(description="会员权限")
@TableName("fb_zg_member_level_perm")
public class ZGMemberLevelPerm extends BaseEntity {
    /**
     * 会员id
     *
     * Table:     fb_zg_member_level_perm
     * Column:    member_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "会员id",example="0")
    private Long memberId;

    /**
     * 权限ID
     *
     * Table:     fb_zg_member_level_perm
     * Column:    perm_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限ID",example="0")
    private Long permId;
}