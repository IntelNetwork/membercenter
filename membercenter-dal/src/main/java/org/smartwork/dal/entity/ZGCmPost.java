package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_cm_post
 */
@Data
@ApiModel(description="公司岗位")
@TableName("fb_zg_cm_post")
public class ZGCmPost extends BaseEntity {
    /**
     * 岗位名称
     *
     * Table:     fb_zg_cm_post
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "岗位名称",example="")
    private String name;

    /**
     * 等级越小表示职位越高
     *
     * Table:     fb_zg_cm_post
     * Column:    level
     * Nullable:  true
     */
    @ApiModelProperty(value = "等级越小表示职位越高",example="0")
    private Integer level;
}