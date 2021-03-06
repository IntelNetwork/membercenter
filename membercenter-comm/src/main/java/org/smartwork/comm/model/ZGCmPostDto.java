package org.smartwork.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lzw
 * @date 2020/3/16 15:59
 */
@Data
@ApiModel(description="公司岗位dto")
public class ZGCmPostDto implements Serializable {

    private static final long serialVersionUID = 7994363878066352509L;
    /**
     * id
     */
    @ApiModelProperty(value = "id,添加不传值", example = "0")
    private Long id;

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

    /**
     * 公司id
     *
     * Table:     fb_zg_cm_post
     * Column:    level
     * Nullable:  true
     */
    @ApiModelProperty(value = "公司id",example="0")
    @NotNull(message = "公司id不能为空",groups = Validated.class)
    private Long cmId;
}
