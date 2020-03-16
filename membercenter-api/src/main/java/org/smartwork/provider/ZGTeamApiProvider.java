package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTeamService;
import org.smartwork.comm.constant.SaveValid;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGTeamDto;
import org.smartwork.comm.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/add", method = RequestMethod.POST)
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
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
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




}
