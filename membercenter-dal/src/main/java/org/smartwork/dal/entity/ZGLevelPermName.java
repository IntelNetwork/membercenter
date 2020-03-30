package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_member_level_perm_name
 */
@Data
@ApiModel(description="null")
@TableName("fb_zg_member_level_perm_name")
public class ZGLevelPermName extends BaseEntity {
    /**
     * 权限编码
     *
     * Table:     fb_zg_member_level_perm_name
     * Column:    perm_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限编码",example="0")
    private Long permCode;

    /**
     * 权限名称
     *
     * Table:     fb_zg_member_level_perm_name
     * Column:    perm_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限名称",example="")
    private String permName;
}