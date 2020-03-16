package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.entity.BaseEntity;

/**
 * Table: fb_zg_project_per
 */
@Data
@ApiModel(description="参与项目人员")
@TableName("fb_zg_project_per")
public class ZGProjectPer extends BaseEntity {
    /**
     * 人员ID
     *
     * Table:     fb_zg_project_per
     * Column:    user_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "人员ID",example="0")
    private Long userId;

    /**
     * 姓名
     *
     * Table:     fb_zg_project_per
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "姓名",example="")
    private String name;

    /**
     * 0-项目经理，1-产品经理，2-UI设计师，3-前端工程师，4-后端工程师
     *
     * Table:     fb_zg_project_per
     * Column:    posit
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-项目经理，1-产品经理，2-UI设计师，3-前端工程师，4-后端工程师",example="")
    private Byte posit;

    /**
     * 联系电话
     *
     * Table:     fb_zg_project_per
     * Column:    phone
     * Nullable:  true
     */
    @ApiModelProperty(value = "联系电话",example="")
    private String phone;
}