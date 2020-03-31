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
import org.smartwork.biz.service.*;
import org.smartwork.comm.enums.CmAdminFlagEnum;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.enums.ProjectStateEnum;
import org.smartwork.comm.enums.ProjectTypeEnum;
import org.smartwork.comm.model.ProjectPageDto;
import org.smartwork.comm.model.ZGProjectDto;
import org.smartwork.dal.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/***
 * 概述:项目操作
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/24
 * @修改人 (修改了该文件 ， 请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/project")
@Api(tags = {"API--项目分页,详情,修改删除等"})
@Slf4j
public class ZGProjectApiProvider {

    @Autowired
    IZGProjectService projectService;
    @Autowired
    IZGTeamService teamService;
    @Autowired
    IZGTeamRelUserService teamRelUserService;
    @Autowired
    IZGCompanyService companyService;
    @Autowired
    IZGCmRelUserService cmRelUserService;


    /***
     * 方法概述:分页查询项目列表
     * @param pageDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/24
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
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
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
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
        //只有任务类型为0 团队类型 和 1 公司类型 的时候才能创建项目
        if (!projectDto.getDataType().equals(Long.valueOf(ProjectTypeEnum.COMPANY_PRO.getCode()))
                || !projectDto.getDataType().equals(Long.valueOf(ProjectTypeEnum.TEAM_PRO.getCode()))) {
            result.setBizCode(MemberBizResultEnum.DATA_TYPE_NO_EXIST.getBizCode());
            result.setMessage(MemberBizResultEnum.DATA_TYPE_NO_EXIST.getBizMessage());
            return result;
        }
        SysUser user = UserContext.getSysUser();
        //判断项目数据类型是否为团队类型
        if (projectDto.getDataType().equals(Long.valueOf(ProjectTypeEnum.TEAM_PRO.getCode()))) {
            //判断判断当前操作人是否为团队的管理员
            QueryWrapper<ZGTeamRelUser> query = new QueryWrapper<>();
            query.eq("user_id", user.getId());
            query.eq("team_id", projectDto.getDataId());
            ZGTeamRelUser teamRelUser = teamRelUserService.getOne(query);
            if (!teamRelUser.getAdminFlag().equals(CmAdminFlagEnum.ORDINARY.getCode())) {
                result.setBizCode(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizCode());
                result.setMessage(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizMessage());
                return result;
            }
        }
        //判断项目数据类型是否为公司类型
        if (projectDto.getDataType().equals(Long.valueOf(ProjectTypeEnum.COMPANY_PRO.getCode()))) {
            //判断判断当前操作人是公司类型的管理员
            QueryWrapper<ZGCmRelUser> query = new QueryWrapper<>();
            query.eq("user_id", user.getId());
            query.eq("cm_id", projectDto.getDataId());
            ZGCmRelUser cmRelUser = cmRelUserService.getOne(query);
            if (!cmRelUser.getAdminFlag().equals(CmAdminFlagEnum.ORDINARY.getCode())) {
                result.setBizCode(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizCode());
                result.setMessage(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizMessage());
                return result;
            }
        }
        projectDto.setStatus(ProjectStateEnum.NOT_YET_BEGUN.getCode());
        projectService.createPro(projectDto);
        result.setResult(projectDto);
        return result;
    }


    /***
     * 方法概述:编辑项目(完善项目)
     * @param projectDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/24
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/modify-project", method = RequestMethod.POST)
    @ApiOperation("编辑项目(完善项目)")
    public Result<ZGProjectDto> modifyPro(@RequestBody @Validated(value = UpdateValid.class) ZGProjectDto projectDto) {
        Result<ZGProjectDto> result = new Result<>();
        if (ConvertUtils.isEmpty(projectDto)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        SysUser user = UserContext.getSysUser();
        //任务类型1 公司类型
        if (projectDto.getDataType().equals(Long.valueOf(ProjectTypeEnum.COMPANY_PRO.getCode()))) {
            QueryWrapper<ZGCmRelUser> query = new QueryWrapper<>();
            query.eq("user_id", user.getId());
            query.eq("cm_id", projectDto.getDataId());
            ZGCmRelUser cmRelUser = cmRelUserService.getOne(query);
            if (!cmRelUser.getAdminFlag().equals(CmAdminFlagEnum.ORDINARY.getCode())) {
                result.setBizCode(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizCode());
                result.setMessage(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizMessage());
                return result;
            }
            projectService.modifyPro(projectDto);
        }
        if (projectDto.getDataType().equals(Long.valueOf(ProjectTypeEnum.TEAM_PRO.getCode()))) {
            QueryWrapper<ZGTeamRelUser> query = new QueryWrapper<>();
            query.eq("user_id", user.getId());
            query.eq("team_id", projectDto.getDataId());
            ZGTeamRelUser teamRelUser = teamRelUserService.getOne(query);
            if (!teamRelUser.getAdminFlag().equals(CmAdminFlagEnum.ORDINARY.getCode())) {
                result.setBizCode(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizCode());
                result.setMessage(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizMessage());
                return result;
            }
            projectService.modifyPro(projectDto);
        }
        result.setResult(projectDto);
        return result;
    }

    /***
     * 方法概述:事实更新项目进度
     * @param id
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/31
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/real-time", method = RequestMethod.POST)
    @ApiOperation("事实更新项目进度")
    public Result<ZGProject> realTime(@RequestParam(value = "id", required = true) Long id) {
        Result<ZGProject> result = new Result<>();
        ZGProject project = projectService.getById(id);
        if (ConvertUtils.isEmpty(project)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        result.setResult(project);
        return result;
    }


    /***
     * 方法概述:删除项目
     * @param id
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/31
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/remove-pro", method = RequestMethod.POST)
    @ApiOperation("删除项目")
    public Result<ZGProject> removePro(@RequestParam(value = "id", required = true) String id) {
        Result<ZGProject> result = new Result<>();
        ZGProject project = projectService.getById(id);
        if (ConvertUtils.isEmpty(project)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        SysUser user = UserContext.getSysUser();
        //是当前团队的项目,可以删除
        if (project.getDataType().equals(ProjectTypeEnum.TEAM_PRO)) {
            ZGTeam team = teamService.getOne(new QueryWrapper<ZGTeam>().eq("legal_person", user.getRealname()));
            if (ConvertUtils.isNotEmpty(team)) {
                projectService.removeById(project);
            }
        }
        //是当前公司的项目,可以删除
        if (project.getDataType().equals(ProjectTypeEnum.COMPANY_PRO)) {
            ZGCompany company = companyService.getOne(new QueryWrapper<ZGCompany>().eq("legal_person", user.getRealname()));
            if (ConvertUtils.isNotEmpty(company)) {
                projectService.removeById(project);
            }
        }
        return result;
    }


}
