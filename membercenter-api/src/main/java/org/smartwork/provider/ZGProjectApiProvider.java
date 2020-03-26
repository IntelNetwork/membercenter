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
import org.smartwork.biz.service.IZGProjectService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.enums.ProjectStateEnum;
import org.smartwork.comm.model.ProjectPageDto;
import org.smartwork.comm.model.ZGProjectDto;
import org.smartwork.dal.entity.ZGProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * 概述:项目操作
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/24
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/project")
@Api(tags = {"API--项目分页,详情,修改删除等"})
@Slf4j
public class ZGProjectApiProvider {

    @Autowired
    IZGProjectService projectService;


    /***
     * 方法概述:分页查询项目列表
     * @param pageDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/24
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/create-project", method = RequestMethod.GET)
    @ApiOperation("分页查询项目列表")
    public Result<IPage<ZGProject>> list(BasePageDto basePageDto, ProjectPageDto pageDto) {
        Result<IPage<ZGProject>> result = new Result<>();
        QueryWrapper<ZGProject> qw = new QueryWrapper<>();
        if (ConvertUtils.isNotEmpty(pageDto)) {
            if (ConvertUtils.isNotEmpty(pageDto.getName())) {
                qw.like("name", pageDto.getName());
            }
            if (ConvertUtils.isNotEmpty(pageDto.getStartDate())) {
                qw.ge("start_date", pageDto.getStartDate());
            }
            if (ConvertUtils.isNotEmpty(pageDto.getEndTime())) {
                qw.le("end_time", pageDto.getEndTime());
            }
        }
        IPage<ZGProject> page = new Page<>(basePageDto.getPageNo(), basePageDto.getPageSize());
        IPage<ZGProject> pages = projectService.page(page, qw);
        result.setResult(pages);
        return result;
    }


    /***
     * 方法概述:创建项目
     * @param projectDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/24
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/create-project", method = RequestMethod.POST)
    @ApiOperation("创建项目")
    public Result<ZGProjectDto> createPro(@RequestBody @Validated(value = SaveValid.class) ZGProjectDto projectDto) {
        Result<ZGProjectDto> result = new Result<>();
        if (ConvertUtils.isEmpty(projectDto)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        SysUser user = UserContext.getSysUser();
        //判断当前操作人是否为团队/公司的管理员
        projectDto.setStatus(ProjectStateEnum.NOT_YET_BEGUN.getCode());
        projectService.createPro(projectDto);
        result.setResult(projectDto);
        return result;
    }



    /***
     * 方法概述:编辑项目
     * @param projectDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/24
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/modify-project", method = RequestMethod.POST)
    @ApiOperation("编辑项目")
    public Result<ZGProjectDto> modifyPro(@RequestBody @Validated(value = UpdateValid.class) ZGProjectDto projectDto) {
        Result<ZGProjectDto> result = new Result<>();
        if (ConvertUtils.isEmpty(projectDto)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }

        projectService.modifyPro(projectDto);
        result.setResult(projectDto);
        return result;
    }
}
