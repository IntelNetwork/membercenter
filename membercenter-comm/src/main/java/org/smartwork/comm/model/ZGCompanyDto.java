package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author lzw
 * @date 2020/3/16 13:57
 */
@Data
@ApiModel(description = "创建公司传入dto")
public class ZGCompanyDto implements Serializable {

    private static final long serialVersionUID = 5342459506556732373L;

    /**
     * id
     */
    @ApiModelProperty(value = "id,添加不传值", example = "0")
    private Long id;

    /**
     * 公司名称
     * <p>
     * Table:     fb_zg_company
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "公司名称", example = "", required = true)
    @NotEmpty(message = "公司名称为空")
    private String name;

    /**
     * 组织机构代码
     * <p>
     * Table:     fb_zg_company
     * Column:    org_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "组织机构代码", example = "", required = true)
    @NotEmpty(message = "组织机构代码为空")
    private String orgCode;

    /**
     * 法人
     * <p>
     * Table:     fb_zg_company
     * Column:    legal_person
     * Nullable:  true
     */
    @ApiModelProperty(value = "法人", example = "", required = true)
    @NotEmpty(message = "法人为空")
    private String legalPerson;

    /**
     * 法人身份证
     * <p>
     * Table:     fb_zg_company
     * Column:    Id_card
     * Nullable:  true
     */
    @ApiModelProperty(value = "法人身份证", example = "", required = true)
    @NotEmpty(message = "法人身份证为空")
    private String idCard;

    /**
     * 公司规模公司规模
     * <p>
     * Table:     fb_zg_company
     * Column:    scale
     * Nullable:  true
     */
    @ApiModelProperty(value = "公司规模公司规模", example = "0")
    private Integer scale;

    /**
     * 公司介绍
     * <p>
     * Table:     fb_zg_company
     * Column:    intr
     * Nullable:  true
     */
    @ApiModelProperty(value = "公司介绍", example = "")
    private String intr;

    /**
     * 公司状态-0待审核-1审核通过-2审核未通过
     * <p>
     * Table:     fb_zg_company
     * Column:    state
     * Nullable:  true
     */
    @ApiModelProperty(value = "公司状态-0待审核-1审核通过-2审核未通过", example = "")
    private String state;



    /**
     * 公司附件dto
     */
    @ApiModelProperty(value = "公司附件dto")
    List<ZGCmAttachDto> zgCmAttachDtos;

}
