package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_team_rel_user
 */
@Data
@ApiModel(description="用户团队岗位")
@TableName("fb_zg_team_rel_user")
public class ZGTeamRelUser extends BaseEntity {
    /**
     * 团队ID
     *
     * Table:     fb_zg_team_rel_user
     * Column:    team_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "团队ID",example="0")
    private Long teamId;

    /**
     * 用户ID
     *
     * Table:     fb_zg_team_rel_user
     * Column:    user_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户ID",example="0")
    private Long userId;

    /**
     * 用户名
     *
     * Table:     fb_zg_team_rel_user
     * Column:    user_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户名",example="")
    private String userName;

    /**
     * 0-否1-是
     *
     * Table:     fb_zg_team_rel_user
     * Column:    admin_flag
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-否1-是",example="")
    private Byte adminFlag;

    /**
     * 负责方向：比如前端、后端
     *
     * Table:     fb_zg_team_rel_user
     * Column:    direction
     * Nullable:  true
     */
    @ApiModelProperty(value = "负责方向：比如前端、后端",example="")
    private String direction;
}