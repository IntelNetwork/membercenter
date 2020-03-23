package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *团队人员用户Dto
 */
@Data
@ApiModel(value = "团队人员分页参数")
public class ZGTeamRelUserPageDto implements Serializable{
    private static final long serialVersionUID = -8775195755223453974L;
    /**
     * 团队ID
     *
     * Table:     fb_zg_team_rel_user
     * Column:    user_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "团队ID",example="")
    private Long teamId;
 /**
     * 用户名
     *
     * Table:     fb_zg_team_rel_user
     * Column:    user_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户名",example="")
    private String userName;


}
