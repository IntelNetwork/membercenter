package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_team
 */
@Data
@ApiModel(description="团队")
@TableName("fb_zg_team")
public class ZGTeam extends BaseEntity {
    /**
     * 负责人
     *
     * Table:     fb_zg_team
     * Column:    legal_person
     * Nullable:  true
     */
    @ApiModelProperty(value = "负责人",example="")
    private String legalPerson;

    /**
     * 负责人身份证
     *
     * Table:     fb_zg_team
     * Column:    Id_card
     * Nullable:  true
     */
    @ApiModelProperty(value = "负责人身份证",example="")
    private String idCard;

    /**
     * 团队规模
     *
     * Table:     fb_zg_team
     * Column:    scale
     * Nullable:  true
     */
    @ApiModelProperty(value = "团队规模",example="")
    private Byte scale;

    /**
     * 团队介绍
     *
     * Table:     fb_zg_team
     * Column:    intr
     * Nullable:  true
     */
    @ApiModelProperty(value = "团队介绍",example="")
    private String intr;
}