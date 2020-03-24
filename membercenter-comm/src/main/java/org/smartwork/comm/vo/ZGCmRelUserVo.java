package org.smartwork.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.vo.Result;

import java.io.Serializable;

/**
 * 公司查看员工详情
 */
@Data
@ApiModel(value = "公司查看员工详情视图")
public class ZGCmRelUserVo implements Serializable {
    private static final long serialVersionUID = -6722984346704435888L;

    /**
     * 公司ID
     * <p>
     * Table:     fb_zg_cm_rel_user
     * Column:    cm_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "公司ID", example = "0")
    private Long cmId;

    /**
     * 用户ID
     * <p>
     * Table:     fb_zg_cm_rel_user
     * Column:    user_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户ID", example = "0")
    private Long userId;

    /**
     * 岗位ID
     * <p>
     * Table:     fb_zg_cm_rel_user
     * Column:    post_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "岗位ID", example = "0")
    private Long postId;

    /**
     * 用户名
     * <p>
     * Table:     fb_zg_cm_rel_user
     * Column:    user_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户名", example = "")
    private String userName;

    /**
     * 0-否1-是
     * <p>
     * Table:     fb_zg_cm_rel_user
     * Column:    admin_flag
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-否1-是", example = "")
    private String adminFlag;

    /**
     * 用户详情
     */
    Result<SysUser> user;
}
