package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGProTaskService;
import org.smartwork.comm.constant.ProTaskCommonConstant;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ProTaskPageDto;
import org.smartwork.comm.model.ZGProTaskDto;
import org.smartwork.dal.entity.ZGProTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/***
 * 概述:项目任务(子任务)增删改设置
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/16
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/pro-task")
@Api(tags = {"API--项目任务(子任务)列表,编辑,删除,添加等"})
@Slf4j
public class ZGProTaskApiProvider {

    @Autowired
    IZGProTaskService proTaskService;


    /***
     * 方法概述:分页查询项目任务列表
     * @param pageDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/project-tasks", method = RequestMethod.GET)
    @ApiOperation("分页查询项目任务列表")
    public Result<IPage<ZGProTask>> list(BasePageDto basePageDto, ProTaskPageDto pageDto){
        Result<IPage<ZGProTask>> result = new Result<>();
        QueryWrapper<ZGProTask> qw = new QueryWrapper<>();
        if (ConvertUtils.isNotEmpty(pageDto)) {
            //项目任务名称
            if (ConvertUtils.isNotEmpty(pageDto.getTaskName())) {
                qw.like(ProTaskCommonConstant.PRO_TASK_NAME, pageDto.getTaskName());
            }
            //项目任务状态
            if (ConvertUtils.isNotEmpty(pageDto.getTaskState())) {
                qw.eq(ProTaskCommonConstant.PRO_TASK_STATE, pageDto.getTaskState());
            }
        }
        IPage<ZGProTask> page = new Page<ZGProTask>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ZGProTask> pages = proTaskService.page(page,qw);
        result.setResult(pages);
        return result;

    }


    /***
     * 方法概述:项目任务进度更新
     * @param id,ratio
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/progress-update", method = RequestMethod.PUT)
    @ApiOperation("项目任务进度更新")
    public Result<ZGProTask> progressUpdate(@RequestParam(value = "id") Long id,@RequestParam(value = "ratio") BigDecimal ratio){
        Result<ZGProTask> result = new Result<>();
        ZGProTask proTask = proTaskService.getById(id);
        if(ConvertUtils.isEmpty(proTask)){
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        proTask.setRatio(ratio);
        proTaskService.updateById(proTask);
        result.setResult(proTask);
        return result;

    }



}
