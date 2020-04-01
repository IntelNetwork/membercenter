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
@ApiModel(description = "团队分页传入dto")
public class TeamPageDto implements Serializable {

    private static final long serialVersionUID = -2889840769395063381L;

    /**
     * 法人
     * <p>
     * Table:     fb_zg_company
     * Column:    legal_person
     * Nullable:  true
     */
    @ApiModelProperty(value = "法人", example = "")
    private String legalPerson;

}
