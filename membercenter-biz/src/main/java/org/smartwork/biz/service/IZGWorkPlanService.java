package org.smartwork.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.smartwork.comm.model.ZGWorkPlanAssessDto;
import org.smartwork.comm.model.ZGWorkPlanDto;
import org.smartwork.comm.model.ZGWorkPlanPageDto;
import org.smartwork.dal.entity.ZGWorkPlan;

import java.util.Date;
import java.util.List;

public interface IZGWorkPlanService extends IService<ZGWorkPlan> {

    /***
     * selectPlanDay方法概述:查询我的日程计划
     * @param startTime, endTime
     * @创建人 nhy
     * @创建时间 2020/3/23 12:34
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<ZGWorkPlan> selectPlanDay(Date startTime, Date endTime);

    /***
     * addWorkPlan方法概述:写工作计划
     * @param zgWorkPlanDto
     * @创建人 nhy
     * @创建时间 2020/3/24 9:49
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void addWorkPlan(ZGWorkPlanDto zgWorkPlanDto);

    /***
     * updateWorkPlan方法概述:修改工作计划
     * @param zgWorkPlanDto
     * @创建人 nhy
     * @创建时间 2020/3/24 10:06
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void updateWorkPlan(ZGWorkPlanDto zgWorkPlanDto);

    /***
     * updateWorkPlan方法概述:修改工作计划
     * @param zgWorkPlanAssessDto
     * @创建人 nhy
     * @创建时间 2020/3/24 10:06
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void updateWorkAssessPlan(ZGWorkPlanAssessDto zgWorkPlanAssessDto);

    /***
     * selectMyPlan方法概述:查询我的所有日程计划
     * @param
     * @return org.forbes.comm.vo.Result<java.util.List < org.smartwork.dal.entity.ZGWorkPlan>>
     * @创建人 Tom
     * @创建时间 2020/3/31 14:51
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    IPage<ZGWorkPlan> page(IPage<ZGWorkPlan> page, ZGWorkPlanPageDto zgWorkPlanPageDto);
}