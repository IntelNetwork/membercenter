package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "团队附件")
public class ZGTeamAttachDto implements Serializable{

    private static final long serialVersionUID = 4495063649637070L;
    /**
     * 数据ID
     * <p>
     * Table:     fb_zg_team_attach
     * Column:    data_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "数据ID", example = "0")
    private Long dataId;

    /**
     * 中文名
     * <p>
     * Table:     fb_zg_team_attach
     * Column:    cn_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "中文名", example = "")
    private String cnName;

    /**
     * 后缀名
     * <p>
     * Table:     fb_zg_team_attach
     * Column:    suffix
     * Nullable:  true
     */
    @ApiModelProperty(value = "后缀名", example = "")
    private String suffix;

    /**
     * 文件路径
     * <p>
     * Table:     fb_zg_team_attach
     * Column:    file_path
     * Nullable:  true
     */
    @ApiModelProperty(value = "文件路径", example = "")
    private String filePath;

    /**
     * 数据类型0-负责人身份证1-用户身份证
     * <p>
     * Table:     fb_zg_team_attach
     * Column:    data_type
     * Nullable:  true
     */
    @ApiModelProperty(value = "数据类型0-负责人身份证1-用户身份证", example = "")
    private Byte dataType;

    /**
     * 排序
     * <p>
     * Table:     fb_zg_team_attach
     * Column:    orders
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序", example = "")
    private Byte orders;

}
