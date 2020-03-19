package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTeamService;
import org.smartwork.comm.constant.TeamCommonConstant;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGTeamDto;
import org.smartwork.dal.entity.ZGTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/***
 * 概述:团队增删改设置
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/16
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/team")
@Api(tags = {"API--团队新增,编辑,删除,任务分配,审核等管理"})
@Slf4j
public class ZGTeamApiProvider {
    /**
     * 团队service注入
     */
    @Autowired
    IZGTeamService teamService;

    /***
     * 方法概述:创建团队
     * @param teamDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/create-team", method = RequestMethod.POST)
    @ApiOperation("创建团队")
    public Result<ZGTeamDto> addTeam(@RequestBody @Validated(value = SaveValid.class) ZGTeamDto teamDto) {
        Result<ZGTeamDto> result = new Result<>();
        if (ConvertUtils.isEmpty(teamDto)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        teamService.addTeam(teamDto);
        result.setResult(teamDto);
        return result;
    }

    /***
     * 方法概述:编辑团队
     * @param teamDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/modify-team", method = RequestMethod.PUT)
    @ApiOperation("编辑团队")
    public Result<ZGTeamDto> editTeam(@RequestBody @Validated(value = UpdateValid.class) ZGTeamDto teamDto) {
        Result<ZGTeamDto> result = new Result<>();
        if (ConvertUtils.isEmpty(teamDto)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        if (ConvertUtils.isEmpty(teamDto.getId())) {
            result.setBizCode(MemberBizResultEnum.EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        teamService.editTeam(teamDto);
        result.setResult(teamDto);
        return result;
    }


    /***
     * 方法概述:修改团队负责人(仅负责人可使用)
     * @param id,legalPerson
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/modify-admin", method = RequestMethod.PUT)
    @ApiOperation("修改团队负责人(仅负责人可使用)")
    public Result<ZGTeam> modifyTeamAdmin(@RequestParam(value = "id")Long id, @RequestParam(value = "legalPerson") String legalPerson) {
        Result<ZGTeam> result = new Result<>();
        QueryWrapper<ZGTeam> qw = new QueryWrapper<>();
        qw.eq(TeamCommonConstant.ID,id);
        qw.eq(TeamCommonConstant.LEGAL_PERSON,legalPerson);
        ZGTeam team = teamService.getOne(qw);
        if (ConvertUtils.isEmpty(team)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //对比当前操作人是否是管理员本人(只有管理员本人才可以修改管理员)
        SysUser user = org.forbes.comm.constant.UserContext.getSysUser();
        if(!team.getLegalPerson().equals(user.getUsername())){
            result.setBizCode(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizMessage());
            return result;
        }
        team.setLegalPerson(legalPerson);
        teamService.updateById(team);
        result.setResult(team);
        return result;
    }




}
