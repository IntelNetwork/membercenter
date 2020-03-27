package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
    @ApiModelProperty(value = "团队ID",example="",required = true)
    @NotNull(message = "团队ID为空")
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
