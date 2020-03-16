package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_cm_rel_user
 */
@Data
@ApiModel(description="用户公司岗位")
@TableName("fb_zg_cm_rel_user")
public class ZGCmRelUser extends BaseEntity {
    private static final long serialVersionUID = 250595911067606601L;
    /**
     * 公司ID
     *
     * Table:     fb_zg_cm_rel_user
     * Column:    cm_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "公司ID",example="0")
    private Long cmId;

    /**
     * 用户ID
     *
     * Table:     fb_zg_cm_rel_user
     * Column:    user_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户ID",example="0")
    private Long userId;

    /**
     * 岗位ID
     *
     * Table:     fb_zg_cm_rel_user
     * Column:    post_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "岗位ID",example="0")
    private Long postId;

    /**
     * 用户名
     *
     * Table:     fb_zg_cm_rel_user
     * Column:    user_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户名",example="")
    private String userName;

    /**
     * 0-否1-是
     *
     * Table:     fb_zg_cm_rel_user
     * Column:    admin_flag
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-否1-是",example="")
    private Byte adminFlag;
}