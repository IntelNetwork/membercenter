package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_company
 */
@Data
@ApiModel(description="公司")
@TableName("fb_zg_company")
public class ZGCompany extends BaseEntity {
    /**
     * 公司名称
     *
     * Table:     fb_zg_company
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "公司名称",example="")
    private String name;

    /**
     * 组织机构代码
     *
     * Table:     fb_zg_company
     * Column:    org_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "组织机构代码",example="")
    private String orgCode;

    /**
     * 法人
     *
     * Table:     fb_zg_company
     * Column:    legal_person
     * Nullable:  true
     */
    @ApiModelProperty(value = "法人",example="")
    private String legalPerson;

    /**
     * 法人身份证
     *
     * Table:     fb_zg_company
     * Column:    Id_card
     * Nullable:  true
     */
    @ApiModelProperty(value = "法人身份证",example="")
    private String idCard;

    /**
     * 公司规模公司规模
     *
     * Table:     fb_zg_company
     * Column:    scale
     * Nullable:  true
     */
    @ApiModelProperty(value = "公司规模公司规模",example="")
    private Byte scale;

    /**
     * 公司介绍
     *
     * Table:     fb_zg_company
     * Column:    intr
     * Nullable:  true
     */
    @ApiModelProperty(value = "公司介绍",example="")
    private String intr;
}