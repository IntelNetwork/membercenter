package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_pro_attach
 */
@Data
@ApiModel(description="项目/任务附件")
@TableName("fb_zg_pro_attach")
public class ZGProAttach extends BaseEntity {
    private static final long serialVersionUID = -7234085788760464688L;
    /**
     * 数据ID
     *
     * Table:     fb_zg_pro_attach
     * Column:    data_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "数据ID",example="0")
    private Long dataId;

    /**
     * 中文名
     *
     * Table:     fb_zg_pro_attach
     * Column:    cn_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "中文名",example="")
    private String cnName;

    /**
     * 后缀名
     *
     * Table:     fb_zg_pro_attach
     * Column:    suffix
     * Nullable:  true
     */
    @ApiModelProperty(value = "后缀名",example="")
    private String suffix;

    /**
     * 文件路径
     *
     * Table:     fb_zg_pro_attach
     * Column:    file_path
     * Nullable:  true
     */
    @ApiModelProperty(value = "文件路径",example="")
    private String filePath;

    /**
     * 数据类型0-项目附件1-任务附件
     *
     * Table:     fb_zg_pro_attach
     * Column:    data_type
     * Nullable:  true
     */
    @ApiModelProperty(value = "数据类型0-项目附件1-任务附件",example="")
    private Byte dataType;

    /**
     * 排序
     *
     * Table:     fb_zg_pro_attach
     * Column:    orders
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序",example="")
    private Byte orders;
}