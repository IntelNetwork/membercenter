package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTeamRelUserService;
import org.smartwork.comm.constant.ProTaskCommonConstant;
import org.smartwork.comm.constant.TeamRelUserCommonConstant;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ProTaskPageDto;
import org.smartwork.comm.model.ZGTeamRelUserDto;
import org.smartwork.comm.model.ZGTeamRelUserPageDto;
import org.smartwork.dal.entity.ZGProTask;
import org.smartwork.dal.entity.ZGTeamRelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * 概述:团队任务分配
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/16
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/team-user-rel")
@Api(tags = {"API--团队任务分配"})
@Slf4j
public class ZGTeamRelUserApiProvider {

    @Autowired
    IZGTeamRelUserService teamRelUserService;


    /***
     * 方法概述:团队人员列表
     * @param pageDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/team-users", method = RequestMethod.GET)
    @ApiOperation("分页查询团队人员列表")
    public Result<IPage<ZGTeamRelUser>> list(BasePageDto basePageDto, ZGTeamRelUserPageDto pageDto) {
        Result<IPage<ZGTeamRelUser>> result = new Result<>();
        QueryWrapper<ZGTeamRelUser> qw = new QueryWrapper<>();
        if (ConvertUtils.isNotEmpty(pageDto)) {
            if (ConvertUtils.isNotEmpty(pageDto.getUserName())) {
                qw.like(TeamRelUserCommonConstant.TEAM_USER_NAME, pageDto.getUserName());
            }
        }
        IPage<ZGTeamRelUser> page = new Page<>(basePageDto.getPageNo(), basePageDto.getPageSize());
        IPage<ZGTeamRelUser> pages = teamRelUserService.page(page, qw);
        result.setResult(pages);
        return result;
    }


    /***
     * 方法概述:团队任务分配
     * @param teamRelUserDtos
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ApiOperation("团队任务分配")
    public Result<List<ZGTeamRelUserDto>> editTeam(@RequestBody @Validated(value = UpdateValid.class) List<ZGTeamRelUserDto> teamRelUserDtos) {
        Result<List<ZGTeamRelUserDto>> result = new Result<>();
        if (ConvertUtils.isEmpty(teamRelUserDtos)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        teamRelUserService.addTeamRelUser(teamRelUserDtos);
        result.setResult(teamRelUserDtos);
        return result;
    }


    /***
     * 方法概述:团队人员删除
     * @param teamId,userId
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/team-user-remove", method = RequestMethod.DELETE)
    @ApiOperation("团队人员删除")
    public Result<List<ZGTeamRelUserDto>> removeTeamUser(@RequestParam(value = "teamId") Long teamId, @RequestParam(value = "userId") Long userId) {
        Result<List<ZGTeamRelUserDto>> result = new Result<>();
        if (ConvertUtils.isEmpty(teamId) || ConvertUtils.isEmpty(userId)) {
            result.setBizCode(MemberBizResultEnum.EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        QueryWrapper<ZGTeamRelUser> qw = new QueryWrapper<>();
        qw.eq(TeamRelUserCommonConstant.TEAM_TEAM_ID, teamId);
        qw.eq(TeamRelUserCommonConstant.TEAM_USER_ID, userId);
        ZGTeamRelUser teamRelUser = teamRelUserService.getOne(qw);
        teamRelUserService.removeById(teamRelUser);
        return result;
    }
}
