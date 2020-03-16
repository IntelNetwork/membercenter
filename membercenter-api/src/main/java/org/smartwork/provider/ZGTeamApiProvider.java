package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTeamService;
import org.smartwork.comm.constant.SaveValid;
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
@Api(tags = {"团队新增,编辑,删除,任务分配,审核等管理"})
@Slf4j
public class ZGTeamApiProvider {
    /**
     * 团队service注入
     */
    @Autowired
    IZGTeamService teamService;



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("创建团队")
    public Result<ZGTeamDto> addTeam(@RequestBody @Validated(value = SaveValid.class)ZGTeamDto teamDto) {
        Result<ZGTeamDto> result = new Result<>();
        if(ConvertUtils.isEmpty(teamDto)){

        }

        return result;
    }


}
