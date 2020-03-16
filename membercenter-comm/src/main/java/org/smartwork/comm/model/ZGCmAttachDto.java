package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lzw
 * @date 2020/3/16 14:07
 */
@Data
@ApiModel(description="公司附件dto")
public class ZGCmAttachDto implements Serializable {

    private static final long serialVersionUID = 6962498447520581408L;

    /**
     * 数据ID
     *
     * Table:     fb_zg_cm_attach
     * Column:    data_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "数据ID",example="0")
    private Long dataId;

    /**
     * 中文名
     *
     * Table:     fb_zg_cm_attach
     * Column:    cn_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "中文名",example="")
    private String cnName;

    /**
     * 后缀名
     *
     * Table:     fb_zg_cm_attach
     * Column:    suffix
     * Nullable:  true
     */
    @ApiModelProperty(value = "后缀名",example="")
    private String suffix;

    /**
     * 文件路径
     *
     * Table:     fb_zg_cm_attach
     * Column:    file_path
     * Nullable:  true
     */
    @ApiModelProperty(value = "文件路径",example="")
    private String filePath;

    /**
     * 数据类型0-营业执照1-法人身份证2-用户身份证
     *
     * Table:     fb_zg_cm_attach
     * Column:    data_type
     * Nullable:  true
     */
    @ApiModelProperty(value = "数据类型0-营业执照1-法人身份证2-用户身份证",example="")
    private Byte dataType;

    /**
     * 排序
     *
     * Table:     fb_zg_cm_attach
     * Column:    orders
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序",example="")
    private Byte orders;
}
