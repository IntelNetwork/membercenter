package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.biz.service.IZGTeamRelUserService;
import org.smartwork.biz.service.IZGWorkPlanService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGCmRelUserDto;
import org.smartwork.comm.model.ZGWorkPlanAssessDto;
import org.smartwork.comm.model.ZGWorkPlanDto;
import org.smartwork.comm.model.ZGWorkPlanPageDto;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.smartwork.dal.entity.ZGTeamRelUser;
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

    @Autowired
    IZGCmRelUserService izgCmRelUserService;

    @Autowired
    IZGTeamRelUserService izgTeamRelUserService;

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
     * selectPlanDay方法概述:查询成员的日程计划
     * @param name
     * @创建人 nhy
     * @创建时间 2020/3/23 12:34
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-user-plan", method = RequestMethod.GET)
    @ApiOperation("查询成员日程计划")
    public Result<List<ZGWorkPlan>> selectUserPlan(@RequestParam(value = "name")String name) {
        Result<List<ZGWorkPlan>> result=new Result<>();
        List<ZGWorkPlan> zgWorkPlans = workPlanService.list(new QueryWrapper<ZGWorkPlan>().like("name",name));
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
        ZGWorkPlan zgWorkPlan=workPlanService.getById(zgWorkPlanAssessDto.getId());
        if(ConvertUtils.isNotEmpty(zgWorkPlan.getAssessId())){
            result.setBizCode(MemberBizResultEnum.WORK_PLAN_ASSESS.getBizCode());
            result.setMessage(MemberBizResultEnum.WORK_PLAN_ASSESS.getBizMessage());
            return result;
        }
        workPlanService.updateWorkAssessPlan(zgWorkPlanAssessDto);
        result.setResult(zgWorkPlanAssessDto);
        return result;
    }


    /***
     * selectMyPlan方法概述:查询我的所有日程计划
     * @param
     * @return org.forbes.comm.vo.Result<java.util.List < org.smartwork.dal.entity.ZGWorkPlan>>
     * @创建人 Tom
     * @创建时间 2020/3/31 14:51
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-my-plan", method = RequestMethod.GET)
    @ApiOperation("查询我的所有日程计划")
    public Result<IPage<ZGWorkPlan>> selectMyPlan(BasePageDto basePageDto, ZGWorkPlanPageDto zgWorkPlanPageDto) {
        Result<IPage<ZGWorkPlan>> result=new Result<IPage<ZGWorkPlan>>();
        IPage<ZGWorkPlan> page = new Page<ZGWorkPlan>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ZGWorkPlan> pageUsers =  workPlanService.page(page, zgWorkPlanPageDto);
        result.setResult(pageUsers);
        return result;
    }

    /***查询成员日程计划
     * selectMemberPlan方法概述:查询成员日程计划
     * @param
     * @return org.forbes.comm.vo.Result<java.util.List < org.smartwork.dal.entity.ZGWorkPlan>>
     * @创建人 Tom
     * @创建时间 2020/3/31 15:32
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-member-plan", method = RequestMethod.GET)
    @ApiOperation("查询成员日程计划")
    public Result<List<ZGWorkPlan>> selectMemberPlan() {
        Result<List<ZGWorkPlan>> result=new Result<>();
        SysUser user = UserContext.getSysUser();
        //查询当前用户所属公司
        ZGCmRelUser zgCmRelUser = izgCmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq("user_id",user.getId()));
        //查询当前用户所属公司成员
        List<ZGCmRelUser> zgCmRelUsers=izgCmRelUserService.list(new QueryWrapper<ZGCmRelUser>().eq("cm_id",zgCmRelUser.getCmId()));
        zgCmRelUsers.stream().forEach(zgCmRel -> {
            List<ZGWorkPlan> zgWorkPlans = workPlanService.list(new QueryWrapper<ZGWorkPlan>().eq("user_id",zgCmRel.getUserId()));
            result.setResult(zgWorkPlans);
        });
        return result;
    }

    /***
     * selectPlan方法概述:查询计划详情
     * @param id
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGWorkPlan>
     * @创建人 Tom
     * @创建时间 2020/3/31 16:05
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-plan", method = RequestMethod.GET)
    @ApiOperation("查询计划详情")
    public Result<ZGWorkPlan> selectPlan(@RequestParam(value = "id")Long id) {
        Result<ZGWorkPlan> result=new Result<>();
        ZGWorkPlan zgWorkPlan = workPlanService.getById(id);
        result.setResult(zgWorkPlan);
        return result;
    }

}
