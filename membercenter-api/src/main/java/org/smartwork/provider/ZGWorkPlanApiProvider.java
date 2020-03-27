package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGWorkPlanService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGCmRelUserDto;
import org.smartwork.comm.model.ZGWorkPlanAssessDto;
import org.smartwork.comm.model.ZGWorkPlanDto;
import org.smartwork.dal.entity.ZGWorkPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author lzw
 * @date 2020/3/23 11:23
 */
@RestController
@RequestMapping("/${smartwork.verision}/work-plan")
@Api(tags = {"API--工作计划管理"})
@Slf4j
public class ZGWorkPlanApiProvider {

    @Autowired
    IZGWorkPlanService workPlanService;

    /***
     * selectPlanDay方法概述:查询我的日程计划
     * @param startTime, endTime
     * @创建人 nhy
     * @创建时间 2020/3/23 12:34
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-plan-day", method = RequestMethod.GET)
    @ApiOperation("查询我的日程计划")
    public Result<List<ZGWorkPlan>> selectPlanDay(@RequestParam(value = "startTime")Date startTime,@RequestParam(value = "endTime")Date endTime) {
        Result<List<ZGWorkPlan>> result=new Result<>();
        List<ZGWorkPlan> zgWorkPlans = workPlanService.selectPlanDay(startTime,endTime);
        result.setResult(zgWorkPlans);
        return result;
    }

    /***
     * addWorkPlan方法概述:写工作计划
     * @param zgWorkPlanDto
     * @创建人 nhy
     * @创建时间 2020/3/24 9:49
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/insert-work-plan", method = RequestMethod.POST)
    @ApiOperation("写工作计划")
    public Result<ZGWorkPlanDto> addWorkPlan(@RequestBody @Validated(value = SaveValid.class) ZGWorkPlanDto zgWorkPlanDto) {
        Result<ZGWorkPlanDto> result = new Result<>();
        if (ConvertUtils.isEmpty(zgWorkPlanDto)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        workPlanService.addWorkPlan(zgWorkPlanDto);
        result.setResult(zgWorkPlanDto);
        return result;
    }

    /***
     * updateWorkPlan方法概述:修改工作计划
     * @param zgWorkPlanDto
     * @创建人 nhy
     * @创建时间 2020/3/24 10:06
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update-work-plan", method = RequestMethod.PUT)
    @ApiOperation("修改工作计划")
    public Result<ZGWorkPlanDto> updateWorkPlan(@RequestBody @Validated(value = UpdateValid.class) ZGWorkPlanDto zgWorkPlanDto) {
        Result<ZGWorkPlanDto> result = new Result<>();
        if (ConvertUtils.isEmpty(zgWorkPlanDto)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        workPlanService.updateWorkPlan(zgWorkPlanDto);
        result.setResult(zgWorkPlanDto);
        return result;
    }

    /***
     * updateWorkPlan方法概述:修改工作计划
     * @param zgWorkPlanAssessDto
     * @创建人 nhy
     * @创建时间 2020/3/24 10:06
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update-assess-plan", method = RequestMethod.PUT)
    @ApiOperation("修改(评估)工作计划")
    public Result<ZGWorkPlanAssessDto> updateWorkAssessPlan(@RequestBody @Validated(value = UpdateValid.class) ZGWorkPlanAssessDto zgWorkPlanAssessDto) {
        Result<ZGWorkPlanAssessDto> result = new Result<>();
        if (ConvertUtils.isEmpty(zgWorkPlanAssessDto)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        if(ConvertUtils.isNotEmpty(zgWorkPlanAssessDto.getAssessId())){
            result.setBizCode(MemberBizResultEnum.WORK_PLAN_ASSESS.getBizCode());
            result.setMessage(MemberBizResultEnum.WORK_PLAN_ASSESS.getBizMessage());
            return result;
        }
        workPlanService.updateWorkAssessPlan(zgWorkPlanAssessDto);
        result.setResult(zgWorkPlanAssessDto);
        return result;
    }
}
