package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lzw
 * @date 2020/3/31 18:04
 */
@Data
@ApiModel(description = "审核团队传入dto")
public class AuditTeamDto implements Serializable {

    private static final long serialVersionUID = 5379256178057277211L;

    /**
     * id
     */
    @ApiModelProperty(value = "id,添加不传值", example = "0")
    private Long id;

    /**
     * 团队状态-0待审核-1审核通过-2审核未通过
     * <p>
     * Table:     fb_zg_company
     * Column:    state
     * Nullable:  true
     */
    @ApiModelProperty(value = "团队状态-0待审核-1审核通过-2审核未通过", example = "")
    private String state;

}
