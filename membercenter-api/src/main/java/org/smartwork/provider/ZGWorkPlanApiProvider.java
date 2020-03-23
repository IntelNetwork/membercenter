package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGWorkPlanService;
import org.smartwork.dal.entity.ZGWorkPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    IZGWorkPlanService izgWorkPlanService;

    /***
     * selectPlanDay方法概述:查询我的日程计划
     * @param startTime, endTime
     * @return org.forbes.comm.vo.Result<java.util.List<org.smartwork.dal.entity.ZGWorkPlan>>
     * @创建人 Tom
     * @创建时间 2020/3/23 12:34
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-plan-day", method = RequestMethod.GET)
    @ApiOperation("查询我的日程计划")
    public Result<List<ZGWorkPlan>> selectPlanDay(@RequestParam(value = "startTime",required = true)Date startTime,@RequestParam(value = "endTime",required = true)Date endTime) {
        Result<List<ZGWorkPlan>> result=new Result<List<ZGWorkPlan>>();
        List<ZGWorkPlan> zgWorkPlans = izgWorkPlanService.selectPlanDay(startTime,endTime);
        result.setResult(zgWorkPlans);
        return result;
    }
}
