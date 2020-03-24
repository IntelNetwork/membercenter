package org.smartwork.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.vo.Result;

import java.io.Serializable;

@Data
@ApiModel(value = "员工详情视图")
public class ZGTeamRelUserVo implements Serializable{

    private static final long serialVersionUID = 1901039201562121777L;
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

    /**
     * 用户详情
     * */
    Result<SysUser> user;
}
