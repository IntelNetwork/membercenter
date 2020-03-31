package org.smartwork.comm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Table: fb_zg_member_level_perm_member
 */
@Data
@ApiModel(description = "分配会员权限传入dto")
public class ZGLevelPermMemberDto implements Serializable {

    private static final long serialVersionUID = -4091692601293484687L;

    /**
     * id
     */
    @ApiModelProperty(value = "id,添加不传值", example = "0")
    private Long id;

    /**
     * 权限编码
     *
     * Table:     fb_zg_member_level_perm_member
     * Column:    perm_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限编码",example="0",required = true)
    @NotNull(message = "权限编码为空")
    private Long permCode;

    /**
     * 权限id
     *
     * Table:     fb_zg_member_level_perm_member
     * Column:    perm_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限id",example="0",required = true)
    @NotNull(message = "权限id为空")
    private Long permId;

//    /**
//     * 会员id
//     *
//     * Table:     fb_zg_member_level_perm_member
//     * Column:    member_id
//     * Nullable:  true
//     */
//    @ApiModelProperty(value = "会员id",example="0",required = true)
//    @NotNull(message = "会员id为空")
//    private Long memberId;
}