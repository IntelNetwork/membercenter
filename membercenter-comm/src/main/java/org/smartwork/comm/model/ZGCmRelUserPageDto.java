package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lzw
 * @date 2020/3/23 12:51
 */
@Data
@ApiModel(value = "公司人员分页参数")
public class ZGCmRelUserPageDto implements Serializable {

    private static final long serialVersionUID = 5738114268065539160L;

    /**
     * 公司ID
     *
     * Table:     fb_zg_cm_rel_user
     * Column:    cm_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "公司ID",example="0",required = true)
    @NotNull(message = "公司id为空")
    private Long cmId;

    /**
     * 用户名
     *
     * Table:     fb_zg_cm_rel_user
     * Column:    user_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户名",example="")
    private String userName;
}
