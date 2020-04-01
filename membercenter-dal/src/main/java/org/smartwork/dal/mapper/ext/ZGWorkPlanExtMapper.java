package org.smartwork.dal.mapper.ext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.smartwork.comm.model.ZGWorkPlanPageDto;
import org.smartwork.dal.entity.ZGWorkPlan;

import java.util.Date;
import java.util.List;

public interface ZGWorkPlanExtMapper {

    /***
     * selectPlanDay方法概述:查询我的日程计划
     * @param startTime, endTime
     * @return org.forbes.comm.vo.Result<java.util.List<org.smartwork.dal.entity.ZGWorkPlan>>
     * @创建人 Tom
     * @创建时间 2020/3/23 12:34
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<ZGWorkPlan> selectPlanDay(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

    /***
     * selectMyPlan方法概述:查询我的所有日程计划
     * @param
     * @return org.forbes.comm.vo.Result<java.util.List < org.smartwork.dal.entity.ZGWorkPlan>>
     * @创建人 Tom
     * @创建时间 2020/3/31 14:51
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    IPage<ZGWorkPlan> page(IPage<ZGWorkPlan> page, @Param("zgWorkPlanPageDto") ZGWorkPlanPageDto zgWorkPlanPageDto);
}