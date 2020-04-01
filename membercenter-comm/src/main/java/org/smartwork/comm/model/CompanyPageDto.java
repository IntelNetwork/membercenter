package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author lzw
 * @date 2020/3/31 18:04
 */
@Data
@ApiModel(description = "公司分页传入dto")
public class CompanyPageDto implements Serializable {

    private static final long serialVersionUID = 5379256178057277211L;

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
