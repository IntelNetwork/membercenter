package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/***
 * 概述: 团队相关传入参数
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/16
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@Data
@ApiModel(description="团队相关传入参数")
public class ZGTeamDto implements Serializable {

    private static final long serialVersionUID = 3303918946433156074L;

    /**
     * id
     * <p>
     * Table:     fb_zg_team
     * Column:    id
     * Nullable:  true
     */
    @ApiModelProperty(value = "团队ID,添加不传值", example = "")
    private Long id;

    /**
     * 负责人
     * <p>
     * Table:     fb_zg_team
     * Column:    legal_person
     * Nullable:  true
     */
    @ApiModelProperty(value = "负责人", example = "")
    private String legalPerson;

    /**
     * 负责人身份证
     * <p>
     * Table:     fb_zg_team
     * Column:    Id_card
     * Nullable:  true
     */
    @ApiModelProperty(value = "负责人身份证", example = "")
    private String idCard;

    /**
     * 团队规模
     * <p>
     * Table:     fb_zg_team
     * Column:    scale
     * Nullable:  true
     */
    @ApiModelProperty(value = "团队规模", example = "")
    private Byte scale;

    /**
     * 团队介绍
     * <p>
     * Table:     fb_zg_team
     * Column:    intr
     * Nullable:  true
     */
    @ApiModelProperty(value = "团队介绍", example = "")
    private String intr;


    @ApiModelProperty(value = "团队附件(集合)", example = "")
    private List<ZGTeamAttachDto> teamAttachDtos;


    @ApiModelProperty(value = "团队用户(集合)", example = "")
    private List<ZGTeamRelUserDto> teamRelUserDtos;

}
