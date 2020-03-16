package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTeamRelUserService;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGTeamDto;
import org.smartwork.comm.model.ZGTeamRelUserDto;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.dal.mapper.ZGTeamRelUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class ZGTeamRelUserProvider {

    @Autowired
    IZGTeamRelUserService teamRelUserService;


    /***
     * 方法概述:团队任务分配
     * @param teamRelUserDtos
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
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

}
